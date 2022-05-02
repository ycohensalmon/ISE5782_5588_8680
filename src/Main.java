import geometries.Plane;
import primitives.*;

import static java.lang.System.out;
import static primitives.Util.*;

/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     *
     * @param args irrelevant here
     */
    public static void main(String[] args) {

        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        out.println(pl.getNormal());
    }
}
