package com.vehiclefactory.product;

/**
 * Concrete Product: ElectricEngine
 * 
 * Represents an Electric Motor powertrain powered by high-voltage battery packs.
 */
public class ElectricEngine implements IEngine {

    private final String engineType;
    private final int horsepower;
    private final double batteryCapacityKWh;
    private final int voltageArchitecture;
    private boolean isRunning;

    /**
     * Default constructor for ElectricEngine with standard 85 kWh dual-motor specs.
     */
    public ElectricEngine() {
        this("Permanent Magnet Synchronous Electric Motor", 360, 85.0, 800);
    }

    /**
     * Parameterized constructor for custom ElectricEngine configurations.
     * 
     * @param engineType           Descriptive name of the electric motor
     * @param horsepower           Engine horsepower equivalent
     * @param batteryCapacityKWh   Battery pack size in kilowatt-hours
     * @param voltageArchitecture  Operating voltage system (e.g., 400V or 800V)
     */
    public ElectricEngine(String engineType, int horsepower, double batteryCapacityKWh, int voltageArchitecture) {
        this.engineType = engineType;
        this.horsepower = horsepower;
        this.batteryCapacityKWh = batteryCapacityKWh;
        this.voltageArchitecture = voltageArchitecture;
        this.isRunning = false;
    }

    @Override
    public void Start() {
        if (!isRunning) {
            this.isRunning = true;
            System.out.println("  [Electric Engine] High-voltage contactors closed (CLACK)! Inverter hum active (Whirrrr...). Instant peak torque available (100% silent).");
        } else {
            System.out.println("  [Electric Engine] Electric drive unit is already active.");
        }
    }

    @Override
    public void Stop() {
        if (isRunning) {
            this.isRunning = false;
            System.out.println("  [Electric Engine] High-voltage relay disengaged. Electric drive unit shut down silently.");
        } else {
            System.out.println("  [Electric Engine] Electric drive unit is already offline.");
        }
    }

    @Override
    public String getEngineType() {
        return engineType;
    }

    @Override
    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public String getEnergySource() {
        return String.format("%.1f kWh Li-ion Battery (%dV)", batteryCapacityKWh, voltageArchitecture);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    public double getBatteryCapacityKWh() {
        return batteryCapacityKWh;
    }

    public int getVoltageArchitecture() {
        return voltageArchitecture;
    }

    @Override
    public String toString() {
        return String.format("%s (%.1f kWh, %d HP, %dV, Status: %s)",
                engineType, batteryCapacityKWh, horsepower, voltageArchitecture, isRunning ? "ACTIVE" : "OFFLINE");
    }
}
