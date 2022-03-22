package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * This class represent a sphere like a ball and defined by point and radius
 */
public class Sphere implements Geometry {
    final private Point center;
    final private double radius;

    /**
     * constructor
     * @param center point
     * @param radius radius of sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getting center
     * @return center of sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getting radius
     * @return radius of sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * to string
     * @return values of sphere
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /**
     * calculating the normal of sphere
     * @param p should be null for flat geometries
     * @return the noemal
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
