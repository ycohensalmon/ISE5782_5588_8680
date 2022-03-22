package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * this class represent a plane defined by a point in space and a vertical vector
 */
public class Plane implements Geometry {
    final private Point p0;
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
        p0= point1;
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
        p0 = point;
    }

    /**
     * getting q0
     * @return this point
     */
    public Point getP0() { return this.p0; }

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
                "p0=" + p0 +
                ", normal=" + normal +
                '}';
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
