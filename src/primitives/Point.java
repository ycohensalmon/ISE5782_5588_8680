package primitives;
import static primitives.Util.*;

public class Point {

    final Double3 xyz;

    public Point(double i, double j, double k) {
        xyz = new Double3(i, j, k);
    }

    public Vector subtract(Point p) {
        return new Vector(xyz.d1 - p.xyz.d1, xyz.d2 - p.xyz.d2, xyz.d3 - p.xyz.d3);
    }

    public Point add(Point p) {
        return new Point(xyz.d1 + p.xyz.d1, xyz.d2 + p.xyz.d2, xyz.d3 + p.xyz.d3);
    }

    public double distanceSquared(Point p) {
        return (xyz.d1 - p.xyz.d1)*(xyz.d1 - p.xyz.d1) + (xyz.d2 - p.xyz.d2)*(xyz.d2 - p.xyz.d2) + (xyz.d3 - p.xyz.d3)*(xyz.d3 - p.xyz.d3);
    }

    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
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
        return xyz.toString();
    }
}
