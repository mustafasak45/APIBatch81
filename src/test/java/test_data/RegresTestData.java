package test_data;

import java.util.HashMap;
import java.util.Map;

public class RegresTestData {

    public Map<String,String> expectedData(String name,String job){
        Map<String,String> tempMap = new HashMap<>();
        tempMap.put("name",name);
        tempMap.put("job",job);

        return tempMap;
    }
}
