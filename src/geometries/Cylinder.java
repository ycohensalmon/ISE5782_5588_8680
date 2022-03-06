package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{

    private  double height;

    /**
     * constructor
     * @param axisRay ray
     * @param radius
     * @param height
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
     * @param point should be null for flat geometries
     * @return vector of this normal
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
