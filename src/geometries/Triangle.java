package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * this class represents a Triangle
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class Triangle extends Polygon {
    /**
     * constructor for triangle by 3 points
     *
     * @param p1 coordinate value for X axis
     * @param p2 coordinate value for Y axis
     * @param p3 coordinate value for Z axis
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = plane.findGeoIntersectionsHelper(ray, maxDistance);

        //Check if the ray intersect the plane.
        if (result == null) return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double vn1 = alignZero(v.dotProduct(n1));
        if (vn1 == 0) return null;

        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double vn2 = alignZero(v.dotProduct(n2));
        if (vn1 * vn2 <= 0) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double vn3 = alignZero(v.dotProduct(n3));
        if (vn1 * vn3 <= 0) return null;

        result.get(0).geometry = this;
        return result;
    }
}
