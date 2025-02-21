class SmartAC implements SmartDevice {
    private final int temperature;
    private final String mode;

    public SmartAC(int temperature, String mode) {
        this.temperature = temperature;
        this.mode = mode;
    }

    @Override
    public void turnOn() {
        System.out.println("Smart AC is on. Temperature: " + temperature + " Mode: " + mode);
    }
}