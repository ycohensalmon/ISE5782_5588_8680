package primitives;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Ray class represents the set of points on a line that are on one side of a given point on a line called the head of the fund.
 * Defined by point and direction
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
public class Ray {
    final private Point p0;
    final private Vector dir;

    /**
     * constructor for a ray by point and vector
     *
     * @param p0  point
     * @param dir vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * get point on the ray
     *
     * @return point on ray
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get the vector on the ray
     *
     * @return vector on the ray
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * The function returns the calculation of the pont on the ray
     *
     * @param t distance from the ray head to the point with ray direction
     * @return the point
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * @param lst The list of all the points.
     * @return The closest point to p0 in the list.
     */
    public Point findClosestPoint(List<Point> lst) {
        if (lst == null || lst.size() == 0) return null;

        Point closest = lst.get(0);
        double closestDistance = p0.distanceSquared(closest); // To make the calculations more efficient.
        double tmpDist;
        for (Point point : lst) {
            tmpDist = p0.distanceSquared(point); // To make the calculations more efficient.
            if (tmpDist < closestDistance) {
                closest = point;
                closestDistance = tmpDist;
            }
        }
        return closest;
    }

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

    @Override
    public String toString() {
        return p0.toString() + " , " + dir.toString();
    }
}