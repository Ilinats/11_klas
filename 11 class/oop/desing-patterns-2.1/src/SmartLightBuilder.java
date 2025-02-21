class SmartLightBuilder {
    private int brightness = 50;
    private String color = "White";

    public SmartLightBuilder setBrightness(int brightness) {
        this.brightness = brightness;
        return this;
    }

    public SmartLightBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public SmartLight build() {
        return new SmartLight(brightness, color);
    }
}
