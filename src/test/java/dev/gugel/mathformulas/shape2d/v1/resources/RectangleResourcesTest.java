package dev.gugel.mathformulas.shape2d.v1.resources;

import dev.gugel.mathformulas.shape2d.v1.input.RectangleInput;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class RectangleResourcesTest {

    private RectangleInput rectangleInput;

    private final String user = "restUser";
    private final String pw = "changeit";

    private final String urlAllValues = "/v1/rectangle";

    private final String urlDoc = "/v1/rectangle/doc";

    private final String urlArea = "/v1/rectangle/area";
    private final String outputKeyArea = "area";

    private final String urlPerimeter = "/v1/rectangle/perimeter";
    private final String outputKeyPerimeter = "perimeter";

    @BeforeEach
    void setUp() {
        rectangleInput = new RectangleInput( 10.0, 5.0 );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllValuesUnauthorized() {
        given()
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 401 );
    }

    @Test
    void getAllValues() {
        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( 50.0f ) )
                .body( outputKeyPerimeter, equalTo( 30.0f ) );
    }

    @Test
    void getAllValuesFailedByNull() {
        rectangleInput = new RectangleInput( null, 5.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( null ) )
                .body( outputKeyPerimeter, equalTo( null ) );
    }

    @Test
    void getAllValuesFailedByNegative() {
        rectangleInput = new RectangleInput( 10.0, -5.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlAllValues ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( null ) )
                .body( outputKeyPerimeter, equalTo( null ) );
    }

    @Test
    void getArea() {
        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlArea ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( 50.0f ) );
    }

    @Test
    void getAreaFailedByNull() {
        rectangleInput = new RectangleInput( null, 5.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlArea ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyArea, equalTo( null ) );
    }

    @Test
    void getPerimeter() {
        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlPerimeter ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyPerimeter, equalTo( 30.0f ) );
    }

    @Test
    void getPerimeterFailedByNull() {
        rectangleInput = new RectangleInput( 10.0, -5.0 );

        given().auth().basic( user, pw )
                .contentType( ContentType.JSON ).body( rectangleInput )
                .when().post( urlPerimeter ).then()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body( outputKeyPerimeter, equalTo( null ) );
    }

    @Test
    void getDocumentation() {
        given().auth().basic( user, pw )
                .when().get( urlDoc ).then()
                .statusCode( 200 )
                .contentType( ContentType.TEXT );
    }
}