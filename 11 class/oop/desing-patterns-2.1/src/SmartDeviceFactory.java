class SmartDeviceFactory {
    public static SmartDevice createDevice(String type) {
        return switch (type.toLowerCase()) {
            case "light" -> new SmartLight(50, "White");
            case "ac" -> new SmartAC(22, "Cool");
            case "speaker" -> new SmartSpeaker(10, "English");
            default -> throw new IllegalArgumentException("Unknown device type");
        };
    }
}