package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class represents a list of shapes of all kinds
 */
public class Geometries implements Intersectable{

    List<Intersectable> geometries;

    /**
     * constructor for list of geometries
     */
    public Geometries() {
        this.geometries = new LinkedList<>();
    }

    /**
     * constructor for list of geometries
     * @param geometries list of shapes of all kinds
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        this.add(geometries);
    }

    /**
     * adds geometries to list
     * @param geometries list of shapes of all kinds
     */
    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
