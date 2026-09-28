package dev.gugel.mathformulas.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormulasTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateRectangleArea() {
        assertEquals( 12.0, Formulas.calculateRectangleArea( 3.0, 4.0 ) );
    }

    @Test
    void calculateRectangleAreaFailed() {
        assertAll(
                () -> assertNull( Formulas.calculateRectangleArea( null, 4.0) ),
                () -> assertNull( Formulas.calculateRectangleArea( 3.0, null) )
        );
    }

    @Test
    void calculateRectanglePerimeter() {
        assertEquals( 14.0, Formulas.calculateRectanglePerimeter( 3.0, 4.0 ) );
    }

    @Test
    void calculateRectanglePerimeterFailed() {
        assertAll(
                () -> assertNull( Formulas.calculateRectanglePerimeter( null, 4.0 ) ),
                () -> assertNull( Formulas.calculateRectanglePerimeter( 3.0, null ) )
        );
    }

    @Test
    void calculateTriangleArea() {
        assertAll(
                () -> assertEquals( 6.0, Formulas.calculateTriangleArea( 3.0, 4.0, 5.0 ) ),
                () -> assertEquals( 6.0, Formulas.calculateTriangleArea( 5.0, 3.0, 4.0 ) ),
                () -> assertEquals( 12.0, Formulas.calculateTriangleArea( 5.0, 5.0, 6.0 ) ),
                () -> assertEquals( Math.sqrt( 3.0 ), Formulas.calculateTriangleArea( 2.0, 2.0, 2.0 ), 1e-12 )
        );
    }

    @Test
    void calculateTriangleAreaExtremeSides() {
        assertAll(
                // no intermediate overflow while the area itself still fits in a double
                () -> assertEquals( Math.sqrt( 3.0 ) / 4 * 1e200, Formulas.calculateTriangleArea( 1e100, 1e100, 1e100 ), 1e186 ),
                // thin triangle: base 1, legs 1e16
                () -> assertEquals( 5e15, Formulas.calculateTriangleArea( 1e16, 1.0, 1e16 ), 10.0 )
        );
    }

    @Test
    void isTriangle() {
        assertAll(
                () -> assertTrue( Formulas.isTriangle( 3.0, 4.0, 5.0 ) ),
                () -> assertTrue( Formulas.isTriangle( 1e16, 1.0, 1e16 ) ),
                () -> assertFalse( Formulas.isTriangle( 1.0, 2.0, 3.0 ) ),
                () -> assertFalse( Formulas.isTriangle( 10.0, 1.0, 2.0 ) )
        );
    }

    @Test
    void calculateTriangleAreaFailed() {
        assertAll(
                () -> assertNull( Formulas.calculateTriangleArea( null, 4.0, 5.0 ) ),
                () -> assertNull( Formulas.calculateTriangleArea( 3.0, null, 5.0 ) ),
                () -> assertNull( Formulas.calculateTriangleArea( 3.0, 4.0, null ) )
        );
    }

    @Test
    void calculateTrianglePerimeter() {
        assertEquals( 12.0, Formulas.calculateTrianglePerimeter( 3.0, 4.0, 5.0 ) );
    }

    @Test
    void calculateTrianglePerimeterFailed() {
        assertAll(
                () -> assertNull( Formulas.calculateTrianglePerimeter( null, 4.0, 5.0 ) ),
                () -> assertNull( Formulas.calculateTrianglePerimeter( 3.0, null, 5.0 ) ),
                () -> assertNull( Formulas.calculateTrianglePerimeter( 3.0, 4.0, null ) )
        );
    }
}