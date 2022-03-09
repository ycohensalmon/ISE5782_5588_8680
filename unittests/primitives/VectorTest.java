package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Tests for Vector
 */
class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(0, 3, -2);
    Vector v3 = new Vector(-2, -4, -6);

    /**
     * Tests the scale function in Vector class.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the scaled vector has a scaled length
        assertEquals(3 * v1.length(), v1.scale(3).length(), 0.00001, "scale() gave wrong result");

        // =============== Boundary Values Tests ==================
        // TC11: Test that scaling a vector by 0 throws an exception
        assertThrows(IllegalArgumentException.class,() -> v1.scale(0),
                "scale() by 0 gave wrong result");
    }

    /**
     * Tests the dot product function in Vector class.
     */
    @Test
    void dotProduct() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that dot product return value is proper
        assertEquals(-28, v1.dotProduct(v3), 0.00001, "ERROR: dotProduct() wrong value");

        // TC02: test that orthogonal vectors return 0 on dot product
        assertTrue(isZero(v1.dotProduct(v2)), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Tests the normalize function in Vector class.
     */
    @Test
    void normalize() {
//        // test vector normalization vs vector length and cross-product
//        Vector vCopy = new Vector(v1.getHead());
//        Vector vCopyNormalize = vCopy.normalize();
//
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Test that normalize doesn't create a new vector
//        assertSame(vCopy, vCopyNormalize, "ERROR: normalize() function creates a new vector");
//
//        // TC02: Test that normalize makes the vector length 1
//        assertEquals(1, vCopyNormalize.length(), 0.0001, "ERROR: normalize() result is not a unit vector");
    }

    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that lengthSquared value is proper
        assertEquals (14, v1.lengthSquared(), 0.00001, "ERROR: lengthSquared() gave wrong value");
    }

    /**
     * Tests the length function in Vector class.
     */
    @Test
    void length() {
        Vector v = new Vector(0, 3, 4);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that length value is proper
        assertEquals (5, v.length(), 0.00001, "ERROR: length() gave wrong value");
    }

    /**
     * Tests the add function in Vector class.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that add is proper
        assertEquals((v1.add(v2)), new Vector(1, 5, 1), "add() gave wrong result");

        // =============== Boundary Values Tests ==================
        // TC11: Test v1 plus Zero Vector = v1
        assertEquals(v1.add(new Vector(Double3.ZERO)), v1, "add() gave wrong result");
    }

    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3)
        ,"crossProduct() for parallel vectors does not throw an exception");

    }
}