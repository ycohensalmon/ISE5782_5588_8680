package geometries;

import primitives.*;

import java.util.List;

/**
 * Common interface for all graphic objects
 * that intersect with a {@link Ray}
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public interface Intersectable {
    /**
     * find all intersection {@link Point}s
     * that intersect with a specific {@link Ray}
     *
     * @param ray ray pointing towards the graphic object
     * @return list of intersection {@link Point}s
     */
    List<Point> findIntersections(Ray ray);
}