package com.vehiclefactory.factory;

import com.vehiclefactory.product.IEngine;
import com.vehiclefactory.product.IVehicle;

/**
 * Abstract Factory Interface: IVehicleFactory
 * 
 * Declares factory methods to create families of related vehicle products
 * (Car, Truck) and engines (Engine) without coupling client code to concrete implementations.
 */
public interface IVehicleFactory {

    /**
     * Creates a passenger car matching this factory's powertrain family.
     * 
     * @return An IVehicle instance of type Car
     */
    IVehicle createCar();

    /**
     * Creates a heavy-duty truck matching this factory's powertrain family.
     * 
     * @return An IVehicle instance of type Truck
     */
    IVehicle createTruck();

    /**
     * Creates an engine matching this factory's powertrain technology.
     * 
     * @return An IEngine instance
     */
    IEngine createEngine();

    /**
     * Convenience factory method to create a vehicle by category name.
     * 
     * @param vehicleType "car" or "truck" (case-insensitive)
     * @return The created IVehicle instance
     * @throws IllegalArgumentException if the vehicleType is unknown
     */
    default IVehicle createVehicle(String vehicleType) {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        String cleanType = vehicleType.trim().toLowerCase();
        switch (cleanType) {
            case "car":
            case "sedan":
                return createCar();
            case "truck":
            case "hauler":
                return createTruck();
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType + ". Supported: 'car', 'truck'");
        }
    }

    /**
     * Returns the name/description of this factory variant family.
     * 
     * @return Factory family description
     */
    String getFactoryType();
}
