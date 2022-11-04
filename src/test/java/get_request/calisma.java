package get_request;

import base_urls.GoRestBaseUrl;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.calismaTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class calisma extends GoRestBaseUrl {
 /*
{
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
            "avatar": "https://reqres.in/img/faces/1-image.jpg"
        },
        {
            "id": 2,
            "email": "janet.weaver@reqres.in",
            "first_name": "Janet",
            "last_name": "Weaver",
            "avatar": "https://reqres.in/img/faces/2-image.jpg"
        },
	.
	.
	.(burada aşağıya doğru bodyler devam ediyor)
}

*/

    @Test
    public void get01(){
        String url ="https://reqres.in/api/users";

        Response response=given().when().get(url);
       // response.prettyPrint();

        Map<String,Object> actData = response.as(HashMap.class);

        System.out.println(actData);





    }
}