package primitives;
import static primitives.Util.*;
import java.lang.*;

public class Point {

    Double3 xyz;

    /**
     * costructor
     * @param i first number value
     * @param j second number value
     * @param k third number value
     */
    public Point(double i, double j, double k) {
        xyz = new Double3(i, j, k);
    }

    /**
     * second constructor
     * @param xyz the xyz value
     */
    public Point(Double3 xyz) { this.xyz = xyz; }

    public Double3 getXyz() { return xyz; }

    /**
     *
     * the function calculates vector plus point
     * @param vector
     * @return new point of the result
     */
    public Point add(Vector vector) {
        Double3 newXyz = xyz.add(vector.xyz);
        return new Point(newXyz);
    }

    /**
     *
     * the function calculates point minus point
     * @param point
     * @return new point of the result
     */
    public Vector subtract(Point point)  {
        Double3 newXyz = xyz.subtract(point.xyz);
        return new Vector(newXyz);
    }

    /**
     * the function calculates the distance by minus between them and pow 2 every value
     * @param p
     * @return the result of the distance in pow 2
     */
    public double distanceSquared(Point p) {
        return (xyz.d1 - p.xyz.d1)*(xyz.d1 - p.xyz.d1) + (xyz.d2 - p.xyz.d2)*(xyz.d2 - p.xyz.d2) + (xyz.d3 - p.xyz.d3)*(xyz.d3 - p.xyz.d3);
    }

    /**
     *the function calculates the distance between 2 points by making root
     *  to the value that return from distance squared
     * @param p
     * @return the distance between 2 points
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * checking if the object are the same
     * @param obj Object (basicaly another Point3d) to compare
     * @return true or false accordingly
     */
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
            Point other = (Point) obj;
		return isZero(xyz.d1 - other.xyz.d1) && isZero(xyz.d2 - other.xyz.d2) && isZero(xyz.d3 - other.xyz.d3);
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
