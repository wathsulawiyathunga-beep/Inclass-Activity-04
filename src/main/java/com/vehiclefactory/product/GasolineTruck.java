package com.vehiclefactory.product;

/**
 * Concrete Product: GasolineTruck
 * 
 * Represents a heavy-duty commercial/cargo truck powered by a high-torque gasoline engine.
 */
public class GasolineTruck implements IVehicle {

    private final String model;
    private final IEngine engine;
    private final double towingCapacityKg;
    private final double payloadCapacityKg;
    private final double fuelTankCapacityLiters;

    /**
     * Default constructor for GasolineTruck.
     */
    public GasolineTruck() {
        this("Titan Hauler HD-650", new GasolineEngine("5.0L V8 Heavy-Duty", 395, 5.0, "95 Octane Gasoline"), 6200.0, 1850.0, 120.0);
    }

    /**
     * Parameterized constructor for custom GasolineTruck.
     * 
     * @param model                  Model designation
     * @param engine                 Installed gasoline engine
     * @param towingCapacityKg       Max towing capacity in kg
     * @param payloadCapacityKg      Max payload cargo weight in kg
     * @param fuelTankCapacityLiters Fuel capacity in liters
     */
    public GasolineTruck(String model, IEngine engine, double towingCapacityKg, double payloadCapacityKg, double fuelTankCapacityLiters) {
        this.model = model;
        this.engine = engine != null ? engine : new GasolineEngine();
        this.towingCapacityKg = towingCapacityKg;
        this.payloadCapacityKg = payloadCapacityKg;
        this.fuelTankCapacityLiters = fuelTankCapacityLiters;
    }

    @Override
    public void ShowDetails() {
        System.out.println("==================================================");
        System.out.println("  VEHICLE DETAILS: GASOLINE TRUCK");
        System.out.println("==================================================");
        System.out.printf("  Model Name:         %s%n", model);
        System.out.printf("  Vehicle Category:   %s%n", getVehicleCategory());
        System.out.printf("  Towing Capacity:    %,.1f kg%n", towingCapacityKg);
        System.out.printf("  Payload Capacity:   %,.1f kg%n", payloadCapacityKg);
        System.out.printf("  Fuel Tank Capacity: %.1f Liters%n", fuelTankCapacityLiters);
        System.out.println("  --- Engine Specifications ---");
        System.out.printf("  Engine Type:        %s%n", engine.getEngineType());
        System.out.printf("  Horsepower:         %d HP%n", engine.getHorsepower());
        System.out.printf("  Fuel/Energy Source: %s%n", engine.getEnergySource());
        System.out.printf("  Engine Status:      %s%n", engine.isRunning() ? "RUNNING" : "STOPPED");
        System.out.println("==================================================");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getVehicleCategory() {
        return "Heavy-Duty Utility Truck";
    }

    @Override
    public IEngine getEngine() {
        return engine;
    }

    @Override
    public void drive() {
        if (!engine.isRunning()) {
            System.out.println("[Driving Alert] Engine is offline. Auto-starting truck engine...");
            engine.Start();
        }
        System.out.printf("[Driving] %s hauling cargo with high-displacement V8 muscle! Robust torque engaged.%n", model);
    }

    public double getTowingCapacityKg() {
        return towingCapacityKg;
    }

    public double getPayloadCapacityKg() {
        return payloadCapacityKg;
    }

    public double getFuelTankCapacityLiters() {
        return fuelTankCapacityLiters;
    }

    @Override
    public String toString() {
        return String.format("GasolineTruck[Model='%s', HP=%d, Towing=%.0fkg, Payload=%.0fkg]",
                model, engine.getHorsepower(), towingCapacityKg, payloadCapacityKg);
    }
}
