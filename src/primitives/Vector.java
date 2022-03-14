package primitives;
import static primitives.Util.*;
import java.lang.*;

/**
 * This class does operations on vectors
 */
public class Vector extends Point{
    /**
     * constructor
     * @param x coordinate value for x axis
     * @param y coordinate value for y axis
     * @param z coordinate value for z axis
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (Double3.ZERO.equals(xyz)) throw new IllegalArgumentException("ERROR: zero vector");
    }

    /**
     * constructor
     * @param xyz value of double3
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (Double3.ZERO.equals(xyz)) throw new IllegalArgumentException("ERROR: zero vector");
    }

    /**
     * Scale (multiply) floating point triad by a number into a new triad where each
     * number is multiplied by the number
     * @param scalar right handle side operand for scaling
     * @return result of scale in vector
     */
    public Vector scale(double scalar){
        if (scalar == 0) throw new IllegalArgumentException("it's not possible to scale by 0");
        return new Vector(xyz.scale(scalar));
    }

    /**
     * dot product between two vectors (scalar product)
     * @param v the right vector of U.V
     * @return scalre value of dot product
     */
    public double dotProduct(Vector v){
        Double3 result = xyz.product(v.xyz);
        return result.d1 + result.d2 + result.d3;
    }

    /**
     * A normalization operation
     * @return a new vector normalized in the same direction as the original vector
     */
    public Vector normalize() { return new Vector(xyz.reduce(this.length())); }

    /**
     * calculating the length of vector
     * @return euclidean length squared of the vector
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * Calculating the length of vector by Pythagoras
     * @return the length of this vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * add method
     *
     * @param other the vector to add
     * @return algebraic added vector
     */
    public Vector add(Vector other) {
        return new Vector(super.add(other).xyz);
    }


    /**
     * Cross product (vectorial product)
     * @param v second vector
     * @return new vector resulting from cross product
     */
    public Vector crossProduct(Vector v) {
        double d1 = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double d2 = xyz.d1 * v.xyz.d3 - xyz.d3 * v.xyz.d1;
        double d3 = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1;
        return new Vector(d1,-d2,d3);
    }

    /**
     * equalsing between two objects
     * @param obj Object (basicaly another Point3d) to compare
     * @return true or false accordingly
     */
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector other))
			return false;
        return super.equals(other);
	}

    /**
     * to string
     * @return value of vector
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
