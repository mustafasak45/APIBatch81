package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01b {
    /*
   Given                                        //gherkin language denir buraya
       https://reqres.in/api/users/3
   When                                         //gherkin language denir buraya
       User sends a GET Request to the url
   Then                                         //gherkin language denir buraya
       HTTP Status Code should be 200
   And                                          //gherkin language denir buraya
       Content Type should be JSON
   And                                          //gherkin language denir buraya
       Status Line should be HTTP/1.1 200 OK
*/

    @Test
    public void get01(){
        String url = "https://reqres.in/api/users/3";

        Response response=given().when().get(url);
        //response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }
}
