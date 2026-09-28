package dev.gugel.mathformulas.shape2d.common;

import dev.gugel.mathformulas.shape2d.v1.output.Shape2Doutput;

public interface Shape2Dresources<I, O> {

    Shape2Doutput getAllValues( I inputSI );

    Shape2Doutput getArea(I inputSI );

    Shape2Doutput getPerimeter( I inputSI );

    String getDocumentation();

    I getInputDummy();
}
