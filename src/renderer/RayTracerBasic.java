package renderer;

import lighting.DirectionalLight;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import static geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * A basic implementation of a ray tracer class.
 */

public class RayTracerBasic extends RayTracerBase {

    /**
     * The deep of the recursion of transparencies / reflections
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * check when the color is week, and we will stop the calculation
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * A constant variable is equal 1
     */
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Constructs a new instance of ray tracer with a given scene.
     *
     * @param scene The given scene.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * calculate the color at a specific point
     *
     * @param closestPoint the point that we calculate its color
     * @param ray          the ray towards the pixel
     * @param isSoftShadow is soft shadow
     * @return the color at this point with the ambient light, local and global effects
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray, boolean isSoftShadow, int numOfRays, boolean isASS, int depth) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K, isSoftShadow, numOfRays, isASS, depth)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate the color at a specific point
     *
     * @param geoPoint     the point that we calculate its color
     * @param ray          the ray towards the pixel
     * @param level        the level of the recursion
     * @param isSoftShadow is soft shadow
     * @return the color at this point with the local and global effects
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k, boolean isSoftShadow, int numOfRays, boolean isASS, int depth) {
        Color color = calcLocalEffects(geoPoint, ray, k, isSoftShadow, numOfRays, isASS, depth);
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k, isSoftShadow, numOfRays, isASS, depth));
    }

    /**
     * calculate the global effects on the color in a point
     *
     * @param intersection the closet point intersect with the ray
     * @param ray          the raya from the camera
     * @param level        the level of the recursion
     * @param isSoftShadow is soft shadow
     * @return the global effects color
     */
    private Color calcGlobalEffects(GeoPoint intersection, Ray ray, int level, Double3 k, boolean isSoftShadow, int numOfRays, boolean isASS, int depth) {
        Vector n = intersection.geometry.getNormal(intersection.point);
        Material material = intersection.geometry.getMaterial();
        Vector v = ray.getDir();
        Ray refractedRay = constructRefractedRay(intersection.point, v, n);
        Ray reflectedRay = constructReflectedRay(intersection.point, v, n);
        return calcGlobalEffect(refractedRay, level, k, material.kT, isSoftShadow, numOfRays, isASS, depth)
                .add(calcGlobalEffect(reflectedRay, level, k, material.kR, isSoftShadow, numOfRays, isASS, depth));
    }

    /**
     * calc the color affected by refraction/reflection
     *
     * @param ray          the reflected/refracted ray
     * @param level        the level of the recursion
     * @param kx           kr/kt
     * @param isSoftShadow is soft shadow
     * @return the color affected by refraction/reflection
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx, boolean isSoftShadow, int numOfRays, boolean isASS, int depth) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint reflectedPoint = findClosestIntersection(ray);
        return reflectedPoint == null ? scene.background : calcColor(reflectedPoint, ray, level - 1, kkx, isSoftShadow, numOfRays, isASS, depth).scale(kx);
    }

    /**
     * calculate how shaded the point is
     *
     * @param gp    the geo point we check how shaded it is
     * @param light the light source
     * @param l     direction from light to point
     * @param n     normal from the object at the point
     * @return the shadow level on the spot
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(gp.point));
        Double3 ktr = Double3.ONE;
        if (intersections == null)
            return ktr;

        for (GeoPoint geoP : intersections) {
            ktr = ktr.product(geoP.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }
        return ktr;
    }

    /**
     * calculated light contribution from all light sources
     *
     * @param geoPoint     the geo point we calculate the color of
     * @param ray          ray from the camera to the point
     * @param isSoftShadow is soft shadow
     * @param isASS        is adaptive super sampling
     * @return the color from the lights at the point
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k, boolean isSoftShadow, int numOfRays, boolean isASS, int depth) {
        Color color = geoPoint.geometry.getEmission();//Color.BLACK;

        //get color given by every light source

        for (LightSource lightSource : scene.lights) {
            if (isSoftShadow) {
                Color color1 = new Color(0, 0, 0);
                if (!isASS) {
                    for (Vector[] ls : lightSource.getList(geoPoint.point, numOfRays))
                        for (Vector l : ls) {
                            color1 = getColor(geoPoint, k, lightSource, color1, l, ray);
                        }
                    color = color.add(color1.reduce(Math.pow(lightSource.getList(geoPoint.point, numOfRays).length, 2)));
                } else {
                    Vector[][] lss = lightSource.getList(geoPoint.point, numOfRays);
                    Color lu = getColor(geoPoint, k, lightSource, color1, lss[0][0], ray);
                    Color ld = getColor(geoPoint, k, lightSource, color1, lss[lss.length - 1][0], ray);
                    Color ru = getColor(geoPoint, k, lightSource, color1, lss[0][lss.length - 1], ray);
                    Color rd = getColor(geoPoint, k, lightSource, color1, lss[lss.length - 1][lss.length - 1], ray);
                    if (lu.equals(ld) && lu.equals(ru) && lu.equals(rd)) {
                        color = color.add(lu);
                    } else {
                        Color help = helpSuperSampling(lss, lu, ld, ru, rd, 0, 0, lss.length - 1, lss.length - 1, geoPoint, ray, k, lightSource, depth);
                        color = color.add(help);
                    }
                }
            } else {
                Vector l = lightSource.getL(geoPoint.point);
                color = getColor(geoPoint, k, lightSource, color, l, ray);
            }
        }
        return color;
    }

    /**
     * the function helps calcLocalEffects to get the color with super sampling
     *
     * @param lss         the matrix of rays from the light
     * @param lu          the left up point
     * @param ld          the left down point
     * @param ru          the right up point
     * @param rd          the right down point
     * @param x           index x of left up
     * @param y           index y of left up
     * @param z           index x of right down
     * @param w           index y of right down
     * @param geoPoint    the geo point we calculate the color of
     * @param ray         ray from the camera to the point
     * @param lightSource the current light source
     * @param depth       the deep of the recursion
     * @return the color from the lights at the point
     */
    private Color helpSuperSampling(Vector[][] lss, Color lu, Color ld, Color ru, Color rd, int x, int y, int z, int w, GeoPoint geoPoint, Ray ray, Double3 k, LightSource lightSource, int depth) {
        if (depth == 0)
            return lu;
        Color col = Color.BLACK;
        Color mu = getColor(geoPoint, k, lightSource, Color.BLACK, lss[x][(y + w) / 2], ray);
        Color md = getColor(geoPoint, k, lightSource, Color.BLACK, lss[z][(y + w) / 2], ray);
        Color mm = getColor(geoPoint, k, lightSource, Color.BLACK, lss[(x + z) / 2][(y + w) / 2], ray);
        Color lm = getColor(geoPoint, k, lightSource, Color.BLACK, lss[(x + z) / 2][y], ray);
        Color rm = getColor(geoPoint, k, lightSource, Color.BLACK, lss[(x + z) / 2][w], ray);
        if (lu.equals(mu) && lu.equals(mm) && lu.equals(lm))
            col = col.add(lu);
        else
            col = col.add(helpSuperSampling(lss, lu, lm, mu, mm, x, y, (x + z) / 2, (y + w) / 2, geoPoint, ray, k, lightSource, depth - 1));
        if (mu.equals(ru) && mu.equals(mm) && mu.equals(rm))
            col = col.add(mu);
        else
            col = col.add(helpSuperSampling(lss, mu, mm, ru, rm, x, (y + w) / 2, (x + z) / 2, w, geoPoint, ray, k, lightSource, depth - 1));
        if (lm.equals(mm) && lm.equals(ld) && lm.equals(md))
            col = col.add(lm);
        else
            col = col.add(helpSuperSampling(lss, lm, ld, mm, md, (x + z) / 2, y, z, (y + w) / 2, geoPoint, ray, k, lightSource, depth - 1));
        if (mm.equals(rm) && mm.equals(md) && mm.equals(rd))
            col = col.add(mm);
        else
            col = col.add(helpSuperSampling(lss, mm, md, rm, rd, (x + z) / 2, (y + w) / 2, z, w, geoPoint, ray, k, lightSource, depth - 1));
        return col.reduce(4);
    }

    /**
     * get color given by every light source
     *
     * @param geoPoint    the geo point we calculate the color of
     * @param lightSource the light source
     * @param color1      color of the pixel
     * @param l           direction from light to point
     * @param ray         from the camera to the point
     * @return color of the pixel
     */
    private Color getColor(GeoPoint geoPoint, Double3 k, LightSource lightSource, Color color1, Vector l, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = geoPoint.geometry.getMaterial().nShininess;
        Double3 kd = geoPoint.geometry.getMaterial().kD;
        Double3 ks = geoPoint.geometry.getMaterial().kS;
        double nl = alignZero(n.dotProduct(l));

        if (nl * nv > 0) { // sign(nl) == sign(nv)
            //get transparency of the object
            Double3 ktr = transparency(geoPoint, lightSource, l, n);
            if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) { //check if the depth of calculation was reached then don't calculate any more
                // color is scaled by transparency to get the right color effect
                Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                //get effects of the color and add them to the color
                color1 = color1.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
            }
        }
        return color1;
    }

    /**
     * Calculate the Specular component of the light at this point
     *
     * @param ks             specular component
     * @param l              direction from light to point
     * @param n              normal from the object at the point
     * @param nl             dot-product n*l
     * @param v              direction from the camera to the point
     * @param nShininess     shininess level
     * @param lightIntensity light intensity
     * @return the Specular component at the point
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.add(n.scale(-2 * nl));
        double vr = alignZero(v.dotProduct(r));
        if (vr >= 0) return Color.BLACK;
        return lightIntensity.scale(ks.scale(Math.pow(-vr, nShininess)));
    }

    /**
     * Calculate the diffusive component of the light at this point
     *
     * @param kd             diffusive component
     * @param nl             dot-product n*l
     * @param lightIntensity light intensity
     * @return the diffusive component at the point
     */
    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
        return lightIntensity.scale(kd.scale(Math.abs(nl)));
    }

    /**
     * Checking for shading between a point and the light source
     *
     * @param light the light source
     * @param gp    the peo point which is shaded or not
     * @param l     direction from light to point
     * @param n     normal from the object at the point
     * @param nl    dot-product n*l
     * @return if the point is unshaded (true) or not
     * @deprecated please use transparency(...) instead of this method
     */
    @Deprecated
    @SuppressWarnings("unused")
    private boolean unshaded(LightSource light, GeoPoint gp, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(gp.point));
        if (intersections == null)
            return true;
        for (GeoPoint geoP : intersections)
            if (geoP.geometry.getMaterial().kT.equals(new Double3(0.0)))
                return false;
        return true;
    }

    /**
     * calculate the reflected ray with shift in delta
     *
     * @param p the initial point
     * @param v direction of the ray to the current point
     * @param n the normal
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Point p, Vector v, Vector n) {
        //r = v - 2.(v.n).n
        double vn = v.dotProduct(n);
        if (isZero(vn)) return null;

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(p, r, n);
    }

    /**
     * calculate the refracted ray with shift in delta
     *
     * @param p the initial point
     * @param v direction of the ray to the current point
     * @param n the normal
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Point p, Vector v, Vector n) {
        return new Ray(p, v, n);
    }

    /**
     * Scans the ray and looks for the first point that cuts the ray
     *
     * @param ray the ray
     * @return the closest point that cuts the ray and null if there is no points
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

    @Override
    public Color traceRay(Ray ray, boolean isSoftShadow, int numOfRays, boolean isASS, int depth) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray, isSoftShadow, numOfRays, isASS, depth);
    }
}
