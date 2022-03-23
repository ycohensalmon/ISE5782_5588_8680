package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

/**
 * this class represent a plane defined by a point in space and a vertical vector
 */
public class Plane implements Geometry {
    final private Point q0;
    final private Vector normal;


    /**
     * calculating the normal of plane
     * @param p point
     * @return vector of the normal
     */
    public Vector getNormal(Point p) {
        return this.normal;
    }

    /**
     * constructor
     * @param point1 point 1
     * @param point2 point 2
     * @param point3 point 3
     */
    public Plane(Point point1, Point point2, Point point3) {
        q0= point1;
        Vector v1 = point2.subtract(point1);
        Vector v2 = point3.subtract(point1);
        normal= v1.crossProduct(v2).normalize();
    }

    /**
     * constructor
     * @param point point
     * @param vector vector of normal
     */
    public Plane(Point point, Vector vector) {
        normal = vector.normalize();
        q0 = point;
    }

    /**
     * getting q0
     * @return this point
     */
    public Point getQ0() { return this.q0; }

    /**
     * getting normal
     * @return normal of plane
     */
    public Vector getNormal() {
        return this.normal;
    }

    /**
     * to string
     * @return values of plane
     */
    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    /**
     * find all intersection points {@link Point}
     * that intersect with a specific ray{@link Ray}
     * @param ray ray pointing towards the plane
     * @return immutable list of intersection points {@link Point}
     */
    @Override
    public List<Point> findIntsersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        double nv = n.dotProduct(v);
        //ray parallel to plane
        if (isZero(nv))
            return null;
        double nQMinusP0 = n.dotProduct(q0.subtract(p0));
        double t = alignZero(nQMinusP0 / nv);
        if (t > 0){
            Point p = p0.add(v.scale(t));
            return List.of(p);
        }
        //t<=0
        return null;
    }
}
