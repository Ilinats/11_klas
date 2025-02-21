class SmartLight implements SmartDevice {
    private final int brightness;
    private final String color;

    public SmartLight(int brightness, String color) {
        this.brightness = brightness;
        this.color = color;
    }

    @Override
    public void turnOn() {
        System.out.println("Smart Light is on. Brightness: " + brightness + " Color: " + color);
    }
}