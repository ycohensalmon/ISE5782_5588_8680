package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * the interface of all the geometry classs
 */
public interface Geometry extends Intersectable{
    /**
     * the function get point on the geometry and return the vector normal to the geometry surface at the point
     * @param p The point on the geometry
     * @return The vector normal
     */
    public Vector getNormal(Point p);
}
