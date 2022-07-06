package doubleCheck.practice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import doubleCheck.practice.model.BasicModel;

public interface MyService {

    void parseNodes(String model);

    BasicModel saveModel(BasicModel model);

    void testCode() throws JsonProcessingException;
}
