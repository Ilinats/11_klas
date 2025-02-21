class HeaterAdapter implements SmartDevice {
    private OldHeater oldHeater;

    public HeaterAdapter(OldHeater oldHeater) {
        this.oldHeater = oldHeater;
    }

    @Override
    public void turnOn() {
        oldHeater.heatUp(5);
        System.out.println("Heater adapted to smart system");
    }
}