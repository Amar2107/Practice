package doubleCheck.practice.controller;

import doubleCheck.practice.annotation.AttachLog;
import doubleCheck.practice.model.BasicModel;
import doubleCheck.practice.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("practice")
public class PracticeController {

    @Autowired
    MyService service;

    @GetMapping("/service")
    public void invokeService(@RequestParam String model){
        service.parseNodes(model);
        return;
    }

    @PostMapping("/service")
    public ResponseEntity<BasicModel> postModel(@RequestBody BasicModel model)
    {
        BasicModel model1 = service.saveModel(model);
        return ResponseEntity.ok(model1);
    }

    @GetMapping("/test")
    @AttachLog
    public ResponseEntity<String> testMyCode(){
        try {
            service.testCode();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok("Operation performed");
    }


}
