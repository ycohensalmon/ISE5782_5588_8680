package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * this class represent cylinder defined by like tube (ray and radius) and also with height
 */
public class Cylinder extends Tube{

    final private double height;

    /**
     * constructor
     * @param axisRay the ray
     * @param radius the radius
     * @param height the height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getting height
     * @return height of cylinder
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * to string
     * @return values of cylinder
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * calculating the normal of cylinder
     * @param p should be null for flat geometries
     * @return vector of this normal
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
