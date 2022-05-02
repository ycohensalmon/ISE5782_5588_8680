package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * the abstract class of all the geometry classs
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public abstract class Geometry extends Intersectable{
    /**
     * the function get point on the geometry and return the vector normal to the geometry surface at the point
     * @param p The point on the geometry
     * @return The vector normal
     */
    public abstract Vector getNormal(Point p);

    /**
     * the color of the geometry
     */
    protected Color emission = Color.BLACK;

    /**
     * getter for emission
     * @return the emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter for emission
     * @param emission the emission color
     * @return the geometry object
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }


}
