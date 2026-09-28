package dev.gugel.mathformulas.shape2d.v1.calc;

import dev.gugel.mathformulas.common.Formulas;
import dev.gugel.mathformulas.shape2d.common.Shape2Dcalc;
import dev.gugel.mathformulas.shape2d.v1.input.TriangleInput;
import dev.gugel.mathformulas.shape2d.v1.output.Shape2Doutput;

public class TriangleCalc implements Shape2Dcalc {

    private final TriangleInput triangleInput;

    public TriangleCalc( TriangleInput triangleInput ) {
        this.triangleInput = triangleInput;
    }

    private TriangleInput getTriangleInput() {
        return triangleInput;
    }

    @Override
    public Shape2Doutput calculateAllValues() {
        return new Shape2Doutput( calculateArea().area(), calculatePerimeter().perimeter(), null );
    }

    @Override
    public Shape2Doutput calculateArea() {
        return new Shape2Doutput( Formulas.calculateTriangleArea( getTriangleInput().a(),
                getTriangleInput().b(), getTriangleInput().c() ), null, null );
    }

    @Override
    public Shape2Doutput calculatePerimeter() {
        return new Shape2Doutput( null, Formulas.calculateTrianglePerimeter( getTriangleInput().a(),
                getTriangleInput().b(), getTriangleInput().c() ), null );
    }
}
