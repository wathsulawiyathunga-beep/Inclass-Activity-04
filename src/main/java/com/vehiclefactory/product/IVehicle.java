package com.vehiclefactory.product;

/**
 * Abstract Product Interface for all Vehicles.
 * 
 * Defines the contract that every concrete vehicle (Cars, Trucks, etc.)
 * must fulfill, regardless of the powertrain/engine family.
 */
public interface IVehicle {

    /**
     * Displays complete specifications and details of the vehicle.
     * Required by specification: void ShowDetails()
     */
    void ShowDetails();

    /**
     * Standard Java naming convention alias delegating to ShowDetails().
     */
    default void showDetails() {
        ShowDetails();
    }

    /**
     * Returns the model designation of the vehicle.
     * 
     * @return Vehicle model name
     */
    String getModel();

    /**
     * Returns the category of vehicle (e.g., "Car", "Truck").
     * 
     * @return Vehicle category
     */
    String getVehicleCategory();

    /**
     * Returns the engine installed in this vehicle.
     * 
     * @return Installed IEngine instance
     */
    IEngine getEngine();

    /**
     * Simulates driving the vehicle.
     */
    void drive();
}
