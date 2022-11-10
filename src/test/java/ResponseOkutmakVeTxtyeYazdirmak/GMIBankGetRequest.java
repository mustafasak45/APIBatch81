package ResponseOkutmakVeTxtyeYazdirmak;

import base_urls.GMIBankBaseURL;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.GMIBankCustomer;
import utils.ReadText;
import utils.WriteToText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GMIBankGetRequest extends GMIBankBaseURL {
       /*
http://www.gmibank.com/api/tp-customers end point'ine
request gönderin
 1) Tüm Customer bilgilerini ekrana yazdırın.
 2) Tüm Customer SSN lerini ekrana yazdırın.
 3) Tüm Customer SSN lerini text dosyası olarak kaydedin
 4) Olusturduğunuz text dosyasından  SSNleri okuyarak
    "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın
 */

    @Test
    public void getRequest() throws IOException {



        spec.pathParam("first","tp-customers");

        Response response = given().
                                    spec(spec).
                                    headers("Authorization","Bearer "+generateToken()).
                                    when().
                                    get("/{first}");
        //response.prettyPrint();

        ObjectMapper obj = new ObjectMapper();//responseyi burada ObjectMapper ile GMIBankCustomer pojosuna koyduk

        GMIBankCustomer[] customers = obj.readValue(response.asString(), GMIBankCustomer[].class);//URLden dönen
                                                //en genel pojo(GMIBankCustomer classı) formatında Array tanımladık


//        1) Tüm Customer bilgilerini ekrana yazdırın.

       for (int i = 0; i < customers.length; i++) {
           System.out.println(customers[i]);
        }
     //   Arrays.stream(actualData).forEach(System.out::println); //lambda ile istersek yazdırabiliriz


//        2) Tüm Customer SSN lerini ekrana yazdırın.
        for (int i = 0; i < customers.length; i++) {
            System.out.println(customers[i].getSsn());
        }


//       3) Tüm Customer SSN lerini text dosyası olarak kaydedin
        String fileName = "src/test/java/ResponseOkutmakVeTxtyeYazdirmak/SSNNumbers.txt";//Kayıtların yapılacak
                                                                                        // yolu belileyip kayıt
                                                                                        //ismini elimizler girdik(SSNNumbers.txt)
        WriteToText.saveSSNData(fileName,customers);//fileName de belirtilen yola belirtilen isimde txt dosyası oluşturur

//        4) Olusturduğunuz text dosyasından  SSNleri okuyarak
//        "531-95-8437", "049-43-2360", "123-34-3434" SSNlerinin olduğunu doğrulayın

        //İlk bunun için expected olarak bize verilen SSN'leri liste atıyoruz
        List<String> expectedSSN = new ArrayList<>();
        expectedSSN.add("531-95-8437");
        expectedSSN.add("049-43-2360");
        expectedSSN.add("123-34-3434");

        //Sonra actualDatamızın olacagi listi tanımlayıp txt dosyamızın için liste atıyoruz
        List<String> actualSSN = ReadText.readCustomerSSNList(fileName);

        //Enson da verileri assert ediyoruz
        assertTrue(actualSSN.containsAll(expectedSSN));
    }
}
