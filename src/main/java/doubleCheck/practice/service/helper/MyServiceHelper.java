package doubleCheck.practice.service.helper;

import doubleCheck.practice.model.MockResponse;
import doubleCheck.practice.model.Nodes;
import doubleCheck.practice.model.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class MyServiceHelper {

    @Autowired
    MockResponse response;
    @Autowired
    Nodes nodes;

    public List<Params> prepareParams(){
        Map<String,String> responseParam = response.getParams();
        List<Params> params = new LinkedList<>();
        for(Map.Entry<String,String> es: responseParam.entrySet())
            params.add(new Params(es.getKey(), es.getValue()));
        return params;
    }


    public String customMatcher(String paramName, List<String> keys){
        Map<String, String> nodeValue = nodes.getNodes("ZTE");
        if(keys.contains(paramName))
            return paramName;
        Integer index = nodes.getIndexPos("ZTE");
        List<String> paramNameList = Arrays.asList(paramName.split("\\."));
        paramNameList.set(index,"index");
        String key = String.join(".",paramNameList);
        System.out.println("KEY"+key);
        System.out.println(nodeValue);
       if(keys.contains(key))
           return nodeValue.get(key);
    return "";
    }

    public void getBlockedMacs(List<Params> params){
        Map<String, String> nodeValue = nodes.getNodes("ZTE");
        List<String> keys = new LinkedList<>(nodeValue.keySet());
        for (Params param : params){
            System.out.println("DISPLAY!"+customMatcher(param.getName(),keys));
        }
    }
}
