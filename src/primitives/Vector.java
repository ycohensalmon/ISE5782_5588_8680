package primitives;
import static primitives.Util.*;

public class Vector extends Point{

    public Vector(double i, double j, double k) {
        super(i, j, k);
    }

    public Vector scale(double d) {
        return new Vector(d*xyz.d1 , d*xyz.d2, d*xyz.d3);
    }

    public double lengthSquared() {
        return xyz.d1*xyz.d1 + xyz.d2*xyz.d2 + xyz.d3*xyz.d3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector add(Vector v){
        return new Vector(xyz.d1 + v.xyz.d1, xyz.d2 + v.xyz.d2, xyz.d3 + v.xyz.d3);
    }

    public Vector substrac(Vector v){
        return new Vector(xyz.d1 - v.xyz.d1, xyz.d2 - v.xyz.d2, xyz.d3 - v.xyz.d3);
    }

    public double dotProduct(Vector v) {
        double product = xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
        return product;
    }

    public Vector crossProduct(Vector v) {
        double d1 = xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2;
        double d2 = xyz.d1 * v.xyz.d3 - xyz.d3 * v.xyz.d1;
        double d3 = xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1;
        return new Vector(d1,d2,d3);
    }


    public Vector normalize() {
        return new Vector(xyz.d1/lengthSquared() , xyz.d2/lengthSquared(), xyz.d3/lengthSquared());
    }

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

    @Override
    public String toString() {
        return super.toString();
    }
}
