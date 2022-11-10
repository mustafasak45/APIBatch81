package post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_MapDinamik extends JsonplaceholderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
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

    @Test
    public void post05ObjectMapper() throws IOException {
        spec.pathParam("first","todos");
/*

        String jsonInString = "{\n" +
                "                                    \"userId\": 55,\n" +
                "                                    \"title\": \"Tidy your room\",\n" +
                "                                    \"completed\": false,\n" +
                "                                    \"id\": 201\n" +
                "                                    }";
*/
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData(); //olusturulan obje ile ObjectMapper()'a gonderecek
                                                                    //oldugumuz stringi dinamik yaptık bir alt satırda o
                                                                    //methodu call yaptık
        String jsonInString = obj.expectedDataInString(55,"Tidy your room",false);

        // Map<String,Object> expData = new ObjectMapper().readValue(jsonInString, HashMap.class); //kodu ilk bu satırdaki gibi yazdık
                                                                                                    //İntelij bize asagıdaki gibi yazılabileceğini önerdi
        HashMap expData = new ObjectMapper().readValue(jsonInString, HashMap.class);//ObjectMapper().readValue() içine
                                                                                // girilen String jsonInString variable'ini
                                                                                //HashMap' e çevirir.

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expData).when().post("/{first}");
        response.prettyPrint();

        HashMap actData = new ObjectMapper().readValue(response.asString(),HashMap.class);

        assertEquals(201,response.getStatusCode());
        assertEquals(expData.get("title"),actData.get("title"));
        assertEquals(expData.get("completed"),actData.get("completed"));
        assertEquals(expData.get("userId"),actData.get("userId"));
    }
}
