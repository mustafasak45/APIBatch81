package get_request;
import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10Json extends GoRestBaseUrl {
    /*
  Given
      https://gorest.co.in/public/v1/users/2986
  When
      User send GET Request to the URL
  Then
      Status Code should be 200
  And
      Response body should be like
   {
  "meta": null,
  "data": {
        "id": 2986,
       "name": "Kanaka Jain",
       "email": "kanaka_jain@stark.net",
       "gender": "male",
       "status": "active"
           }
    }
*/
    @Test
    public void get10() {
//Set the Url
        spec.pathParams("first", "users", "second", 2986);
//Set The Expected Data
//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();
//Do Assertion
        assertEquals(null, jsonPath.getString("meta"));
        assertEquals("Kanaka Jain", jsonPath.getString("data.name"));
        assertEquals("kanaka_jain@stark.net", jsonPath.getString("data.email"));
        assertEquals("male", jsonPath.getString("data.gender"));
        assertEquals("active", jsonPath.getString("data.status"));
        assertEquals(response.statusCode(), 200);
    }
}