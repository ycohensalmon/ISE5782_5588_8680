package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class represent a sphere like a ball and defined by point and radius
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class Sphere extends Geometry {
    final private Point center;
    final private double radius;
    final private double radiusSqr;

    /**
     * constructor for sphere by point and radius
     *
     * @param center point
     * @param radius radius of sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radiusSqr = radius * radius;
    }

    /**
     * getting center
     *
     * @return center of sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getting radius
     *
     * @return radius of sphere
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector u;
        try {
            u = center.subtract(p0);
        } catch (IllegalArgumentException ignore) {
            return alignZero(radius - maxDistance) > 0 ? null : List.of(new GeoPoint(this, ray.getPoint(radius)));
        }

        double tm = alignZero(v.dotProduct(u));
        double dSqr = alignZero(u.lengthSquared() - tm * tm);
        double thSqr = radius * radius - dSqr;
        // no intersections : the ray direction is above the sphere
        if (alignZero(thSqr) <= 0) return null;

        double th = alignZero(Math.sqrt(thSqr));

        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null; // both points before p0

        double t1 = alignZero(tm - th);
        if (alignZero(t1 - maxDistance) > 0) return null; // both points after maxDistance

        if (alignZero(t2 - maxDistance) > 0) // 2nd point after maxDistance
            return t1 <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t1)));
        else { // 2nd point between p0 and maxDistance - it's included in the list!
            GeoPoint gp2 = new GeoPoint(this, ray.getPoint(t2));
            return t1 <= 0 ? List.of(gp2) : List.of(new GeoPoint(this, ray.getPoint(t1)), gp2);
        }
    }
}
