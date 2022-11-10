package get_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Map extends JsonplaceholderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */


    @Test
    public void get14Map() {
 //Set the Url
        spec.pathParams("first","todos","second",198);

 //Set the Expected Data

       // JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();  //   40. satır 38-39'un aynısını yapar
       // String expectedDataInString = obj.expectedDataInString(10,"quis eius est sint explicabo",true);
        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);

       // Map<String,Object> asd = new ObjectMapper().readValue(expectedDataInString,Map.class);
        Map expectedData =  ObjectMapperUtils.convertJsonToJava(expectedDataInString, Map.class);

 //Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

 //Do Assertion
        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),Map.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }
}
