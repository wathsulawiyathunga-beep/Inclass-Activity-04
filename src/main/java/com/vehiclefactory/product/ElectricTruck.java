package com.vehiclefactory.product;

/**
 * Concrete Product: ElectricTruck
 * 
 * Represents a heavy-duty electric commercial truck featuring instant electric torque
 * and zero emissions.
 */
public class ElectricTruck implements IVehicle {

    private final String model;
    private final IEngine engine;
    private final double towingCapacityKg;
    private final double payloadCapacityKg;
    private final int estimatedRangeKm;
    private final boolean triMotorAWD;

    /**
     * Default constructor for ElectricTruck.
     */
    public ElectricTruck() {
        this("CyberTitan Heavy Hauler", new ElectricEngine("Tri-Motor Electric AWD Powertrain", 580, 135.0, 800), 7200.0, 2200.0, 500, true);
    }

    /**
     * Parameterized constructor for custom ElectricTruck.
     * 
     * @param model              Model designation
     * @param engine             Installed electric engine
     * @param towingCapacityKg   Max towing capacity in kg
     * @param payloadCapacityKg  Max payload cargo weight in kg
     * @param estimatedRangeKm   Driving range per full charge in km
     * @param triMotorAWD        Tri-Motor All-Wheel Drive configuration flag
     */
    public ElectricTruck(String model, IEngine engine, double towingCapacityKg, double payloadCapacityKg, int estimatedRangeKm, boolean triMotorAWD) {
        this.model = model;
        this.engine = engine != null ? engine : new ElectricEngine();
        this.towingCapacityKg = towingCapacityKg;
        this.payloadCapacityKg = payloadCapacityKg;
        this.estimatedRangeKm = estimatedRangeKm;
        this.triMotorAWD = triMotorAWD;
    }

    @Override
    public void ShowDetails() {
        System.out.println("==================================================");
        System.out.println("  VEHICLE DETAILS: ELECTRIC TRUCK");
        System.out.println("==================================================");
        System.out.printf("  Model Name:         %s%n", model);
        System.out.printf("  Vehicle Category:   %s%n", getVehicleCategory());
        System.out.printf("  Towing Capacity:    %,.1f kg%n", towingCapacityKg);
        System.out.printf("  Payload Capacity:   %,.1f kg%n", payloadCapacityKg);
        System.out.printf("  Estimated Range:    %d km per charge%n", estimatedRangeKm);
        System.out.printf("  Drivetrain:         %s%n", triMotorAWD ? "Tri-Motor All-Wheel Drive (AWD)" : "Dual-Motor RWD");
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
        return "Heavy-Duty Electric Truck";
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
        System.out.printf("[Driving] %s deploying massive instant electric torque! Effortlessly hauling heavy load with zero tailpipe emissions.%n", model);
    }

    public double getTowingCapacityKg() {
        return towingCapacityKg;
    }

    public double getPayloadCapacityKg() {
        return payloadCapacityKg;
    }

    public int getEstimatedRangeKm() {
        return estimatedRangeKm;
    }

    public boolean isTriMotorAWD() {
        return triMotorAWD;
    }

    @Override
    public String toString() {
        return String.format("ElectricTruck[Model='%s', HP=%d, Towing=%.0fkg, Range=%dkm]",
                model, engine.getHorsepower(), towingCapacityKg, estimatedRangeKm);
    }
}
