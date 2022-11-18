package get_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16_findAll_bodyIcindenVeriCekme extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
     /*
    Given
        https://dummy.restapiexample.com/api/v1/employees

    When
        User sends Get request

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
    public void get10() {

        spec.pathParam("first","employees");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().
                statusCode(200).
                assertThat().
                body("data.id",hasSize(24),         //tek body içinde yapılınca soft assert olur
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

      /*     // employee_name; "Tiger Nixon","Garrett Winters" olanlari süzdürmek için kendi yazıdıgım kod

        List<String> employee_name = response.jsonPath().getList("data.findAll{(it.employee_name=='Tiger Nixon') || (it.employee_name=='Garrett Winters')}.employee_name");
        System.out.println("employee_name = " + employee_name);

        */

        List<Integer> age = response.jsonPath().getList("data.id");
        System.out.println("age.size() = " + age.size());

//   iv) The greatest age is 66
        List<Integer> ages= response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("Sourted Ages: "+ages);
        //System.out.println(ages.get(ages.size()-1));
        assertEquals(66,(int)(ages.get(ages.size()-1)));

//  v) The name of the lowest age is "Tatyana Fitzpatrick"
        //String lowestAgedEmployee=response.jsonPath().getList("data.findAll{it.employee_age==" + ages.get(0) + "}").toString();
        String lowestAgedEmployee=response.jsonPath().getString("data.findAll{it.employee_age==" + ages.get(0) + "}.employee_name");
        System.out.println("lowestAgedEmployee = "+lowestAgedEmployee);
        assertEquals("[Tatyana Fitzpatrick]",lowestAgedEmployee);


// vi) Total salary of all employees is 6,644,770
        List<Integer> salaries = response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);

        //1. Yol
        int sum1 = 0 ;
        for (int w :salaries) {
            sum1+=w;
        }
        assertEquals(6644770,sum1);

        //2. Yol
        int sum2 = salaries.stream().reduce(0,(t,u)->t+u);
        System.out.println("sum = " + sum2);
        assertEquals(6644770,sum2);

        //3. Yol
        int sum3 = salaries.stream().reduce(0, Integer::sum);
        System.out.println("sum = " + sum3);
        assertEquals(6644770,sum3);

        //4. Yol
        int sum4 = salaries.stream().reduce(0,Math::addExact);
        System.out.println("sum = " + sum4);
        assertEquals(6644770,sum4);

    }
}
