package get_request;

import base_urls.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Get07 extends RestfulBaseUrl {
/*
      Given
       https://jsonplaceholder.typicode.com/todos
When
    I send GET Request to the URL == > URL'e Get Request gonderin
Then
    1)Status code is 200 == > Status kodu 200 olmali
    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
      basliginin "delectus aut autem" icerdigini dogrulayin
   */

    @Test
    public void get01() {
        //1. Set The Url
        spec.pathParam("first","todos");

        //2. Set The Expected Data
        //3. Send Request And Get the Response
        Response response=given().spec(spec).when().get("/{first}");

        //4.Do Assertion

//        1)Status code is 200 == > Status kodu 200 olmali
        response.then().assertThat().statusCode(200);
        //assertEquals(200,response.getStatusCode()); üstteki kodla aynı işi yapar

//        2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        JsonPath json= response.jsonPath();
        List<Integer> idler=json.getList("findAll{it.id>190}.id"); //id'si 190 dan buyuk olanlari idler listine at demek
        System.out.println("Id'si 190'dan buyuk olanlar : "+idler);

//        Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
//        3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
//        Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
//        4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
//        Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
//        basliginin "delectus aut autem" icerdigini dogrulayin

    }
}
