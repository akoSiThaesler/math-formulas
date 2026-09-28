package dev.gugel.mathformulas.shape2d.v1.resources;

import dev.gugel.mathformulas.shape2d.v1.input.TriangleInput;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
class TriangleResourcesTest {

    private TriangleInput triangleInput;

    private final String user = "restUser";
    private final String pw = "changeit";

    private final String urlAllValues = "/v1/triangle";

    private final String urlDoc = "/v1/triangle/doc";

    private final String urlArea = "/v1/triangle/area";
    private final String outputKeyArea = "area";

    private final String urlPerimeter = "/v1/triangle/perimeter";
    private final String outputKeyPerimeter = "perimeter";

    private final String outputKeyMessages = "messageOutputList.message";

    @BeforeEach
    void setUp() {
        triangleInput = new TriangleInput( 3.0, 4.0, 5.0 );
    }

    @Test
    void getAllValuesUnauthorized() {
        given()
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 401 );
    }

    @Test
    void getAllValues() {
        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( 6.0f ) )
                .body( outputKeyPerimeter, equalTo( 12.0f ) );
    }

    @Test
    void getAllValuesFailedByNull() {
        triangleInput = new TriangleInput( null, 4.0, 5.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( null ) )
                .body( outputKeyPerimeter, equalTo( null ) )
                .body( outputKeyMessages, hasItem( "TriangleInput.a.isNull" ) );
    }

    @Test
    void getAllValuesFailedByNegative() {
        triangleInput = new TriangleInput( 3.0, -4.0, 5.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( null ) )
                .body( outputKeyPerimeter, equalTo( null ) )
                .body( outputKeyMessages, hasItem( "TriangleInput.b.isNegativeOrZero" ) );
    }

    @Test
    void getAllValuesFailedByTriangleInequality() {
        triangleInput = new TriangleInput( 1.0, 2.0, 10.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( null ) )
                .body( outputKeyPerimeter, equalTo( null ) )
                .body( outputKeyMessages, hasItem( "TriangleInput.sides.isInvalid" ) );
    }

    @Test
    void getAllValuesFailedByMissingBody() {
        given().auth().basic( user, pw )
                .contentType( ContentType.JSON )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyMessages, hasItem( "TriangleInput.isNull" ) );
    }

    @Test
    void getArea() {
        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlArea ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( 6.0f ) )
                .body( outputKeyPerimeter, equalTo( null ) );
    }

    @Test
    void getAreaFailedByDegenerateTriangle() {
        triangleInput = new TriangleInput( 1.0, 2.0, 3.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlArea ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( null ) )
                .body( outputKeyMessages, hasItem( "TriangleInput.sides.isInvalid" ) );
    }

    @Test
    void getPerimeter() {
        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlPerimeter ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyPerimeter, equalTo( 12.0f ) )
                .body( outputKeyArea, equalTo( null ) );
    }

    @Test
    void getPerimeterFailedByNull() {
        triangleInput = new TriangleInput( 3.0, 4.0, null );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( triangleInput )
                .when().post( urlPerimeter ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyPerimeter, equalTo( null ) )
                .body( outputKeyMessages, hasItem( "TriangleInput.c.isNull" ) );
    }

    @Test
    void getDocumentation() {
        given().auth().basic( user, pw )
                .when().get( urlDoc ).then()
                .statusCode( 200 )
                .contentType( ContentType.TEXT )
                .body( containsString( "Triangle Input" ) );
    }
}
