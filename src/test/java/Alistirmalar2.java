import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Alistirmalar2 extends ReqresBaseUrl {
    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                 "name": "morpheus",
                                                 "job": "leader",
                                                 "id": "913",
                                                 "createdAt": "2022-11-08T16:27:47.964Z"
                                                }
*/

    @Test
    public void testOfMap() {
        spec.pathParam("first","users");

        RegresTestData obj = new RegresTestData();
        Map<String,String> expectedData = obj.expectedData("morpheus","leader");

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String,String> actualData = response.as(HashMap.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));
    }

    @Test
    public void testOfPojo() {
        spec.pathParam("first","users");

    }
}
