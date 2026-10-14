package com.vehiclefactory.main;

import com.vehiclefactory.client.VehicleClient;
import com.vehiclefactory.factory.ElectricVehicleFactory;
import com.vehiclefactory.factory.GasolineVehicleFactory;
import com.vehiclefactory.factory.IVehicleFactory;
import com.vehiclefactory.product.IEngine;
import com.vehiclefactory.product.IVehicle;

import java.util.Scanner;

/**
 * Main Application: Vehicle Manufacturing Abstract Factory System
 * 
 * Demonstrates how to use the Abstract Factory pattern to dynamically select
 * a vehicle manufacturing factory based on the requested engine type (Gasoline or Electric),
 * create vehicles (Car, Truck) and engines, and invoke ShowDetails() and Start().
 */
public class Main {

    public static void main(String[] args) {
        printBanner();

        // Check if interactive flag is passed
        boolean interactive = false;
        String cliEngine = null;
        String cliVehicle = null;

        for (int i = 0; i < args.length; i++) {
            if ("--interactive".equalsIgnoreCase(args[i]) || "-i".equalsIgnoreCase(args[i])) {
                interactive = true;
            } else if ("--demo".equalsIgnoreCase(args[i]) || "-d".equalsIgnoreCase(args[i])) {
                runAutomatedShowcase();
                return;
            } else if (cliEngine == null && !args[i].startsWith("-")) {
                cliEngine = args[i];
            } else if (cliVehicle == null && !args[i].startsWith("-")) {
                cliVehicle = args[i];
            }
        }

        // If command-line arguments specify engine and vehicle:
        if (cliEngine != null) {
            handleCliExecution(cliEngine, cliVehicle != null ? cliVehicle : "all");
            return;
        }

        if (interactive) {
            runInteractiveSession();
            return;
        }

        // Default behavior: Run the comprehensive automated showcase, then offer interactive mode
        runAutomatedShowcase();
        
        System.out.println("\nTip: To run the interactive configuration console, launch with: run.bat --interactive");
    }

    private static void printBanner() {
        System.out.println("======================================================================");
        System.out.println("    VEHICLE MANUFACTURING SYSTEM - ABSTRACT FACTORY PATTERN           ");
        System.out.println("    Software Architecture & Design Patterns (In-class Activity 04)    ");
        System.out.println("======================================================================");
    }

    /**
     * Demonstrates both Gasoline and Electric factories creating all vehicles and engines.
     */
    public static void runAutomatedShowcase() {
        System.out.println("\n>>> [DEMONSTRATION 1/2] SELECTING ENGINE TYPE: GASOLINE");
        System.out.println("Initializing GasolineVehicleFactory through IVehicleFactory interface...");
        
        IVehicleFactory gasolineFactory = new GasolineVehicleFactory();
        System.out.println("Active Factory: " + gasolineFactory.getFactoryType());

        // 1. Create Gasoline Car
        System.out.println("\n[1] Creating Vehicle: GasolineCar...");
        IVehicle gasCar = gasolineFactory.createCar();
        gasCar.ShowDetails();
        System.out.println("Calling vehicle engine Start():");
        gasCar.getEngine().Start();

        // 2. Create Gasoline Truck
        System.out.println("\n[2] Creating Vehicle: GasolineTruck...");
        IVehicle gasTruck = gasolineFactory.createTruck();
        gasTruck.ShowDetails();
        System.out.println("Calling vehicle engine Start():");
        gasTruck.getEngine().Start();

        // 3. Create Standalone Gasoline Engine
        System.out.println("\n[3] Creating Standalone Engine: GasolineEngine...");
        IEngine gasEngine = gasolineFactory.createEngine();
        System.out.printf("Engine Description: %s%n", gasEngine);
        System.out.println("Starting standalone engine:");
        gasEngine.Start();
        gasEngine.Stop();


        System.out.println("\n\n>>> [DEMONSTRATION 2/2] SELECTING ENGINE TYPE: ELECTRIC");
        System.out.println("Initializing ElectricVehicleFactory through IVehicleFactory interface...");

        IVehicleFactory electricFactory = new ElectricVehicleFactory();
        System.out.println("Active Factory: " + electricFactory.getFactoryType());

        // 1. Create Electric Car
        System.out.println("\n[1] Creating Vehicle: ElectricCar...");
        IVehicle electricCar = electricFactory.createCar();
        electricCar.ShowDetails();
        System.out.println("Calling vehicle engine Start():");
        electricCar.getEngine().Start();

        // 2. Create Electric Truck
        System.out.println("\n[2] Creating Vehicle: ElectricTruck...");
        IVehicle electricTruck = electricFactory.createTruck();
        electricTruck.ShowDetails();
        System.out.println("Calling vehicle engine Start():");
        electricTruck.getEngine().Start();

        // 3. Create Standalone Electric Engine
        System.out.println("\n[3] Creating Standalone Engine: ElectricEngine...");
        IEngine electricEngine = electricFactory.createEngine();
        System.out.printf("Engine Description: %s%n", electricEngine);
        System.out.println("Starting standalone engine:");
        electricEngine.Start();
        electricEngine.Stop();


        System.out.println("\n\n>>> [DEMONSTRATION 3] DECOUPLED CLIENT ASSEMBLY PIPELINE");
        VehicleClient client = new VehicleClient(electricFactory);
        client.orderCar();
        client.inspectAndTestFleet();

        System.out.println("\n======================================================================");
        System.out.println("             ABSTRACT FACTORY DEMONSTRATION COMPLETE                  ");
        System.out.println("======================================================================");
    }

