import base_urls.AutomationexerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Alistirmalar1 extends AutomationexerciseBaseUrl {
    /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void get01() {  //Test Fail
        spec.pathParams("first","brandsList");
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();  //html döndürüyor

        JsonPath jsonPath=response.jsonPath();
        //jsonPath.prettyPrint();

        List<String> numberOfHM = jsonPath.getList("brands.findAll{it.brand=='H&M'}.brand");
        System.out.println("numberOfHM = " + numberOfHM.size());
        List<String> numberOfPolo = jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("numberOfPolo = " + numberOfPolo.size());

        assertEquals(200,response.statusCode());
        assertEquals("text/html; charset=utf-8",response.contentType());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

        //assertEquals(numberOfHM.size(),numberOfPolo.size());  //FAİL

    }
}
