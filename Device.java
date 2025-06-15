package smarthome;

public abstract class Device extends Thread {
    protected String deviceId;
    protected String name;
    protected String status;
    protected String type;
    protected String location;

    public Device(String deviceId, String name, String type, String location) {
        this.deviceId = deviceId;
        this.name = name;
        this.status = "OFF";
        this.type = type;
        this.location = location;
    }

    public synchronized void turnOn() {
        status = "ON";
        System.out.println(name + " is turned ON.");
    }

    public synchronized void turnOff() {
        status = "OFF";
        System.out.println(name + " is turned OFF.");
    }

    public synchronized String getStatus() {
        return status;
    }

    public synchronized void updateStatus() {
        System.out.println("Status of " + name + " is " + status);
    }

    public abstract void run();
}
