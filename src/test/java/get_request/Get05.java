package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Ali" and lastname is "Cengiz"
 */

    @Test
    public void get01() {
        //https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz

        //1. Set The URL
        spec.pathParam("first","booking").//burada parent classta olan urlye taskte verilen url gibi olsundiye booking ekledik
            queryParams("firstname","Ali","lastname","Cengiz");

       //2. Set The Expected Data

       //3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

       //4. Do Assertion
        //response.then().assertThat().statusCode(200); // alttaki kodla aynısını yapar fakat alttakini kullanılmalıymış
         assertEquals(200,response.getStatusCode());
         assertTrue(response.asString().contains("bookingid"));

            /* çıktısı:
                         [
                                 {
                                     "bookingid": 2299
                                 }
                         ]
            */


    }
}
