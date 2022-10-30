package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get04b extends RestfulBaseUrl {

 /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"
   //firstname,lastname console de gelmedigi için kontrolunu yapmadaık

 */

    @Test
    public void test04(){
        spec.pathParam("first","booking").queryParams("firstname","Almedin","lastname","Alikadic");

        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("booking"));

    }

}
