package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulBookerTestData { //PAYLOAD denir bu classtan method kullanmaya

    public Map<String,String> expBookingdatesMethod(String checkin,String checkout){

        Map<String,String> bookingMap = new HashMap<>();
        bookingMap.put("checkin",checkout);
        bookingMap.put("checkout",checkout);
        return bookingMap;
    }

    public Map<String,Object> expDataMethod(String firstname, String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingdates){

        Map<String,Object> expDataMap = new HashMap<>();
        expDataMap.put("firstname",firstname);
        expDataMap.put("lastname",lastname);
        expDataMap.put("totalprice",totalprice);
        expDataMap.put("depositpaid",depositpaid);
        expDataMap.put("bookingdates",bookingdates);
        return expDataMap;
    }
}
