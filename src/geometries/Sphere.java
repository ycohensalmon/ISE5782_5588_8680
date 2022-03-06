package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
    private Point center;
    private double radius;

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
     * @param Point should be null for flat geometries
     * @return the noemal
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
