package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends RestfulBaseUrl {
    /*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like:
                       {
                         "firstname": "Dane",
                         "lastname": "Combs",
                         "totalprice": 111,
                         "depositpaid": true,
                         "bookingdates": {
                             "checkin": "2018-01-01",
                             "checkout": "2019-01-01"
                         },
                         "additionalneeds": "Breakfast"
                }
  */

    @Test
    public void get12Pojo() {
        spec.pathParams("first","booking","second",18);

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
       // System.out.println(bookingDatesPojo);

        BookingPojo expData = new BookingPojo("Dane","Combs",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expData = " + expData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        BookingPojo actData = response.as(BookingPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(expData.getFirstname(),actData.getFirstname());
        assertEquals(expData.getLastname(),actData.getLastname());
        assertEquals(expData.getTotalprice(),actData.getTotalprice());
        assertEquals(expData.getDepositpaid(),actData.getDepositpaid());
        assertEquals(expData.getAdditionalneeds(),actData.getAdditionalneeds());

        //1. yol inner için;
        assertEquals(expData.getBookingdates().getCheckin(),actData.getBookingdates().getCheckin());
        assertEquals(expData.getBookingdates().getCheckout(),actData.getBookingdates().getCheckout());

        //2. yol inner için;
        assertEquals(bookingDatesPojo.getCheckin(),actData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actData.getBookingdates().getCheckout());


    }
}
