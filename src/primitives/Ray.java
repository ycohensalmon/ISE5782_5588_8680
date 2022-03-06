package primitives;

public class Ray {
    private Point p0;
    private Vector dir;
    /**
     * constractor
     * @param p0 point
     * @param dir vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    public Point getP0() { return p0; }

    public Vector getDir() { return dir; }

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
		if (!(obj instanceof Ray))
			return false;
            Ray other = (Ray) obj;
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