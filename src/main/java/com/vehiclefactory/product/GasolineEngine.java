package com.vehiclefactory.product;

/**
 * Concrete Product: GasolineEngine
 * 
 * Represents an Internal Combustion Engine powered by gasoline fuel.
 */
public class GasolineEngine implements IEngine {

    private final String engineType;
    private final int horsepower;
    private final double displacementLiters;
    private final String fuelGrade;
    private boolean isRunning;

    /**
     * Default constructor for GasolineEngine with standard 2.5L 4-cylinder specs.
     */
    public GasolineEngine() {
        this("Gasoline ICE 2.5L", 205, 2.5, "95 Octane Premium Gasoline");
    }

    /**
     * Parameterized constructor for custom GasolineEngine configurations.
     * 
     * @param engineType         Descriptive name of the engine
     * @param horsepower         Engine horsepower rating
     * @param displacementLiters Displacement in liters (e.g. 2.5, 5.0)
     * @param fuelGrade          Required fuel grade
     */
    public GasolineEngine(String engineType, int horsepower, double displacementLiters, String fuelGrade) {
        this.engineType = engineType;
        this.horsepower = horsepower;
        this.displacementLiters = displacementLiters;
        this.fuelGrade = fuelGrade;
        this.isRunning = false;
    }

    @Override
    public void Start() {
        if (!isRunning) {
            this.isRunning = true;
            System.out.println("  [Gasoline Engine] Priming fuel injectors... Spark plugs firing! VROOOOM! Engine is idling at 800 RPM.");
        } else {
            System.out.println("  [Gasoline Engine] Engine is already running.");
        }
    }

    @Override
    public void Stop() {
        if (isRunning) {
            this.isRunning = false;
            System.out.println("  [Gasoline Engine] Ignition cut. Fuel injection stopped. Engine has shut down.");
        } else {
            System.out.println("  [Gasoline Engine] Engine is already stopped.");
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
        return fuelGrade;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    public double getDisplacementLiters() {
        return displacementLiters;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    @Override
    public String toString() {
        return String.format("%s (%.1fL, %d HP, %s, Status: %s)",
                engineType, displacementLiters, horsepower, fuelGrade, isRunning ? "RUNNING" : "STOPPED");
    }
}
