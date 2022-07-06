package doubleCheck.practice.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "input")
public class DemoConfig {

    HashMap<String,String> demoConfig;
}
