//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SmartHomeManager manager = SmartHomeManager.getInstance();

        SmartDevice light = new SmartLightBuilder().setBrightness(80).setColor("Blue").build();
        SmartDevice ac = SmartDeviceFactory.createDevice("ac");
        SmartDevice heater = new HeaterAdapter(new OldHeater());

        manager.addDevice(light);
        manager.addDevice(ac);
        manager.addDevice(heater);

        manager.showDevices();

        light.turnOn();
        ac.turnOn();
        heater.turnOn();
    }
}