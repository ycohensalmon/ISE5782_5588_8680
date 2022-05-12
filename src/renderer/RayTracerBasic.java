package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import static geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A basic implementation of a ray tracer class.
 */

public class RayTracerBasic extends RayTracerBase {

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
     * @param geoPoint a geo point
     * @return the color at this point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }

    /**
     * calculated light contribution from all light sources
     *
     * @param geoPoint the geo point we calculate the color of
     * @param ray      ray from the camera to the point
     * @return the color from the lights at the point
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = geoPoint.geometry.getMaterial().nShininess;
        Material material = geoPoint.geometry.getMaterial();

        Color color = geoPoint.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                Color lightIntensity = lightSource.getIntensity(geoPoint.point);
                color = color.add(calcDiffusive(material.kD, nl, lightIntensity),
                        calcSpecular(material.kS, l, n, nl, v, nShininess, lightIntensity));
            }
        }
        return color;
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

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? scene.background :
                calcColor(ray.findClosestGeoPoint(intersections), ray);
    }
}
