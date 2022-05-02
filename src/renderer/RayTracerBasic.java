package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Geometries;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * A basic implementation of a ray tracer class.
 */

public class RayTracerBasic extends RayTracerBase{

    /**
     * Constructs a new instance of ray tracer with a given scene.
     * @param scene The given scene.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * calculate the color at a specific point
     * @param geoPoint a geo point
     * @return the color at this point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color ambientLight = scene.ambientLight.getIntensity();
        Color emissionLight = geoPoint.geometry.getEmission();
        return ambientLight.add(emissionLight);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            //ray did not intersect any geometrical object
            return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint,ray);
    }
}
