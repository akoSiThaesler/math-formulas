package dev.gugel.mathformulas.shape2d.v1.calc;

import dev.gugel.mathformulas.common.Formulas;
import dev.gugel.mathformulas.shape2d.common.Shape2Dcalc;
import dev.gugel.mathformulas.shape2d.v1.input.RectangleInput;
import dev.gugel.mathformulas.shape2d.v1.output.Shape2Doutput;

public class RectangleCalc implements Shape2Dcalc {

    private final RectangleInput rectangleInput;

    public RectangleCalc(RectangleInput rectangleInput ) {
        this.rectangleInput = rectangleInput;
    }

    private RectangleInput getRectangleInput() {
        return rectangleInput;
    }

    @Override
    public Shape2Doutput calculateAllValues() {
        return new Shape2Doutput( calculateArea().area(), calculatePerimeter().perimeter(), null );
    }

    @Override
    public Shape2Doutput calculateArea() {
        return new Shape2Doutput( Formulas.calculateRectangleArea( getRectangleInput().a(),
                getRectangleInput().b() ),null,null );
    }

    @Override
    public Shape2Doutput calculatePerimeter() {
        return new Shape2Doutput(null, Formulas.calculateRectanglePerimeter( getRectangleInput().a(),
                getRectangleInput().b() ),null );
    }
}
