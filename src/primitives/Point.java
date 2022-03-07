package primitives;
import static primitives.Util.*;
import java.lang.*;

/**
 * this class serve a point with 3 coordinates, contains an object of three numbers of type Double3
 */
public class Point {

    protected final Double3 xyz;

    /**
     * constructor
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * second constructor
     * @param xyz the xyz value
     */
    public Point(Double3 xyz) {
        if (Double3.ZERO.equals(xyz)) throw new IllegalArgumentException("ERROR: zero vector");
        this.xyz = xyz;
    }

    /**
     * the function calculates vector plus point
     * @param vector the vector that we want to add
     * @return new point of the result
     */
    public Point add(Vector vector) {
        Double3 newXyz = xyz.add(vector.xyz);
        return new Point(newXyz);
    }

    /**
     * the function calculates point minus point
     * @param point the point that we want to subtract
     * @return new point of the result
     */
    public Vector subtract(Point point)  {
        Double3 newXyz = xyz.subtract(point.xyz);
        return new Vector(newXyz);
    }

    /**
     * the function calculates the distance by minus between them and pow 2 every value
     * @param p the second point
     * @return the result of the distance in pow 2
     */
    public double distanceSquared(Point p) {
        double p1 = xyz.d1 - p.xyz.d1;
        double p2 = xyz.d2 - p.xyz.d2;
        double p3 = xyz.d3 - p.xyz.d3;
        return p1*p1 + p2*p2 + p3*p3;
    }

    /**
     *the function calculates the distance between 2 points by making root
     *  to the value that return from distance squared
     * @param p the second point
     * @return the distance squared between 2 points
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * checking if the object are the same
     * @param obj Object (basically another Point3d) to compare
     * @return true or false accordingly
     */
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

    /**
     * to string
     * @return the values of point
     */
    @Override
    public String toString() {
        return xyz.toString();
    }
}
