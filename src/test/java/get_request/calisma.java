package get_request;

import base_urls.DummyRestApiBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class calisma extends DummyRestApiBaseUrl {
 /*
 Given
           URL: https://dummy.restapiexample.com/api/v1/employees
 When
           Users sends GET Request
Then
            i) Status code is 200
And
            ii) There are 24 employees
And
            iii) "Tiger Nixon" and "Garrett Winters" are among the employees
And
            iv) The greatest age is 66
And
            v) The name of the lowest age is "Tatyana Fitzpatrick"
And
            vi) Total salary of all employees is 6,644,770
    */

    @Test
    public void get01(){
        spec.pathParam("first","employees");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                body("data.id",hasSize(24),
                        "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        List<Integer> employee_ageList = response.jsonPath().getList("data.findAll{it.employee_age}.employee_age");
        Collections.sort(employee_ageList);
        System.out.println("employee_ageList = " + employee_ageList);
        assertEquals(66,(int)employee_ageList.get(employee_ageList.size()-1));
        String lowestAgeOfName = response.jsonPath().getString("data.findAll{it.employee_age=="+employee_ageList.get(0)+"}.employee_name");
        System.out.println("lowestAge = " + lowestAgeOfName);
        assertEquals("[Tatyana Fitzpatrick]",lowestAgeOfName);


    }
}