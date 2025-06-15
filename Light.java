package smarthome;

public class Light extends Device {
    private int brightness;

    public Light(String deviceId, String name) {
        super(deviceId, name, "Light", "Living Room");
        this.brightness = 100; // Start at full brightness
    }

    public Light(String deviceId, String name, String type, String location) {
        super(deviceId, name, type, location);
        this.brightness = 100;
    }

    public void dim() {
        brightness = Math.max(0, brightness - 10);
        System.out.println(name + " dimmed to " + brightness + "%");
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                turnOn();
                Thread.sleep(1000);
                dim();
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
