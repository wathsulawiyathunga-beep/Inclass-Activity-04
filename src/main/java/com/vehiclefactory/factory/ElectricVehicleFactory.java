package com.vehiclefactory.factory;

import com.vehiclefactory.product.ElectricCar;
import com.vehiclefactory.product.ElectricEngine;
import com.vehiclefactory.product.ElectricTruck;
import com.vehiclefactory.product.IEngine;
import com.vehiclefactory.product.IVehicle;

/**
 * Concrete Factory: ElectricVehicleFactory
 * 
 * Manufactures vehicle products and engines belonging exclusively
 * to the Zero-Emission Electric powertrain family.
 */
public class ElectricVehicleFactory implements IVehicleFactory {

    private final String facilityName;

    /**
     * Default constructor.
     */
    public ElectricVehicleFactory() {
        this("Fremont Clean-Energy Gigafactory");
    }

    /**
     * Constructor with custom facility name.
     * 
     * @param facilityName Manufacturing facility designation
     */
    public ElectricVehicleFactory(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public IVehicle createCar() {
        IEngine carEngine = new ElectricEngine("Dual Permanent Magnet Synchronous Motors", 420, 92.0, 800);
        return new ElectricCar("VoltStream Electric Aero Sedan", carEngine, 5, 540, 3.2);
    }

    @Override
    public IVehicle createTruck() {
        IEngine truckEngine = new ElectricEngine("Tri-Motor Electric AWD Powertrain", 580, 135.0, 800);
        return new ElectricTruck("CyberTitan Electric Heavy Hauler", truckEngine, 7200.0, 2200.0, 500, true);
    }

    @Override
    public IEngine createEngine() {
        return new ElectricEngine("Standard Electric Drive Unit", 320, 75.0, 400);
    }

    /**
     * Creates a custom ElectricCar with specific parameters.
     */
    public ElectricCar createCustomCar(String model, int horsepower, double batteryKWh, int rangeKm) {
        IEngine engine = new ElectricEngine(model + " Custom EV Motor", horsepower, batteryKWh, 800);
        return new ElectricCar(model, engine, 5, rangeKm, 2.9);
    }

    /**
     * Creates a custom ElectricTruck with specific parameters.
     */
    public ElectricTruck createCustomTruck(String model, int horsepower, double batteryKWh, double towingKg) {
        IEngine engine = new ElectricEngine(model + " Heavy EV Powertrain", horsepower, batteryKWh, 800);
        return new ElectricTruck(model, engine, towingKg, 2500.0, 480, true);
    }

    @Override
    public String getFactoryType() {
        return "Electric Powertrain Gigafactory (" + facilityName + ")";
    }

    @Override
    public String toString() {
        return getFactoryType();
    }
}
