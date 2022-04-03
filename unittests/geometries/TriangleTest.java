package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Triangle} class
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
class TriangleTest {

    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 0, 0));
        assertEquals(new Vector(0, 1, 0), tr.getNormal(new Point(0.3, 0, 0.3)), "TC01: Bad normal to triangle");
    }

    /**
     * Test method for {@link Triangle#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        //TC01: the ray begins inside the triangle
        assertEquals(triangle.findIntersections(new Ray(new Point(-0.79, -0.62, 0.76), new Vector(1.02, 0.86, -0.76))).size(), 1,
                "TC01: the func doesn't work when the ray through inside the triangle");

        //TC02: the ray begins outside against edge
        assertNull(triangle.findIntersections(new Ray(new Point(0.5, -1, 0), new Vector(0, 1, 0))), "TC02: Ray from outside of triangle against edge.");

        //TC03: the ray begins outside against vertex
        assertNull(triangle.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0))), "TC03: Ray from outside of triangle against vertex.");

        // =============== Boundary Values Tests ==================
        //TC11: the ray begins on edge
        assertNull(triangle.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(0, 0, 1))), "TC11: Ray begins on edge against outside.");

        //TC12: the ray begins in vertex
        assertNull(triangle.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, 0, 1))), "TC12: Ray begins in vertex against outside.");

        //TC13: the ray begins on edge's continuation
        assertNull(triangle.findIntersections(new Ray(new Point(0, 2, 0), new Vector(0, -1, 0))), "TC13: Ray begins on edge's continuation against outside.");

    }
}