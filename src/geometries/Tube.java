package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

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
        double t= axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        if (isZero(t))
            return p.subtract(axisRay.getP0()).normalize();

        //calculate the projection of the vector from p to p0 on ray
        //getDir return normalized vector, so we don't need to divide by its length
        Vector projection= axisRay.getDir().scale(t);
        return p.subtract(axisRay.getP0().add(projection)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
