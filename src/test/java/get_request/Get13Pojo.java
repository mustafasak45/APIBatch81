package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestMetaPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void get13() {
        spec.pathParams("first","users","second",2508);

        GoRestDataPojo gorestDataPojo = new GoRestDataPojo(2508,"Sharmila Deshpande VM","deshpande_sharmila_vm@becker.name","female","active");
        GoRestMetaPojo expData = new GoRestMetaPojo(null,gorestDataPojo);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        GoRestMetaPojo actData = response.as(GoRestMetaPojo.class);

        assertEquals(expData.getMeta(),actData.getMeta());
        assertEquals(expData.getData().getId(),actData.getData().getId());
        assertEquals(expData.getData().getName(),actData.getData().getName());
        assertEquals(expData.getData().getEmail(),actData.getData().getEmail());
        assertEquals(expData.getData().getGender(),actData.getData().getGender());
        assertEquals(expData.getData().getStatus(),actData.getData().getStatus());


        assertEquals(gorestDataPojo.getId(),actData.getData().getId());
        assertEquals(gorestDataPojo.getName(),actData.getData().getName());
        assertEquals(gorestDataPojo.getEmail(),actData.getData().getEmail());
        assertEquals(gorestDataPojo.getGender(),actData.getData().getGender());
        assertEquals(gorestDataPojo.getStatus(),actData.getData().getStatus());
    }
}
