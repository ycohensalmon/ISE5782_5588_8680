package primitives;

public class Ray {
    private Point p0;
    private Vector dir;

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

    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }
}