package com.vehiclefactory;

import com.vehiclefactory.client.VehicleClient;
import com.vehiclefactory.factory.ElectricVehicleFactory;
import com.vehiclefactory.factory.GasolineVehicleFactory;
import com.vehiclefactory.factory.IVehicleFactory;
import com.vehiclefactory.product.ElectricCar;
import com.vehiclefactory.product.ElectricEngine;
import com.vehiclefactory.product.ElectricTruck;
import com.vehiclefactory.product.GasolineCar;
import com.vehiclefactory.product.GasolineEngine;
import com.vehiclefactory.product.GasolineTruck;
import com.vehiclefactory.product.IEngine;
import com.vehiclefactory.product.IVehicle;

/**
 * VehicleFactoryTest
 * 
 * Standalone test suite verifying all Abstract Factory Pattern requirements,
 * class contracts, polymorphic behaviors, and constraints specified in "Inclass 04".
 */
public class VehicleFactoryTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     RUNNING VEHICLE ABSTRACT FACTORY TESTS       ");
        System.out.println("==================================================");

        testGasolineVehicleFactoryProductFamily();
        testElectricVehicleFactoryProductFamily();
        testGasolineEngineLifecycle();
        testElectricEngineLifecycle();
        testShowDetailsMethods();
        testConvenienceCreateVehicle();
        testVehicleClientDecoupling();
        testFactoryCustomizationMethods();
        testInvalidInputHandling();

        System.out.println("\n--------------------------------------------------");
        System.out.printf("TEST SUMMARY: %d Passed, %d Failed%n", testsPassed, testsFailed);
        System.out.println("--------------------------------------------------");

        if (testsFailed > 0) {
            System.err.println("SOME TESTS FAILED!");
            System.exit(1);
        } else {
            System.out.println("ALL TESTS PASSED SUCCESSFULLY!");
        }
    }

    private static void assertTrue(String testName, boolean condition, String errorMsg) {
        if (condition) {
            System.out.println(" [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.println(" [FAIL] " + testName + " - " + errorMsg);
            testsFailed++;
        }
    }

    private static void assertEquals(String testName, Object expected, Object actual) {
        boolean match = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        if (match) {
            System.out.println(" [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.printf(" [FAIL] %s - Expected: %s, Actual: %s%n", testName, expected, actual);
            testsFailed++;
        }
    }

    public static void testGasolineVehicleFactoryProductFamily() {
        System.out.println("\n--- Testing GasolineVehicleFactory Product Family ---");
        IVehicleFactory factory = new GasolineVehicleFactory();

        // 1. Car test
        IVehicle car = factory.createCar();
        assertTrue("Gasoline factory creates non-null car", car != null, "Car is null");
        assertTrue("Car is instance of GasolineCar", car instanceof GasolineCar, "Car is not GasolineCar");
        assertTrue("Car engine is instance of GasolineEngine", car.getEngine() instanceof GasolineEngine, "Engine not GasolineEngine");
        assertEquals("Car category is passenger car", "Passenger Car (Sedan)", car.getVehicleCategory());

        // 2. Truck test
        IVehicle truck = factory.createTruck();
        assertTrue("Gasoline factory creates non-null truck", truck != null, "Truck is null");
        assertTrue("Truck is instance of GasolineTruck", truck instanceof GasolineTruck, "Truck is not GasolineTruck");
        assertTrue("Truck engine is instance of GasolineEngine", truck.getEngine() instanceof GasolineEngine, "Engine not GasolineEngine");

        // 3. Engine test
        IEngine engine = factory.createEngine();
        assertTrue("Gasoline factory creates non-null engine", engine != null, "Engine is null");
        assertTrue("Engine is instance of GasolineEngine", engine instanceof GasolineEngine, "Engine is not GasolineEngine");
    }

    public static void testElectricVehicleFactoryProductFamily() {
        System.out.println("\n--- Testing ElectricVehicleFactory Product Family ---");
        IVehicleFactory factory = new ElectricVehicleFactory();

        // 1. Car test
        IVehicle car = factory.createCar();
        assertTrue("Electric factory creates non-null car", car != null, "Car is null");
        assertTrue("Car is instance of ElectricCar", car instanceof ElectricCar, "Car is not ElectricCar");
        assertTrue("Car engine is instance of ElectricEngine", car.getEngine() instanceof ElectricEngine, "Engine not ElectricEngine");

        // 2. Truck test
        IVehicle truck = factory.createTruck();
        assertTrue("Electric factory creates non-null truck", truck != null, "Truck is null");
        assertTrue("Truck is instance of ElectricTruck", truck instanceof ElectricTruck, "Truck is not ElectricTruck");
        assertTrue("Truck engine is instance of ElectricEngine", truck.getEngine() instanceof ElectricEngine, "Engine not ElectricEngine");

        // 3. Engine test
        IEngine engine = factory.createEngine();
        assertTrue("Electric factory creates non-null engine", engine != null, "Engine is null");
        assertTrue("Engine is instance of ElectricEngine", engine instanceof ElectricEngine, "Engine is not ElectricEngine");
    }

    public static void testGasolineEngineLifecycle() {
        System.out.println("\n--- Testing Gasoline Engine Start & Stop Lifecycle ---");
        IEngine engine = new GasolineEngine("Test V6", 300, 3.5, "95 Octane");

        assertTrue("Engine starts in STOPPED state", !engine.isRunning(), "Engine was running initially");
        engine.Start();
        assertTrue("Engine is RUNNING after Start()", engine.isRunning(), "Engine not running after Start");
        engine.Stop();
        assertTrue("Engine is STOPPED after Stop()", !engine.isRunning(), "Engine still running after Stop");
        assertEquals("Engine horsepower matches", 300, engine.getHorsepower());
    }

    public static void testElectricEngineLifecycle() {
        System.out.println("\n--- Testing Electric Engine Start & Stop Lifecycle ---");
        IEngine engine = new ElectricEngine("Dual-Motor EV", 400, 90.0, 800);

        assertTrue("Engine starts in OFFLINE state", !engine.isRunning(), "Electric engine was running initially");
        engine.Start();
        assertTrue("Engine is RUNNING after Start()", engine.isRunning(), "Electric engine not running after Start");
        engine.Stop();
        assertTrue("Engine is OFFLINE after Stop()", !engine.isRunning(), "Electric engine still running after Stop");
        assertEquals("Electric horsepower matches", 400, engine.getHorsepower());
    }

    public static void testShowDetailsMethods() {
        System.out.println("\n--- Testing ShowDetails() on All Concrete Products ---");
        IVehicle gasCar = new GasolineCar();
        IVehicle gasTruck = new GasolineTruck();
        IVehicle elecCar = new ElectricCar();
        IVehicle elecTruck = new ElectricTruck();

        // Ensure ShowDetails() executes without throwing any exceptions
        try {
            gasCar.ShowDetails();
            gasTruck.ShowDetails();
            elecCar.ShowDetails();
            elecTruck.ShowDetails();
            assertTrue("ShowDetails() executes cleanly for all 4 vehicle types", true, "");
        } catch (Exception e) {
            assertTrue("ShowDetails() threw exception", false, e.getMessage());
        }
    }

    public static void testConvenienceCreateVehicle() {
        System.out.println("\n--- Testing createVehicle(type) Polymorphic Dispatch ---");
        IVehicleFactory factory = new GasolineVehicleFactory();

        IVehicle car = factory.createVehicle("car");
        assertTrue("createVehicle('car') returns GasolineCar", car instanceof GasolineCar, "Not GasolineCar");

        IVehicle truck = factory.createVehicle("truck");
        assertTrue("createVehicle('truck') returns GasolineTruck", truck instanceof GasolineTruck, "Not GasolineTruck");
    }

    public static void testVehicleClientDecoupling() {
        System.out.println("\n--- Testing VehicleClient Decoupled Assembly ---");
        IVehicleFactory electricFactory = new ElectricVehicleFactory();
        VehicleClient client = new VehicleClient(electricFactory);

        IVehicle car = client.orderCar();
        IVehicle truck = client.orderTruck();
        IEngine engine = client.orderStandaloneEngine();

        assertEquals("Client produced 2 vehicles", 2, client.getProducedVehicles().size());
        assertEquals("Client produced 1 standalone engine", 1, client.getProducedEngines().size());
        assertTrue("Client car is ElectricCar", car instanceof ElectricCar, "Not ElectricCar");
        assertTrue("Client truck is ElectricTruck", truck instanceof ElectricTruck, "Not ElectricTruck");
        assertTrue("Client engine is ElectricEngine", engine instanceof ElectricEngine, "Not ElectricEngine");
    }

    public static void testFactoryCustomizationMethods() {
        System.out.println("\n--- Testing Custom Factory Methods ---");
        GasolineVehicleFactory gasFactory = new GasolineVehicleFactory();
        GasolineCar customCar = gasFactory.createCustomCar("Mustang GT Custom", 450, 5.0, 4);
        assertEquals("Custom car model name matches", "Mustang GT Custom", customCar.getModel());
        assertEquals("Custom car horsepower matches", 450, customCar.getEngine().getHorsepower());

        ElectricVehicleFactory elecFactory = new ElectricVehicleFactory();
        ElectricTruck customTruck = elecFactory.createCustomTruck("CyberHauler Max", 650, 150.0, 8500.0);
        assertEquals("Custom truck model name matches", "CyberHauler Max", customTruck.getModel());
        assertEquals("Custom truck towing matches", 8500.0, customTruck.getTowingCapacityKg());
    }

    public static void testInvalidInputHandling() {
        System.out.println("\n--- Testing Error Handling for Invalid Vehicle Types ---");
        IVehicleFactory factory = new ElectricVehicleFactory();

        try {
            factory.createVehicle("submarine");
            assertTrue("Unknown vehicle type should throw IllegalArgumentException", false, "Did not throw");
        } catch (IllegalArgumentException e) {
            assertTrue("Properly threw IllegalArgumentException on invalid vehicle type", true, "");
        }

        try {
            factory.createVehicle(null);
            assertTrue("Null vehicle type should throw IllegalArgumentException", false, "Did not throw");
        } catch (IllegalArgumentException e) {
            assertTrue("Properly threw IllegalArgumentException on null vehicle type", true, "");
        }
    }
}
