package com.vehiclefactory.product;

/**
 * Concrete Product: GasolineCar
 * 
 * Represents a passenger car powered by a gasoline internal combustion engine.
 */
public class GasolineCar implements IVehicle {

    private final String model;
    private final IEngine engine;
    private final int seatingCapacity;
    private final double fuelTankCapacityLiters;
    private final String transmission;

    /**
     * Default constructor for GasolineCar.
     */
    public GasolineCar() {
        this("Apex Sedan GT", new GasolineEngine("2.5L Turbocharged Inline-4", 250, 2.5, "95 Octane Gasoline"), 5, 55.0, "8-Speed Automatic");
    }

    /**
     * Parameterized constructor for custom GasolineCar.
     * 
     * @param model                  Model designation
     * @param engine                 Installed gasoline engine
     * @param seatingCapacity        Number of passenger seats
     * @param fuelTankCapacityLiters Fuel capacity in liters
     * @param transmission           Transmission type
     */
    public GasolineCar(String model, IEngine engine, int seatingCapacity, double fuelTankCapacityLiters, String transmission) {
        this.model = model;
        this.engine = engine != null ? engine : new GasolineEngine();
        this.seatingCapacity = seatingCapacity;
        this.fuelTankCapacityLiters = fuelTankCapacityLiters;
        this.transmission = transmission;
    }

    @Override
    public void ShowDetails() {
        System.out.println("==================================================");
        System.out.println("  VEHICLE DETAILS: GASOLINE CAR");
        System.out.println("==================================================");
        System.out.printf("  Model Name:         %s%n", model);
        System.out.printf("  Vehicle Category:   %s%n", getVehicleCategory());
        System.out.printf("  Seating Capacity:   %d Passengers%n", seatingCapacity);
        System.out.printf("  Fuel Tank Capacity: %.1f Liters%n", fuelTankCapacityLiters);
        System.out.printf("  Transmission:       %s%n", transmission);
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
        return "Passenger Car (Sedan)";
    }

    @Override
    public IEngine getEngine() {
        return engine;
    }

    @Override
    public void drive() {
        if (!engine.isRunning()) {
            System.out.println("[Driving Alert] Engine is offline. Auto-starting engine before drive...");
            engine.Start();
        }
        System.out.printf("[Driving] %s accelerating smoothly! Exhaust notes rumbling through the dual pipes.%n", model);
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public double getFuelTankCapacityLiters() {
        return fuelTankCapacityLiters;
    }

    public String getTransmission() {
        return transmission;
    }

    @Override
    public String toString() {
        return String.format("GasolineCar[Model='%s', HP=%d, Seats=%d, Tank=%.1fL]",
                model, engine.getHorsepower(), seatingCapacity, fuelTankCapacityLiters);
    }
}
