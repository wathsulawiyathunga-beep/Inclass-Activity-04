package com.vehiclefactory.client;

import com.vehiclefactory.factory.IVehicleFactory;
import com.vehiclefactory.product.IEngine;
import com.vehiclefactory.product.IVehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Client: VehicleClient (Assembly Line Coordinator)
 * 
 * Demonstrates decoupling achieved through the Abstract Factory pattern.
 * This class interacts STRICTLY with the abstract interfaces (IVehicleFactory,
 * IVehicle, IEngine) and has zero compile-time dependencies on concrete product
 * classes like GasolineCar or ElectricTruck.
 */
public class VehicleClient {

    private final IVehicleFactory factory;
    private final List<IVehicle> producedVehicles;
    private final List<IEngine> producedEngines;

    /**
     * Initializes the client with an injected concrete factory.
     * 
     * @param factory Concrete factory implementing IVehicleFactory
     */
    public VehicleClient(IVehicleFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        this.factory = factory;
        this.producedVehicles = new ArrayList<>();
        this.producedEngines = new ArrayList<>();
    }

    /**
     * Orders the factory to produce a passenger car.
     * 
     * @return Newly created IVehicle car
     */
    public IVehicle orderCar() {
        System.out.printf("[Order Pipeline] Manufacturing car via: %s...%n", factory.getFactoryType());
        IVehicle car = factory.createCar();
        producedVehicles.add(car);
        return car;
    }

    /**
     * Orders the factory to produce a heavy-duty truck.
     * 
     * @return Newly created IVehicle truck
     */
    public IVehicle orderTruck() {
        System.out.printf("[Order Pipeline] Manufacturing truck via: %s...%n", factory.getFactoryType());
        IVehicle truck = factory.createTruck();
        producedVehicles.add(truck);
        return truck;
    }

    /**
     * Orders the factory to produce a standalone engine unit.
     * 
     * @return Newly created IEngine
     */
    public IEngine orderStandaloneEngine() {
        System.out.printf("[Order Pipeline] Assembling standalone engine via: %s...%n", factory.getFactoryType());
        IEngine engine = factory.createEngine();
        producedEngines.add(engine);
        return engine;
    }

    /**
     * Produces a complete vehicle family batch (Car + Truck + Standalone Engine).
     */
    public void manufactureCompleteFleet() {
        System.out.println("\n--------------------------------------------------");
        System.out.printf("Starting Full Production Cycle on [%s]%n", factory.getFactoryType());
        System.out.println("--------------------------------------------------");
        orderCar();
        orderTruck();
        orderStandaloneEngine();
    }

    /**
     * Inspects every produced vehicle in the fleet by invoking ShowDetails() and Start().
     */
    public void inspectAndTestFleet() {
        System.out.println("\n==================================================");
        System.out.println("       QUALITY ASSURANCE & FLEET INSPECTION       ");
        System.out.println("==================================================");

        if (producedVehicles.isEmpty()) {
            System.out.println("No vehicles currently in production queue.");
            return;
        }

        for (int i = 0; i < producedVehicles.size(); i++) {
            IVehicle vehicle = producedVehicles.get(i);
            System.out.printf("%n[Inspection Item #%d] %s%n", i + 1, vehicle.getModel());
            vehicle.ShowDetails();

            System.out.println("  Testing Engine Ignition Sequence:");
            vehicle.getEngine().Start();

            System.out.println("  Conducting Proving Grounds Test Track Drive:");
            vehicle.drive();
        }

        if (!producedEngines.isEmpty()) {
            System.out.println("\n--- Standalone Crate Engine Benchmarks ---");
            for (IEngine engine : producedEngines) {
                System.out.printf("Dynamometer Testing: %s%n", engine.getEngineType());
                engine.Start();
                engine.Stop();
            }
        }
    }

    public IVehicleFactory getFactory() {
        return factory;
    }

    public List<IVehicle> getProducedVehicles() {
        return Collections.unmodifiableList(producedVehicles);
    }

    public List<IEngine> getProducedEngines() {
        return Collections.unmodifiableList(producedEngines);
    }
}
