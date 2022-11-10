package get_request;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get06b_ArrayIcindenVeriCekme extends ReqresBaseUrl {
     /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
*/
    @Test
    public void test01(){
        spec.pathParam("first","unknown");

        Response response=given().spec(spec).when().get("/{first}");

        response.prettyPrint();
       // response.then().body("data",hasSize(6)); body deki data'nın altında çıkan arraylerin sayısını verir

        assertEquals(200,response.statusCode());

        JsonPath jsonPath=response.jsonPath();

//        1)Status code is 200
        assertEquals(200,response.getStatusCode());

//        2)Print all pantone_values()
        System.out.println(jsonPath.getList("data.pantone_value")); //[15-4020, 17-2031, 19-1664, 14-4811, 17-1456, 15-5217]

//        3)Print all ids greater than 3 on the console
        System.out.println("TUM id'ler = "+jsonPath.getList("data.id"));

        System.out.println("id'si 3 ten buyuk olanlarin Tum bilgilerini = "+jsonPath.getList("data.findAll{it.id>3}"));

        List<Integer> ids= jsonPath.getList("data.findAll{it.id>3}.id"); //findAll collection'in basladigi yerde kullanilir
        System.out.println("id'si 3 ten buyuk olanlarin id'sini getirdi ="+ids);

//        Assert that there are 3 ids greater than 3
        assertEquals(ids.size(),3);

//        4)Print all names whose ids are less than 3 on the console
//        Assert that the number of names whose ids are less than 3 is 2
        List<String> names = jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println(names);
        assertEquals(2,names.size());

    }

}
