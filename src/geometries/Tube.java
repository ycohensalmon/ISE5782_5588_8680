package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * this class represents a tube defined by ray and radius
 */
public class Tube implements Geometry {
    final protected Ray axisRay;
    final protected double radius;

    /**
     * constructor
     * @param axisRay the ray
     * @param radius the radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * get the ray
     * @return the ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * get the radius
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
