package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/19
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
       {
    "firstname": "Jim",
    "lastname": "ozan",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-01-01",
        "checkout": "2022-01-01"
    },
    "additionalneeds": "kahvaltı"
}
     */

    @Test
    public void get15() {
        spec.pathParams("first","booking","second",19);

        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2022-01-01","2022-01-01");
        BookingPojo expectedData = new BookingPojo("Jim","ozan",111,true,bookingDatesPojo,"kahvaltı");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        //response.prettyPrint();

        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);

        //Hard Assertion
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());

        //soft assertion

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin());
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout());

        softAssert.assertAll();

    }
}
