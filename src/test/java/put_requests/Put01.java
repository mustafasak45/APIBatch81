package put_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonplaceholderBaseUrl { //put body nin tamamını değiştirir
      /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									    "id": 198
									   }
									        */

    @Test
    public void put01() {
//Set the URL (base URL yi parent classdaki spec ile çagirip parametreleri yazıyoruz)
        spec.pathParams("first","todos","second",198);

//Set the Expected (Bize taskte verilen verilerin girişinin yapılması; Bunun için test_data package'i altındaki daha önce olusturulan classdaki method kullanılır)
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expData = obj.expectedDataMethod(21,"Wash the dishes",false);

//Send the Request and Get the Response (Url'deki gerçek verileri Response'ye alıyoruz)
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expData).when().put("/{first}/{second}");
        response.prettyPrint();

//Do Assert (Yukarıda oluşturduğumuz responseyi karşılastırma yapmak için Response'den HasMap formatına cevirerek Map'e atıyoruz)
        Map<String,Object> actData = response.as(HashMap.class);

        assertEquals(expData.get("userId"),actData.get("userId"));
        assertEquals(expData.get("title"),actData.get("title"));
        assertEquals(expData.get("completed"),actData.get("completed"));

    }
}
