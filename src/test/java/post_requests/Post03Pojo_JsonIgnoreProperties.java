package post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo_JsonIgnoreProperties extends JsonplaceholderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    //bu testin pojo classında @JsonIgnoreProperties(ignoreUnknown = true) kullandık
    @Test
    public void post03() {
        spec.pathParam("first","todos");

        JsonPlaceHolderPojo expData =new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expData = " + expData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expData).when().post("/{first}");
        response.prettyPrint();

        JsonPlaceHolderPojo actData = response.as(JsonPlaceHolderPojo.class);

        assertEquals(expData.getUserId(),actData.getUserId());
        assertEquals(expData.getTitle(),actData.getTitle());
        assertEquals(expData.getCompleted(),actData.getCompleted());
    }
}
