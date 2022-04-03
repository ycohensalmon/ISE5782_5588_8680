package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Unit tests for {@link Vector} class
 *
 * @author Tweig Elhanan & Yossef Cohen-Salmon
 */
class VectorTest {

    private Vector v1 = new Vector(1, 2, 3);
    private Vector v2 = new Vector(0, 3, -2);
    private Vector v3 = new Vector(-2, -4, -6);

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the scaled vector has a scaled length
        assertEquals(3 * v1.length(), v1.scale(3).length(), "TC01: scale() gave wrong result");

        // =============== Boundary Values Tests ==================
        // TC11: Test that scaling a vector by 0 throws an exception
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "TC11: scale() by 0 gave wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void testDotProduct() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that dot product return value is proper
        assertEquals(-28, v1.dotProduct(v3), "TC01:  dotProduct() wrong value");

        // TC02: test that orthogonal vectors return 0 on dot product
        assertEquals(alignZero(v1.dotProduct(v2)), 0, "TC02:  dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that normalize create a unit vector
        Vector u = v.normalize();
        assertEquals(u.length(), 1.0, "TC01: the normalized vector is not a unit vector");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u), "TC01: the normalized vector is not parallel to the original one");
        assertEquals(v.length(), v.dotProduct(u), 0.0000001, "TC01: the normalized vector is not parallel to the original one");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that lengthSquared value is proper
        assertEquals(14, v1.lengthSquared(), "TC01: lengthSquared() gave wrong value");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        Vector v = new Vector(0, 3, 4);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that length value is proper
        assertEquals(5, v.length(), "TC01: length() gave wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that add is proper
        assertEquals((v1.add(v2)), new Vector(1, 5, 1), "TC01: add() gave wrong result");

        // =============== Boundary Values Tests ==================
        //TC11: Test v1 plus -v1 throw exception
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)), "TC01: add() gave wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "TC01: crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(alignZero(vr.dotProduct(v1)), 0, "TC02: crossProduct() result is not orthogonal to 1st operand");
        assertEquals(alignZero(vr.dotProduct(v2)), 0, "TC02: crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3)
                , "TC11: crossProduct() for parallel vectors does not throw an exception");

    }
}