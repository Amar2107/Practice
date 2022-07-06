package doubleCheck.practice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import doubleCheck.practice.model.BasicModel;
import doubleCheck.practice.model.Device;
import doubleCheck.practice.model.Nodes;
import doubleCheck.practice.parser.Parser;
import doubleCheck.practice.repo.BasicModelRepository;
import doubleCheck.practice.service.helper.MyServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyServiceImpl implements MyService{

    @Autowired
    Nodes nodes;
    @Autowired
    Parser parser;
    @Autowired
    BasicModelRepository modelRepository;
    @Autowired
    MyServiceHelper msHelper;

    @Override
    public void parseNodes(String model){
        Object ob = null;
        Map<String, Map<String, String>> nodeMap = nodes.getModels();

        for(Map.Entry<String, Map<String, String>> es : nodeMap.entrySet()) {
            System.out.println(" key " + es.getKey());
                    for(Map.Entry<String,String> entry : es.getValue().entrySet())
                        System.out.println(" key " + entry.getKey() + " Value "+ entry.getValue());
            System.out.println();
        }
        HashMap<String,String> node = (HashMap<String, String>) nodes.getNodes(model);
        Map<String, String> nodeCopy = new HashMap<>(node);
        nodeCopy.values().removeIf("obj":: equals);

        System.out.println(nodeCopy);
        System.out.println("Display1: node "+node);
        System.out.println(nodes.getMacNode());
        System.out.println(nodes.getObjNode());
    }

    public List<Device> mockDeviceList(){
        List<Device> devices = new LinkedList<>();

        devices.add(new Device(1,"d1","m1","ACTIVE"));
        devices.add(new Device(2,"d2","m2","ACTIVE"));
        devices.add(new Device(3,"d3","m3","ACTIVE"));
        devices.add(new Device(4,"d4","m4","ACTIVE"));
        return devices;
    }

    public List<Device> mockPausedDevice(){
        List<Device> pausedDevices = new LinkedList<>();
        pausedDevices.add(new Device(2,"d2","m2","PAUSED"));
        pausedDevices.add(new Device(4,"d4","m4","PAUSED"));
        pausedDevices.add(new Device(4,"d5","m5","PAUSED"));
        return pausedDevices;
    }

    @Override
    public BasicModel saveModel(BasicModel model){
        String id = UUID.randomUUID().toString();
        model.setId(id);
        modelRepository.save(model);
        BasicModel modelGet = modelRepository.getById(id);
        return modelGet;

    }

    public int binaryToInteger(String binary) {
        char[] numbers = binary.toCharArray();
        int result = 0;
        for(int i=numbers.length - 1; i>=0; i--)
            if(numbers[i]=='1')
                result += Math.pow(2, (numbers.length-i - 1));
        return result;
    }

    @Override
    public void testCode() throws JsonProcessingException {
        System.out.println("Display1:");

        //System.out.println(msHelper.prepareParams());
        Map<String, String> nodeMap = nodes.getNodes("ZTE");


        System.out.println(" DISP{ "+nodeMap.entrySet().stream().filter(entry ->Objects.equals(entry.getValue(),"mac")).map(Map.Entry::getKey).collect(Collectors.toSet()));

        System.out.println(nodeMap);
        //msHelper.getBlockedMacs(msHelper.prepareParams());

        List<Device> con = new LinkedList<>();
        con.addAll(mockPausedDevice());
        System.out.println("Con "+con);

        List<Device> connectedDevice = mockDeviceList();
        List<Device> pauseDevices = mockPausedDevice();
        pauseDevices = new LinkedList<>();
        HashMap<String,Device> mMap = new HashMap<>();
        connectedDevice.forEach(device -> {
            mMap.put(device.getDeviceMac(),device);
        });
        pauseDevices.forEach(device -> {
            mMap.put(device.getDeviceMac(),device);
        });

        System.out.println("MAP  \n" +mMap.values());

        System.out.println("==================================================");

        String s = "1234567890";

        if(!s.contains(":")) {
            String[] m = s.split("(?<=\\G..)");
            System.out.println("M = " + String.join(":", Arrays.asList(m)));
        }

        String demoHostName = "Unknown_12:ab:3e:fe:gh";

        if(demoHostName.toLowerCase().contains("unknown")) {
            System.out.println(" Replaced " +demoHostName.replaceAll("(?i)unknown_",""));
        }

        System.out.println("===================  TEST ===============================");

        ObjectMapper mapper = new ObjectMapper();

        BasicModel basicModel = new BasicModel();
        basicModel.setId("1");
        basicModel.setName("Amar");
        System.out.println(" basic model "+mapper.writeValueAsString(basicModel));

    }




}
