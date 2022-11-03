package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.calismaTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class calisma extends GoRestBaseUrl {
 /*
 https://gorest.co.in/public/v1/users/2986
 {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Kanaka Jain",
        "email": "kanaka_jain@stark.net",
        "gender": "male",
        "status": "active"
    }
}
*/

    @Test
    public void get01(){
    spec.pathParams("first","users","second",2986);

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        calismaTestData obj = new calismaTestData();
        Map<String,String> expData = obj.dataMap("Kanaka Jain","kanaka_jain@stark.net","male","active");
        Map<String,Object> expMeta = obj.metaMap(null,expData);
       // System.out.println("expMeta = " + expMeta);
       // System.out.println("expData = " + expData);

        Map<String,Object> actMeta = response.as(HashMap.class);
       // System.out.println("actMeta = " + actMeta);

        assertEquals(expMeta.get("meta"),actMeta.get("meta"));
        assertEquals(expData.get("name"),((Map)(actMeta.get("data"))).get("name"));
        assertEquals(expData.get("email"),((Map)(actMeta.get("data"))).get("email"));
        assertEquals(expData.get("gender"),((Map)(actMeta.get("data"))).get("gender"));
        assertEquals(expData.get("status"),((Map)(actMeta.get("data"))).get("status"));

    }
}