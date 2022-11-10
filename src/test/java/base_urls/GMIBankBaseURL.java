package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utils.Authentication;

public class GMIBankBaseURL extends Authentication { //parent classı token almak için kullanılıyor
    protected RequestSpecification spec;

    @Before
    public void setUp(){
        spec =new RequestSpecBuilder().setBaseUri("https://www.gmibank.com/api/").build();
    }
}
