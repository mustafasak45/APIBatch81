package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03c_DataninAltindaCokBodyDondugunde {

  /*  {
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
        {
            "id": 3,
            "email": "emma.wong@reqres.in",
            "first_name": "Emma",
            "last_name": "Wong",
            "avatar": "https://reqres.in/img/faces/3-image.jpg"
        },
        {
            "id": 4,
            "email": "eve.holt@reqres.in",
            "first_name": "Eve",
            "last_name": "Holt",
            "avatar": "https://reqres.in/img/faces/4-image.jpg"
        },
        {
            "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
        },
        {
            "id": 6,
            "email": "tracey.ramos@reqres.in",
            "first_name": "Tracey",
            "last_name": "Ramos",
            "avatar": "https://reqres.in/img/faces/6-image.jpg"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}*/

    @Test
    public void test03() {
        //1-Junit ve TestNG deki Assert ile doğrulama yapılabildigini
        //2-assertThat() ile doğrulama yapılabildigini
        //3-Matcher class ile assert yapılabildigini gördük

        String url ="https://reqres.in/api/users";

        Response response=given().when().get(url);
        response.prettyPrint();
        response.then().
                assertThat().
                body("data[0].email",equalTo("george.bluth@reqres.in"),
                        "data[0].first_name",equalTo("George"),
                        "data[0].last_name",equalTo("Bluth"),
                        "data[0].avatar",equalTo("https://reqres.in/img/faces/1-image.jpg")).
                body("data[1].email",equalTo("janet.weaver@reqres.in"),
                        "data[1].first_name",equalTo("Janet"),
                        "data[1].last_name",equalTo("Weaver"),
                        "data[1].avatar",equalTo("https://reqres.in/img/faces/2-image.jpg")).
                body("data[2].email",equalTo("emma.wong@reqres.in"),
                        "data[2].first_name",equalTo("Emma"),
                        "data[2].last_name",equalTo("Wong"),
                        "data[2].avatar",equalTo("https://reqres.in/img/faces/3-image.jpg"));

        //4,5,6 da aynı gidiyor kodları...

    }
}
