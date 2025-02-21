class SmartSpeaker implements SmartDevice {
    private final int volume;
    private final String language;

    public SmartSpeaker(int volume, String language) {
        this.volume = volume;
        this.language = language;
    }

    @Override
    public void turnOn() {
        System.out.println("Smart Speaker is on. Volume: " + volume + " Language: " + language);
    }
}