package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * this class represents a Triangle
 */
public class Triangle extends Polygon {
    /**
     * constructor
     * @param p1 coordinate value for X axis
     * @param p2 coordinate value for Y axis
     * @param p3 coordinate value for Z axis
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
