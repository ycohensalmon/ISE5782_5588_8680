package geometries;

import primitives.Point;
import primitives.Vector;

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
        q0 = point1;
        normal = null;
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
}
