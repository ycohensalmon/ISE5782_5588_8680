package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * the interface of all the geometry classs
 */
public interface Geometry {
    /**
     * the function get point on the geometry and return the vector normal
     * @param p The point on the geometry
     * @return The vector normal
     */
    public Vector getNormal(Point p);

}
