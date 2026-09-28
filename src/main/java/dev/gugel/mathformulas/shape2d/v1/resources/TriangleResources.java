package dev.gugel.mathformulas.shape2d.v1.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.gugel.mathformulas.shape2d.common.Shape2DinputValidation;
import dev.gugel.mathformulas.shape2d.common.Shape2Dresources;
import dev.gugel.mathformulas.shape2d.common.Shape2Dcalc;
import dev.gugel.mathformulas.shape2d.v1.calc.TriangleCalc;
import dev.gugel.mathformulas.shape2d.v1.input.TriangleInput;
import dev.gugel.mathformulas.shape2d.v1.input.validation.TriangleInputValidation;
import dev.gugel.mathformulas.shape2d.v1.output.Shape2Doutput;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path( "/v1" )
public class TriangleResources implements Shape2Dresources<TriangleInput, Shape2Doutput> {

    @RolesAllowed( "restUser" )
    @Path( "/triangle" )
    @POST
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @Override
    public Shape2Doutput getAllValues( TriangleInput inputSI )
    {
        Shape2DinputValidation triangleInputValidation = new TriangleInputValidation( inputSI );
        triangleInputValidation.validateInputAllValues();
        if( triangleInputValidation.isValid() ) {
            Shape2Dcalc triangleCalc = new TriangleCalc( inputSI );
            return triangleCalc.calculateAllValues();
        } else {
            return new Shape2Doutput( null, null, triangleInputValidation.getMessageOutputList() );
        }
    }

    @RolesAllowed( "restUser" )
    @Path( "/triangle/area" )
    @POST
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @Override
    public Shape2Doutput getArea( TriangleInput inputSI )
    {
        Shape2DinputValidation triangleInputValidation = new TriangleInputValidation( inputSI );
        triangleInputValidation.validateInputArea();
        if( triangleInputValidation.isValid() ) {
            Shape2Dcalc triangleCalc = new TriangleCalc( inputSI );
            return triangleCalc.calculateArea();
        } else {
            return new Shape2Doutput( null, null, triangleInputValidation.getMessageOutputList() );
        }
    }

    @RolesAllowed( "restUser" )
    @Path( "/triangle/perimeter" )
    @POST
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    @Override
    public Shape2Doutput getPerimeter( TriangleInput inputSI )
    {
        Shape2DinputValidation triangleInputValidation = new TriangleInputValidation( inputSI );
        triangleInputValidation.validateInputPerimeter();
        if( triangleInputValidation.isValid() ) {
            Shape2Dcalc triangleCalc = new TriangleCalc( inputSI );
            return triangleCalc.calculatePerimeter();
        } else {
            return new Shape2Doutput( null, null, triangleInputValidation.getMessageOutputList() );
        }
    }

    @RolesAllowed( "restUser" )
    @Path( "/triangle/doc" )
    @GET
    @Produces( MediaType.TEXT_PLAIN )
    @Override
    public String getDocumentation()
    {
        TriangleInput triangleInput = getInputDummy();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson( triangleInput );

        return """
                ########################
                Triangle Input
                ########################

                REST
                'Content-Type: application/json'
                'Authorization: Basic cmVzdFVzZXI6Y2hhbmdlaXQ='

                ########################

                Input parameters as SI units:

                <description>   <formula symbol> : <unit>   (<abbreviation>)   as <data type>

                side a          a                : metre    (m)                as Double
                side b          b                : metre    (m)                as Double
                side c          c                : metre    (m)                as Double

                The sides must form a triangle (triangle inequality):
                a + b > c, a + c > b and b + c > a, otherwise the output holds TriangleInput.sides.isInvalid

                ########################

                Input JSON Objekt:

                """ + jsonString;
    }

    @Override
    public TriangleInput getInputDummy()
    {
        return new TriangleInput( 3.0, 4.0, 5.0 );
    }
}
