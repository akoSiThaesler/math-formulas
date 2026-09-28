package dev.gugel.mathformulas.shape2d.v1.input.validation;

import dev.gugel.mathformulas.shape2d.common.Shape2DinputValidation;
import dev.gugel.mathformulas.shape2d.v1.input.RectangleInput;
import dev.gugel.mathformulas.common.InputValidation;

public class RectangleInputValidation extends Shape2DinputValidation {

    private final RectangleInput rectangleInput;

    public RectangleInputValidation( RectangleInput rectangleInput ) {
        super();
        this.rectangleInput = rectangleInput;
    }

    public RectangleInput getRectangleInput() {
        return rectangleInput;
    }

    @Override
    public void validateInputArea() {
        validateValueNotNullOrNegative( getRectangleInput().a(),RectangleInput.class.getSimpleName() + ".a" );
        validateValueNotNullOrNegative( getRectangleInput().b(),RectangleInput.class.getSimpleName() + ".b" );
    }

    @Override
    public void validateInputPerimeter() {
        validateValueNotNullOrNegative( getRectangleInput().a(),RectangleInput.class.getSimpleName() + ".a" );
        validateValueNotNullOrNegative( getRectangleInput().b(),RectangleInput.class.getSimpleName() + ".b" );
    }
}
