package patch_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonplaceholderBaseUrl { // body nin belirtilen kısmında değişiklik yapar
     /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "title": "Wash the dishes"
           }
    When
I send PATCH Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 10,
                   "title": "Wash the dishes",
                   "completed": true,
                   "id": 198
                   }
 */

    @Test
    public void patch01() {
        spec.pathParams("first","todos","second","198");


        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();

        Map<String,Object> expData = obj.expectedDataMethod(null,"Wash the dishes",null);
        System.out.println(expData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expData).when().patch("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expData.get("title"),actualData.get("title"));
    }
}
