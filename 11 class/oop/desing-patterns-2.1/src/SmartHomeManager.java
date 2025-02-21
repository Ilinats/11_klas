import java.util.ArrayList;
import java.util.List;

class SmartHomeManager {
    private static SmartHomeManager instance;
    private List<SmartDevice> devices = new ArrayList<>();

    private SmartHomeManager() {}

    public static SmartHomeManager getInstance() {
        if (instance == null) {
            instance = new SmartHomeManager();
        }
        return instance;
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void showDevices() {
        for (SmartDevice device : devices) {
            System.out.println(device);
        }
    }
}
