package primitives;

/**
 * Ray class represents the set of points on a line that are on one side of a given point on a line called the head of the fund.
 * Defined by point and direction
 */
public class Ray {
    final private Point p0;
    final private Vector dir;
    /**
     * constructor
     * @param p0 point
     * @param dir vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * get point on the ray
     * @return point on ray
     */
    public Point getP0() { return p0; }

    /**
     * get the vector on the ray
     * @return vector on the ray
     */
    public Vector getDir() { return dir; }

    /**
     * The function returns the calculation of the pont on the ray
     * @param t scalar
     * @return p0 + v*t
     */
    public Point getPoint(double t){
        return p0.add(dir.scale(t));
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
		if (!(obj instanceof Ray other))
			return false;
        return this.dir.equals(other.dir) && this.p0.equals(other.p0);
    }

    /**
     * to string
     * @return value of ray
     */
    @Override
    public String toString() {
        return p0.toString() + " , " + dir.toString();
    }
}