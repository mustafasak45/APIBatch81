package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class zoom {
/*
    Given
            https://restful-booker.herokuapp.com/booking/101
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01() {

    String url="https://restful-booker.herokuapp.com/booking/101";
    Response response=given().when().get(url);

    response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");

    }

}
