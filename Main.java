package smarthome;

public class Main {
    public static void main(String[] args) {

        Light light = new Light("D001", "Living Room Light");
        Thermostat thermostat = new Thermostat("D002", "Hall Thermostat");
        SecurityCamera camera = new SecurityCamera("D003", "Front Door Camera");

        light.setName("Light-Thread");
        thermostat.setName("Thermostat-Thread");
        camera.setName("Camera-Thread");

        light.start();
        thermostat.start();
        camera.start();

        System.out.println("\n--- Starting Safe Coordinated Access ---");
        Controller controller = new Controller(light, thermostat, camera);
        controller.simulateCoordinatedAccess();

        try {
            Thread.sleep(6000);

            System.out.println("\n--- Starting Deadlock Simulation ---");
            DeadlockController deadlockController = new DeadlockController();
            deadlockController.startDeadlock();

            Thread.sleep(8000);

            light.interrupt();
            thermostat.interrupt();
            camera.interrupt();

            light.join();
            thermostat.join();
            camera.join();

        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted.");
            e.printStackTrace();
        }

        System.out.println("\nSimulation complete.");
    }
}
