package doubleCheck.practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    int id;
    String deviceName;
    String deviceMac;
    String deviceStatus;
}
