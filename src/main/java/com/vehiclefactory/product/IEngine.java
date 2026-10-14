package com.vehiclefactory.product;

/**
 * Abstract Product Interface for all Engines.
 * 
 * Defines the contract that every engine (Gasoline, Electric, etc.)
 * must fulfill across different vehicle models.
 */
public interface IEngine {

    /**
     * Starts the engine and activates the propulsion system.
     * Required by specification: void Start()
     */
    void Start();

    /**
     * Standard Java naming convention alias delegating to Start().
     */
    default void start() {
        Start();
    }

    /**
     * Shuts down the engine.
     */
    void Stop();

    /**
     * Standard Java naming convention alias delegating to Stop().
     */
    default void stop() {
        Stop();
    }

    /**
     * Returns the engine type / technology description.
     * 
     * @return Engine type
     */
    String getEngineType();

    /**
     * Returns the rated horsepower of the engine.
     * 
     * @return Engine horsepower
     */
    int getHorsepower();

    /**
     * Returns the energy or fuel source (e.g., "Octane Gasoline", "Lithium-Ion Battery").
     * 
     * @return Energy source string
     */
    String getEnergySource();

    /**
     * Checks if the engine is currently active and running.
     * 
     * @return true if running, false otherwise
     */
    boolean isRunning();
}
