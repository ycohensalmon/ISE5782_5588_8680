package primitives;

import static primitives.Util.*;

import java.lang.*;

/**
 * this class serve a point with 3 coordinates, contains an object of three numbers of type Double3
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class Point {

    protected Double3 xyz;
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * constructor that gets the 3 coordinated and build point
     *
     * @param x first coordinate value
     * @param y second coordinate value
     * @param z third coordinate value
     */
    public Point(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * constructor that gets Double3 and build point
     *
     * @param xyz the xyz value
     */
    protected Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * the function calculates vector plus point
     *
     * @param vector the vector that we want to add
     * @return new point of the result
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Getter for x coordinate
     *
     * @return The x coordinate
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Getter for y coordinate
     *
     * @return The y coordinate
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Getter for z coordinate
     *
     * @return The z coordinate
     */
    public double getZ() {
        return xyz.d3;
    }

    /**
     * the function calculates point minus point
     *
     * @param point the point that we want to subtract
     * @return new point of the result
     */
    public Vector subtract(Point point) {
        Double3 newXyz = xyz.subtract(point.xyz);
        return new Vector(newXyz);
    }

    /**
     * the function calculates the distance by minus between them and pow 2 every value
     *
     * @param p the second point
     * @return the result of the distance in pow 2
     */
    public double distanceSquared(Point p) {
        double p1 = xyz.d1 - p.xyz.d1;
        double p2 = xyz.d2 - p.xyz.d2;
        double p3 = xyz.d3 - p.xyz.d3;
        return p1 * p1 + p2 * p2 + p3 * p3;
    }

    /**
     * the function calculates the distance between 2 points by making root
     * to the value that return from distance squared
     *
     * @param p the second point
     * @return the distance squared between 2 points
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Point other))
            return false;
        return this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "" + xyz;
    }
}
