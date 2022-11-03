package get_request;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get02b_BodyBosOldugunuKontrolEtme extends ReqresBaseUrl {
      /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty
*/

    @Test
    public void get02b(){


        spec.pathParams("first","users","second",23);

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        assertEquals(404,response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        assertEquals("cloudflare",response.header("Server"));
        assertEquals(2,response.asString().replaceAll("\\s","").length());//response'yi String'e cevirdik
        //assertEquals(0,response.asString().replaceAll("[^A-Za-z0-9]","").length());
    }
}
