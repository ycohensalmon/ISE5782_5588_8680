package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * This class represent a sphere like a ball and defined by point and radius
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class Sphere implements Geometry {
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
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector U;
        try {
            U = center.subtract(P0);
        } catch (IllegalArgumentException ignore) {
            return List.of(ray.getPoint(radius));
        }

        double tm = alignZero(v.dotProduct(U));
        double dSqr = alignZero(U.lengthSquared() - tm * tm);
        double thSqr = radiusSqr - dSqr;
        // no intersections : the ray direction is above the sphere
        if (alignZero(thSqr) <= 0) return null;

        double th = alignZero(Math.sqrt(thSqr));

        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null;

        double t1 = alignZero(tm - th);
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
    }
}
