package post_requests;

import base_urls.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo_JsonIgnoreProperties extends RestfulBaseUrl {
    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */

    //bu testin pojo classında @JsonIgnoreProperties(ignoreUnknown = true) kullandık
    @Test
    public void post04() {
        spec.pathParam("first","booking");

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo expData = new BookingPojo("Ali","Can",999,true,bookingDatesPojo,"Breakfast with Dragon Juice");

        Response response= given().spec(spec).contentType(ContentType.JSON).body(expData).when().post("/{first}");
        response.prettyPrint();

        BookingResponseBodyPojo actData =response.as(BookingResponseBodyPojo.class);//Burayı bodysi en geniş kapsamda olan
                                                                                    //constructor'a sahip olan pojo class
                                                                                    //tan create ettik. Bu sekilde @JsonIgnoreProperties(ignoreUnknown = true)
                                                                                    //kullanabildik
        assertEquals(expData.getFirstname(),actData.getBooking().getFirstname());
        assertEquals(expData.getLastname(),actData.getBooking().getLastname());
        assertEquals(expData.getTotalprice(),actData.getBooking().getTotalprice());
        assertEquals(expData.getDepositpaid(),actData.getBooking().getDepositpaid());

        assertEquals(bookingDatesPojo.getCheckin(),actData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actData.getBooking().getBookingdates().getCheckout());

        assertEquals(expData.getAdditionalneeds(),actData.getBooking().getAdditionalneeds());
    }
}
