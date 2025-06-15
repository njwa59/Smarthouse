package smarthome;

public class SecurityCamera extends Device {
    private boolean recording;

    public SecurityCamera(String deviceId, String name) {
        super(deviceId, name, "Security Camera", "Front Door");
        this.recording = false;
    }

    public synchronized void startRecording() {
        if (!recording) {
            recording = true;
            System.out.println(name + " started recording.");
        }
    }

    public synchronized void stopRecording() {
        if (recording) {
            recording = false;
            System.out.println(name + " stopped recording.");
        }
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                turnOn();
                startRecording();
                Thread.sleep(1500); // simulate recording time
                stopRecording();
                turnOff();
                Thread.sleep(1500); // wait before next cycle
            }
        } catch (InterruptedException e) {
            // Thread interrupted
        }
        System.out.println(name + " thread stopped.");
    }
}
