package dev.gugel.mathformulas.shape2d.common;

import dev.gugel.mathformulas.common.InputValidation;

public abstract class Shape2DinputValidation extends InputValidation {

    /**
     * boolean "valid" is true if input for area is valid
     */
    public abstract void validateInputArea();

    /**
     * boolean "valid" is true if input for perimeter is valid
     */
    public abstract void validateInputPerimeter();

    /**
     * boolean "valid" is true if input for all output values is valid
     */
    public void validateInputAllValues() {
        validateInputArea();
        validateInputPerimeter();
    }
}
