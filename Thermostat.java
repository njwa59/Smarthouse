package smarthome;

public class Thermostat extends Device {
    private float temperature;

    public Thermostat(String deviceId, String name) {
        super(deviceId, name, "Thermostat", "Hall");
        this.temperature = 22.0f;
    }

    public Thermostat(String deviceId, String name, String type, String location) {
        super(deviceId, name, type, location);
        this.temperature = 22.0f;
    }

    public void setTemp(float temp) {
        this.temperature = temp;
        System.out.println(name + " temperature set to " + temp + "°C");
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                turnOn();
                setTemp((float)(20 + Math.random() * 5));
                Thread.sleep(1000);
                turnOff();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
            Thread.currentThread().interrupt();
        }

        System.out.println(name + " thread stopped.");
    }
}
