package get_request;

import base_urls.AutomationexerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class odev extends AutomationexerciseBaseUrl {
    /*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */
    @Test
    public void get01(){
        spec.pathParam("first","productsList");
        Response response=given().spec(spec).when().get("/{first}");
        //response.prettyPrint();  // bu satırı yazıdırırsak html formatında oldugundan dolayı bizim çıktımızda
                                    // JSON istediğinden FAIL VERİR

        JsonPath jsonPath=response.jsonPath();
        jsonPath.prettyPrint();

        assertEquals(200,response.statusCode());
        assertEquals("text/html; charset=utf-8",response.contentType());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

        List<String> women=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        List<String> men=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String> kids=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");


        assertEquals(12,women.size());
        assertEquals(9,men.size());
        assertEquals(13,kids.size());
    }
    @Test  //sorunun 2 cozum yontemi
    public void get02(){
        spec.pathParams("first","productsList");
        Response response=given().spec(spec).when().get("/{first}");

        assertEquals(200,response.statusCode());
        assertEquals("text/html; charset=utf-8",response.contentType());
        assertEquals("HTTP/1.1 200 OK",response.statusLine());

        JsonPath jsonPath=response.jsonPath();

        List<String> usertype=jsonPath.getList("products.category.usertype.usertype");

        assertEquals(12,usertype.stream().filter(t->t.equals("Women")).count());
        assertEquals(9,usertype.stream().filter(t->t.equals("Men")).count());
        assertEquals(13,usertype.stream().filter(t->t.equals("Kids")).count());
    }
}
