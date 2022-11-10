package delete_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01 extends JsonplaceholderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */


    @Test
    public void delete01() {
        spec.pathParams("first","todos","second",198);

        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        Map<String,String> expectedData = new HashMap<>();

        Map actData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(200,response.getStatusCode());

        //1. Yöntem:
        assertEquals(expectedData,actData);

        //2. Yöntem:
        assertTrue(actData.isEmpty());

        //3. Yöntem:
        assertEquals(0,actData.size());

    }
}
