package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyResponseDataPojo;
import pojos.DummyRestApiDataPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }

    Given
    URL: https://dummy.restapiexample.com/api/v1/create
                            {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        }
         When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    @Test
    public void post06() {
        spec.pathParam("first","create");

        DummyRestApiDataPojo dummyRestApiDataPojo = new DummyRestApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyResponseDataPojo expectedData = new DummyResponseDataPojo("success",dummyRestApiDataPojo,"Successfully! Record has been added.");

        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyRestApiDataPojo).when().post("/{first}");
        response.prettyPrint();

     //   DummyResponseDataPojo actualData = response.as(DummyResponseDataPojo.class);
        DummyResponseDataPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyResponseDataPojo.class);


        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(dummyRestApiDataPojo.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(dummyRestApiDataPojo.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(dummyRestApiDataPojo.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(dummyRestApiDataPojo.getProfile_image(),actualData.getData().getProfile_image());


    }
}
