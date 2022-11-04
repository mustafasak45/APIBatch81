package post_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulBookerTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/
    @Test
    public void post02(){
        spec.pathParam("first","booking");

        RestfulBookerTestData obj = new RestfulBookerTestData();
        Map<String ,String> expBookingdatesMap = obj.expBookingdatesMethod("2021-09-21","2021-09-21");
        Map<String,Object> expDataMap = obj.expDataMethod("John","Doe",11111,true,expBookingdatesMap);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expDataMap).when().post("/{first}");
        response.prettyPrint();

        Map<String,Object> actDataMap = response.as(HashMap.class);                 //  serialization

        assertEquals(expDataMap.get("firstname"),((Map)actDataMap.get("booking")).get("firstname"));
        assertEquals(expDataMap.get("lastname"),((Map)actDataMap.get("booking")).get("lastname"));
        assertEquals(expDataMap.get("totalprice"),((Map)actDataMap.get("booking")).get("totalprice"));
        assertEquals(expDataMap.get("depositpaid"),((Map)actDataMap.get("booking")).get("depositpaid"));
        assertEquals(expBookingdatesMap.get("checkin"),((Map)((Map)actDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expBookingdatesMap.get("checkout"),((Map)((Map)actDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }
}
