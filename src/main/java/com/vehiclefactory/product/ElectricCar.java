package com.vehiclefactory.product;

/**
 * Concrete Product: ElectricCar
 * 
 * Represents an advanced zero-emission passenger car powered by an electric motor powertrain.
 */
public class ElectricCar implements IVehicle {

    private final String model;
    private final IEngine engine;
    private final int seatingCapacity;
    private final int estimatedRangeKm;
    private final double acceleration0to100Sec;

    /**
     * Default constructor for ElectricCar.
     */
    public ElectricCar() {
        this("VoltStream Aero GT", new ElectricEngine("Dual Permanent Magnet Synchronous Motors", 420, 92.0, 800), 5, 540, 3.2);
    }

    /**
     * Parameterized constructor for custom ElectricCar.
     * 
     * @param model                 Model designation
     * @param engine                Installed electric motor engine
     * @param seatingCapacity       Number of passenger seats
     * @param estimatedRangeKm      Driving range per full charge in km
     * @param acceleration0to100Sec 0-100 km/h acceleration time in seconds
     */
    public ElectricCar(String model, IEngine engine, int seatingCapacity, int estimatedRangeKm, double acceleration0to100Sec) {
        this.model = model;
        this.engine = engine != null ? engine : new ElectricEngine();
        this.seatingCapacity = seatingCapacity;
        this.estimatedRangeKm = estimatedRangeKm;
        this.acceleration0to100Sec = acceleration0to100Sec;
    }

    @Override
    public void ShowDetails() {
        System.out.println("==================================================");
        System.out.println("  VEHICLE DETAILS: ELECTRIC CAR");
        System.out.println("==================================================");
        System.out.printf("  Model Name:         %s%n", model);
        System.out.printf("  Vehicle Category:   %s%n", getVehicleCategory());
        System.out.printf("  Seating Capacity:   %d Passengers%n", seatingCapacity);
        System.out.printf("  Estimated Range:    %d km per charge%n", estimatedRangeKm);
        System.out.printf("  0-100 km/h:         %.1f seconds (Ludicrous Mode)%n", acceleration0to100Sec);
        System.out.println("  --- Engine Specifications ---");
        System.out.printf("  Engine Type:        %s%n", engine.getEngineType());
        System.out.printf("  Horsepower:         %d HP%n", engine.getHorsepower());
        System.out.printf("  Energy Source:      %s%n", engine.getEnergySource());
        System.out.printf("  Engine Status:      %s%n", engine.isRunning() ? "ACTIVE" : "OFFLINE");
        System.out.println("==================================================");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getVehicleCategory() {
        return "Electric Passenger Sedan";
    }

    @Override
    public IEngine getEngine() {
        return engine;
    }

    @Override
    public void drive() {
        if (!engine.isRunning()) {
            System.out.println("[Driving Alert] Electric powertrain is offline. Initializing contactors...");
            engine.Start();
        }
        System.out.printf("[Driving] %s accelerating silently from 0 to 100 km/h in %.1fs with instantaneous electric torque!%n",
                model, acceleration0to100Sec);
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public int getEstimatedRangeKm() {
        return estimatedRangeKm;
    }

    public double getAcceleration0to100Sec() {
        return acceleration0to100Sec;
    }

    @Override
    public String toString() {
        return String.format("ElectricCar[Model='%s', HP=%d, Range=%dkm, 0-100=%.1fs]",
                model, engine.getHorsepower(), estimatedRangeKm, acceleration0to100Sec);
    }
}
