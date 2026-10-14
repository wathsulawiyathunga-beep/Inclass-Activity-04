package com.vehiclefactory.factory;

import com.vehiclefactory.product.GasolineCar;
import com.vehiclefactory.product.GasolineEngine;
import com.vehiclefactory.product.GasolineTruck;
import com.vehiclefactory.product.IEngine;
import com.vehiclefactory.product.IVehicle;

/**
 * Concrete Factory: GasolineVehicleFactory
 * 
 * Manufactures vehicle products and engines belonging exclusively
 * to the Gasoline combustion powertrain family.
 */
public class GasolineVehicleFactory implements IVehicleFactory {

    private final String facilityName;

    /**
     * Default constructor.
     */
    public GasolineVehicleFactory() {
        this("Detroit Gasoline Powertrain Plant");
    }

    /**
     * Constructor with custom facility name.
     * 
     * @param facilityName Manufacturing facility designation
     */
    public GasolineVehicleFactory(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public IVehicle createCar() {
        IEngine carEngine = new GasolineEngine("2.5L Turbo 4-Cylinder", 250, 2.5, "95 Octane Gasoline");
        return new GasolineCar("Apex Gasoline Sedan", carEngine, 5, 55.0, "8-Speed Automatic");
    }

    @Override
    public IVehicle createTruck() {
        IEngine truckEngine = new GasolineEngine("5.0L V8 Heavy-Duty", 395, 5.0, "95 Octane Gasoline");
        return new GasolineTruck("Titan Gasoline Hauler HD", truckEngine, 6200.0, 1850.0, 120.0);
    }

    @Override
    public IEngine createEngine() {
        return new GasolineEngine("Standard Gasoline ICE", 220, 2.8, "95 Octane Gasoline");
    }

    /**
     * Creates a custom GasolineCar with specific parameters.
     */
    public GasolineCar createCustomCar(String model, int horsepower, double displacement, int seats) {
        IEngine engine = new GasolineEngine(model + " Custom ICE", horsepower, displacement, "95 Octane Gasoline");
        return new GasolineCar(model, engine, seats, 60.0, "Sport Automatic");
    }

    /**
     * Creates a custom GasolineTruck with specific parameters.
     */
    public GasolineTruck createCustomTruck(String model, int horsepower, double displacement, double towingKg) {
        IEngine engine = new GasolineEngine(model + " Custom V8", horsepower, displacement, "95 Octane Gasoline");
        return new GasolineTruck(model, engine, towingKg, 2000.0, 130.0);
    }

    @Override
    public String getFactoryType() {
        return "Gasoline Powertrain Facility (" + facilityName + ")";
    }

    @Override
    public String toString() {
        return getFactoryType();
    }
}
