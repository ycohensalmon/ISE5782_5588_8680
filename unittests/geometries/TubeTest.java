package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Tube} class
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Tube tube = new Tube(new Ray(new Point(0, 0, 1), new Vector(0, -1, 0)), 1.0);
        Vector normal = tube.getNormal(new Point(0, 8, 2));
        double check = normal.dotProduct(tube.getAxisRay().getDir());
        boolean firstNormal = new Vector(0, 0, 1).equals(normal);
        boolean secondNormal = new Vector(0, 0, -1).equals(normal);

        //TC01: Test that normal is orthogonal to the tube
        assertEquals(0d, check, "TC01: normal is not orthogonal to the tube");

        //TC02: Test if the normal is proper
        assertTrue(firstNormal || secondNormal, "TC02: wrong normal to tube");

        // =============== Boundary Values Tests ==================
        normal = tube.getNormal(new Point(0, 0, 2));
        check = normal.dotProduct(tube.getAxisRay().getDir());
        firstNormal = new Vector(0, 0, 1).equals(normal);
        secondNormal = new Vector(0, 0, -1).equals(normal);

        //TC11: Test that normal is orthogonal to the tube
        assertEquals(0d, check, "TC11: normal is not orthogonal to the tube");

        //TC12: Test if the normal is proper
        assertTrue(firstNormal || secondNormal, "TC12: wrong normal to tube");
    }
}