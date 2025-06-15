package smarthome;

/** Coordinates the three devices with a fixed lock order to avoid dead‑lock. */
public class Controller {

    private final Light          light;
    private final Thermostat     thermostat;
    private final SecurityCamera camera;

    public Controller(Light light,
                      Thermostat thermostat,
                      SecurityCamera camera) {
        this.light       = light;
        this.thermostat  = thermostat;
        this.camera      = camera;
    }

    public void simulateCoordinatedAccess() {

        Thread t1 = new Thread(() -> lockInOrder("T1"), "Coordinator‑T1");
        Thread t2 = new Thread(() -> lockInOrder("T2"), "Coordinator‑T2");
        Thread t3 = new Thread(() -> lockInOrder("T3"), "Coordinator‑T3");

        t1.start();
        t2.start();
        t3.start();

    }

    private void lockInOrder(String tag) {
        synchronized (light) {
            System.out.println(tag + " locked Light");
            sleepQuiet(100);

            synchronized (thermostat) {
                System.out.println(tag + " locked Thermostat");
                sleepQuiet(100);

                synchronized (camera) {
                    System.out.println(tag + " locked SecurityCamera");
                }
            }
        }
    }

    private void sleepQuiet(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
