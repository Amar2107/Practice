package doubleCheck.practice.parser;

import doubleCheck.practice.model.Model1;
import doubleCheck.practice.model.Model2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class Parser {

    public Object modelParser(String modelType, List<String> nodes) {
        if (Objects.equals(modelType, "model1"))
            return  parseModel1(nodes, new Model1());
        else if (Objects.equals(modelType, "model2"))
            return parseModel2(nodes, new Model2());
        else
            return null;

    }

    private void parseModel3(List<String> nodes) {
    }

    private Model2 parseModel2(List<String> nodes, Model2 model2) {
        nodes.forEach(node -> {
            if (node.contains("mac"))
                model2.setMac(node);
            else if (node.contains("control"))
                model2.setControl(node);
            else if (node.contains("object"))
                model2.setObj(node);
        });
        return model2;
    }

    private Model1 parseModel1(List<String> nodes, Model1 model1) {
        nodes.forEach(node -> {
            if (node.contains("mac"))
                model1.setMac(node);
            else if (node.contains("control"))
                model1.setControl(node);
            else if (node.contains("enable"))
                model1.setEnable(node);
        });
        return model1;
    }
}
