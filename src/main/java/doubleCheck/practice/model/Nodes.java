package doubleCheck.practice.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "pausewifi-nodes")
public class Nodes {
    private Map<String, Map<String, String>> models = new HashMap<>();
    private Map<String, String> nodeValue = new HashMap<>();

    public Map<String, String> getNodes(String model){
        nodeValue = models.get(model);
        return nodeValue;
    }

    public String getMacNode(){
        for(Map.Entry<String,String> es : nodeValue.entrySet())
        {
            if(es.getValue().equals("mac"))
                return es.getKey();
        }
        return "";
    }

    public String getObjNode(){
        for(Map.Entry<String,String> es : nodeValue.entrySet())
        {
            if(es.getValue().equals("obj"))
                return es.getKey();
        }
        return "";
    }

    public Integer getIndexPos(String model){
        Map<String,String> nodes = getNodes(model);
        for(Map.Entry<String,String> es : nodes.entrySet())
        {
            if(es.getKey().contains("index"))
            {
                List<String> keyArray = Arrays.asList(es.getKey().split("\\."));
                return keyArray.indexOf("index");
            }
        }
        return null;
    }

}