    /**
     * Interactive console session allowing user to dynamically select engine type and vehicle.
     */
    public static void runInteractiveSession() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- INTERACTIVE VEHICLE CONFIGURATOR ---");

        System.out.println("Select Engine Type:");
        System.out.println("  [1] Gasoline Internal Combustion Engine");
        System.out.println("  [2] Electric Zero-Emission Powertrain");
        System.out.print("Enter selection (1 or 2): ");
        String engineChoice = scanner.nextLine().trim();

        IVehicleFactory selectedFactory;
        if ("1".equals(engineChoice) || "gasoline".equalsIgnoreCase(engineChoice)) {
            selectedFactory = new GasolineVehicleFactory();
        } else if ("2".equals(engineChoice) || "electric".equalsIgnoreCase(engineChoice)) {
            selectedFactory = new ElectricVehicleFactory();
        } else {
            System.out.println("[Warning] Invalid selection. Defaulting to Electric Powertrain Factory.");
            selectedFactory = new ElectricVehicleFactory();
        }

        System.out.printf("%n[Confirmed] Connected to: %s%n", selectedFactory.getFactoryType());

        System.out.println("\nSelect Vehicle Type to Manufacture:");
        System.out.println("  [1] Passenger Car");
        System.out.println("  [2] Utility Truck");
        System.out.println("  [3] Standalone Engine Unit");
        System.out.println("  [4] Full Production Package (Car + Truck + Engine)");
        System.out.print("Enter selection (1-4): ");
        String vehicleChoice = scanner.nextLine().trim();

        System.out.println("\n--------------------------------------------------");
        System.out.println("               MANUFACTURING REPORT               ");
        System.out.println("--------------------------------------------------");

        switch (vehicleChoice) {
            case "1": {
                IVehicle car = selectedFactory.createCar();
                car.ShowDetails();
                System.out.println("\nTesting Ignition Sequence:");
                car.getEngine().Start();
                car.drive();
                break;
            }
            case "2": {
                IVehicle truck = selectedFactory.createTruck();
                truck.ShowDetails();
                System.out.println("\nTesting Ignition Sequence:");
                truck.getEngine().Start();
                truck.drive();
                break;
            }
            case "3": {
                IEngine engine = selectedFactory.createEngine();
                System.out.printf("Engine Fabricated: %s%n", engine);
                System.out.println("\nTesting Standalone Bench Ignition:");
                engine.Start();
                engine.Stop();
                break;
            }
            case "4":
            default: {
                VehicleClient client = new VehicleClient(selectedFactory);
                client.manufactureCompleteFleet();
                client.inspectAndTestFleet();
                break;
            }
        }

        System.out.println("\nVehicle configuration and test run finished successfully!");
    }

    /**
     * Handles non-interactive direct command line execution.
     */
    private static void handleCliExecution(String engineType, String vehicleType) {
        IVehicleFactory factory;
        if (engineType.toLowerCase().contains("gas")) {
            factory = new GasolineVehicleFactory();
        } else if (engineType.toLowerCase().contains("elec")) {
            factory = new ElectricVehicleFactory();
        } else {
            System.err.println("Error: Unknown engine type '" + engineType + "'. Use 'gasoline' or 'electric'.");
            System.exit(1);
            return;
        }

        System.out.println("Selected Factory: " + factory.getFactoryType());

        if ("car".equalsIgnoreCase(vehicleType)) {
            IVehicle car = factory.createCar();
            car.ShowDetails();
            car.getEngine().Start();
        } else if ("truck".equalsIgnoreCase(vehicleType)) {
            IVehicle truck = factory.createTruck();
            truck.ShowDetails();
            truck.getEngine().Start();
        } else if ("engine".equalsIgnoreCase(vehicleType)) {
            IEngine engine = factory.createEngine();
            System.out.println(engine);
            engine.Start();
        } else {
            VehicleClient client = new VehicleClient(factory);
            client.manufactureCompleteFleet();
            client.inspectAndTestFleet();
        }
    }
}
