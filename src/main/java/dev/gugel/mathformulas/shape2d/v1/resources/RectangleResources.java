package dev.gugel.mathformulas.shape2d.v1.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.gugel.mathformulas.shape2d.common.Shape2DinputValidation;
import dev.gugel.mathformulas.shape2d.common.Shape2Dresources;
import dev.gugel.mathformulas.shape2d.common.Shape2Dcalc;
import dev.gugel.mathformulas.shape2d.v1.calc.RectangleCalc;
import dev.gugel.mathformulas.shape2d.v1.input.RectangleInput;
import dev.gugel.mathformulas.shape2d.v1.input.validation.RectangleInputValidation;
import dev.gugel.mathformulas.shape2d.v1.output.Shape2Doutput;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path( "/v1" )
public class RectangleResources implements Shape2Dresources<RectangleInput, Shape2Doutput> {

    @RolesAllowed( "restUser" )
    @Path( "/rectangle" )
    @POST
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @Override
    public Shape2Doutput getAllValues(RectangleInput inputSI )
    {
        Shape2DinputValidation rectangleInputValidation = new RectangleInputValidation( inputSI );
        rectangleInputValidation.validateInputAllValues();
        if( rectangleInputValidation.isValid() ) {
            Shape2Dcalc rectangleCalc = new RectangleCalc( inputSI );
            return rectangleCalc.calculateAllValues();
        } else {
            return new Shape2Doutput(null,null, rectangleInputValidation.getMessageOutputList() );
        }
    }

    @RolesAllowed( "restUser" )
    @Path( "/rectangle/area" )
    @POST
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @Override
    public Shape2Doutput getArea(RectangleInput inputSI )
    {
        Shape2DinputValidation rectangleInputValidation = new RectangleInputValidation( inputSI );
        rectangleInputValidation.validateInputArea();
        if( rectangleInputValidation.isValid() ) {
            Shape2Dcalc rectangleCalc = new RectangleCalc( inputSI );
            return rectangleCalc.calculateArea();
        } else {
            return new Shape2Doutput(null,null, rectangleInputValidation.getMessageOutputList() );
        }
    }

    @RolesAllowed( "restUser" )
    @Path( "/rectangle/perimeter" )
    @POST
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @Override
    public Shape2Doutput getPerimeter(RectangleInput inputSI )
    {
        Shape2DinputValidation rectangleInputValidation = new RectangleInputValidation( inputSI );
        rectangleInputValidation.validateInputPerimeter();
        if( rectangleInputValidation.isValid() ) {
            Shape2Dcalc rectangleCalc = new RectangleCalc( inputSI );
            return rectangleCalc.calculatePerimeter();
        } else {
            return new Shape2Doutput(null,null, rectangleInputValidation.getMessageOutputList() );
        }
    }

    @RolesAllowed( "restUser" )
    @Path( "/rectangle/doc" )
    @GET
    @Produces( MediaType.TEXT_PLAIN )
    @Override
    public String getDocumentation()
    {
        RectangleInput rectangleInput = getInputDummy();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson( rectangleInput );

        return """
                ########################
                Rectangle Input
                ########################

                REST
                'Content-Type: application/json'
                'Basic: cmVzdFVzZXI6Y2hhbmdlaXQ='
                
                ########################

                Input parameters as SI units:

                <description>   <formula symbol> : <unit>   (<abbreviation>)   as <data type>

                length          a                : metre    (m)                as Double
                width           b                : metre    (m)                as Double

                ########################

                Input JSON Objekt:

                """ + jsonString;
    }

    @Override
    public RectangleInput getInputDummy()
    {
        return new RectangleInput( 10.0, 5.0 );
    }
}
