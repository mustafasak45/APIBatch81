package get_request;


import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Pres. Amarnath Dhawan, Sujata Chaturvedi and Navin Panicker are among the users
    And
        The female users are less than or equals to male users
 */

    @Test
    public void get11() {
        spec.pathParam("first","users");

        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

//1. Yöntem :
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                       "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data",hasSize(10),
                        "data.status",hasItem("active"),//hasItem contains gibi calisir
                        "data.name",hasItems("Bhadran Mehra LLD","Sujata Chaturvedi","Navin Panicker"));

            //1.Yol: JAVA Kullanarak

       List<String> genders= response.jsonPath().getList("data.gender");
        //System.out.println(genders);
       int numFemale = 0;
        for (String w : genders){
            if (w.equalsIgnoreCase("numFemale"))
            {
                numFemale++;
            }
        }
        Assert.assertTrue(numFemale<=genders.size()-numFemale);

            //2.Yol: Kadın ve erkak sayılarını Groovy ile bulalım.

        List<String> femaleNames=response.jsonPath().getList("data.findAll{it.gender=='female'}.name");//gender'i female olanların adlarını liste attık
        List<String> maleNames=response.jsonPath().getList("data.findAll{it.gender=='male'}.name");//gender'i male olanların adlarını liste attık
        assertTrue(femaleNames.size()<=maleNames.size());



//2. Yöntem :
        JsonPath jsonPath=response.jsonPath();

        assertEquals(10,jsonPath.getInt("meta.pagination.limit"));
        assertEquals("https://gorest.co.in/public/v1/users?page=1",jsonPath.getString("meta.pagination.links.current"));
        assertEquals(10,jsonPath.getList("data").size());
//        assertEquals(1,"data.findAll{it.name=='Sujata Chaturvedi'}".length());
//        assertTrue(jsonPath.getList("data").size()/2<="data.findAll{it.gender=='male'}".length());

    }
}
