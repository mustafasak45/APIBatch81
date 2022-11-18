package get_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyResponseDataPojo;
import pojos.DummyRestApiDataPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

/*    {
          "status": "success",
            "data": {
                            "id": 1,
                "employee_name": "Tiger Nixon",
                "employee_salary": 320800,
                "employee_age": 61,
                "profile_image": ""
    },
        "message": "Successfully! Record has been fetched."
    }*/

    @Test
    public void get17() {
        spec.pathParams("first","employee","second",1);
        DummyRestApiDataPojo dummyRestApiDataPojo = new DummyRestApiDataPojo("Tiger Nixon",320800,61,"");
        DummyResponseDataPojo expectedData = new DummyResponseDataPojo("success",dummyRestApiDataPojo,"Successfully! Record has been fetched.");

        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        response.prettyPrint();

        DummyResponseDataPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyResponseDataPojo.class);

        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        assertEquals(dummyRestApiDataPojo.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(dummyRestApiDataPojo.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(dummyRestApiDataPojo.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(dummyRestApiDataPojo.getProfile_image(),actualData.getData().getProfile_image());

    }
}
