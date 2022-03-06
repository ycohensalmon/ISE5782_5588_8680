package primitives;
import static primitives.Util.*;
import java.lang.*;

public class Vector extends Point{
    /**
     * constructor
     * @param i coordinate value for i axis
     * @param j coordinate value for j axis
     * @param k coordinate value for k axis
     */
    public Vector(double i, double j, double k) {
        super(i, j, k);
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
        Double3 result = xyz.scale(scalar);
        return new Vector(result);
    }

    /**
     * dot product between two vectors (scalar product)
     * @param v the right vector of U.V
     * @return scalre value of dot product
     */
    public double dotProduct(Vector v){
        Double3 result = xyz.product(v.xyz);
        return result.d1+result.d2+result.d3;
    }

    public Vector normalize() { return new Vector(xyz.reduce(this.length())); }
    /**
     * calculating the length of vector
     * @return euclidean length squared of the vector
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     *calculating the length of vector by Pythagoras
     * @return the length of this vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector subtract(Point point) {
        Vector result= super.subtract(point);
        return result;
    }

    /**
     * add method
     *
     * @param other the vector to add
     * @return algebraic added vector
     */
    public Vector add(Vector other) {
        Point result = super.add(other);
        return new Vector(result.xyz);
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
        if (d1 == 0 && d2 == 0 && d3 == 0) throw new IllegalArgumentException("ERROR: zero vector");
        return new Vector(d1,d2,d3);
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
		if (!(obj instanceof Vector))
			return false;
            Vector other = (Vector) obj;
		return isZero(xyz.d1 - other.xyz.d1) && isZero(xyz.d2 - other.xyz.d2) && isZero(xyz.d3 - other.xyz.d3);
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
