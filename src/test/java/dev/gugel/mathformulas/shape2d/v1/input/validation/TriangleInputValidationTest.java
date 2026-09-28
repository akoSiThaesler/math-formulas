package dev.gugel.mathformulas.shape2d.v1.input.validation;

import dev.gugel.mathformulas.common.MessageOutput;
import dev.gugel.mathformulas.common.MessageType;
import dev.gugel.mathformulas.shape2d.v1.input.TriangleInput;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleInputValidationTest {

    private TriangleInputValidation validateAllValues( TriangleInput triangleInput ) {
        TriangleInputValidation triangleInputValidation = new TriangleInputValidation( triangleInput );
        triangleInputValidation.validateInputAllValues();
        return triangleInputValidation;
    }

    private List<String> messages( TriangleInputValidation triangleInputValidation ) {
        return triangleInputValidation.getMessageOutputList().stream().map( MessageOutput::getMessage ).toList();
    }

    @Test
    void validTriangle() {
        TriangleInputValidation triangleInputValidation = validateAllValues( new TriangleInput( 3.0, 4.0, 5.0 ) );

        assertTrue( triangleInputValidation.isValid() );
        assertNull( triangleInputValidation.getMessageOutputList() );
    }

    @Test
    void validThinTriangle() {
        assertTrue( validateAllValues( new TriangleInput( 1e16, 1.0, 1e16 ) ).isValid() );
    }

    @Test
    void invalidByNull() {
        TriangleInputValidation triangleInputValidation = validateAllValues( new TriangleInput( null, 4.0, 5.0 ) );

        assertFalse( triangleInputValidation.isValid() );
        assertEquals( List.of( "TriangleInput.a.isNull" ), messages( triangleInputValidation ) );
        assertEquals( MessageType.MANDATORY, triangleInputValidation.getMessageOutputList().get( 0 ).getMessageType() );
    }

    @Test
    void invalidByNegativeOrZero() {
        TriangleInputValidation triangleInputValidation = validateAllValues( new TriangleInput( 3.0, -4.0, 0.0 ) );

        assertFalse( triangleInputValidation.isValid() );
        assertEquals( List.of( "TriangleInput.b.isNegativeOrZero", "TriangleInput.c.isNegativeOrZero" ),
                messages( triangleInputValidation ) );
    }

    @Test
    void invalidByTriangleInequality() {
        assertAll(
                () -> assertEquals( List.of( "TriangleInput.sides.isInvalid" ),
                        messages( validateAllValues( new TriangleInput( 1.0, 2.0, 10.0 ) ) ) ),
                () -> assertEquals( List.of( "TriangleInput.sides.isInvalid" ),
                        messages( validateAllValues( new TriangleInput( 10.0, 1.0, 2.0 ) ) ) ),
                // degenerate: a + b == c
                () -> assertEquals( List.of( "TriangleInput.sides.isInvalid" ),
                        messages( validateAllValues( new TriangleInput( 1.0, 2.0, 3.0 ) ) ) )
        );
    }

    @Test
    void invalidByMissingInput() {
        TriangleInputValidation triangleInputValidation = validateAllValues( null );

        assertFalse( triangleInputValidation.isValid() );
        assertEquals( List.of( "TriangleInput.isNull" ), messages( triangleInputValidation ) );
    }
}
