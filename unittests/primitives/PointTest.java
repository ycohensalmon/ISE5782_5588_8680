package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for {@link Point} class
 *
 * @author Elhanan Tweig & Yossef Cohen-Salmon
 */
class PointTest {
    private Point p1 = new Point(1, 2, 3);

    /**
     * Test method for {@link Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that add is proper.
        Vector v1 = new Vector(0, 2, -1);
        Point res = new Point(1, 4, 2);
        assertEquals(p1.add(v1), res, "TC01: add method failed");
    }

    /**
     * Test method for {@link Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that subtract is proper.
        Point p2 = new Point(0, 2, -1);
        Vector res = new Vector(1, 0, 4);
        assertEquals(p1.subtract(p2), res, "TC01: subtract method failed");

        // =============== Boundary Values Tests ==================
        //TC11: Test p1 minus p1 throw exception
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "TC11: subtract gave wrong result");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that distance squared is proper.
        Point p2 = new Point(0, 2, -1);
        assertEquals(17, p1.distanceSquared(p2), 0.0000001, "TC01: distanceSquared method failed");
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that distance is proper.
        Point p2 = new Point(5, -2, 1);
        assertEquals(p1.distance(p2), 6, "TC01: distanceSquared method failed");
    }
}