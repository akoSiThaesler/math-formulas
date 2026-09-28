package dev.gugel.mathformulas.shape2d.v1.input.validation;

import dev.gugel.mathformulas.common.Formulas;
import dev.gugel.mathformulas.common.MessageType;
import dev.gugel.mathformulas.shape2d.common.Shape2DinputValidation;
import dev.gugel.mathformulas.shape2d.v1.input.TriangleInput;

import java.util.ArrayList;

public class TriangleInputValidation extends Shape2DinputValidation {

    private final TriangleInput triangleInput;

    public TriangleInputValidation( TriangleInput triangleInput ) {
        super();
        this.triangleInput = triangleInput;
    }

    public TriangleInput getTriangleInput() {
        return triangleInput;
    }

    @Override
    public void validateInputArea() {
        validateSides();
    }

    @Override
    public void validateInputPerimeter() {
        validateSides();
    }

    /**
     * every side must be set and positive, and the three sides must form a triangle
     */
    private void validateSides() {
        if( getTriangleInput() == null ) {
            validateValueNotNull( null, TriangleInput.class.getSimpleName() );
            return;
        }
        validateValueNotNullOrNegative( getTriangleInput().a(), TriangleInput.class.getSimpleName() + ".a" );
        validateValueNotNullOrNegative( getTriangleInput().b(), TriangleInput.class.getSimpleName() + ".b" );
        validateValueNotNullOrNegative( getTriangleInput().c(), TriangleInput.class.getSimpleName() + ".c" );
        if( isValid() ) {
            validateTriangleInequality( getTriangleInput().a(), getTriangleInput().b(), getTriangleInput().c(),
                    TriangleInput.class.getSimpleName() + ".sides" );
        }
    }

    /**
     * sets validation to invalid and creates a message if one side is at least as long as the other two
     * together, i.e. the sides violate the triangle inequality (a degenerate triangle has no area)
     *
     * @param a side a
     * @param b side b
     * @param c side c
     * @param message set this message, if the sides do not form a triangle
     */
    private void validateTriangleInequality( double a, double b, double c, String message ) {
        if( !Formulas.isTriangle( a, b, c ) ) {
            if( getMessageOutputList() == null ) {
                setMessageOutputList( new ArrayList<>() );
            }
            if( isMessageNew( message, MessageType.ERROR ) ) {
                addInvalidValueMessage( message, MessageType.ERROR );
            }
            setValid( false );
        }
    }
}
