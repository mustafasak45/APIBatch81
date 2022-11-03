package test_data;

import java.util.HashMap;
import java.util.Map;

public class calismaTestData {

    public Map<String,String> dataMap(String name,String email,String gender, String status){

        Map<String,String> x =new HashMap<>();
        x.put("name",name);
        x.put("email",email);
        x.put("gender",gender);
        x.put("status",status);
        return x;
    }

    public Map<String,Object> metaMap(Object meta,Map<String,String> data){

        Map<String,Object> y = new HashMap<>();
        y.put("meta",meta);
        y.put("data",data);

        return y;
    }
}
