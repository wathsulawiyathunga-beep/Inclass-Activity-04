# Architectural Deep Dive: Abstract Factory Pattern in Vehicle Manufacturing

## 1. Context & Architectural Challenge

In vehicle manufacturing systems, vehicles (Cars, Trucks) and their respective powertrains (Gasoline Engines, Electric Motors) belong to distinct technological ecosystems. A naive design often instantiates concrete objects directly:

```java
// Anti-Pattern: Tight Coupling & Incompatible Assemblies
IVehicle car = new GasolineCar();
IEngine engine = new ElectricEngine(); // Risk: Incompatible powertrain pairing!
```

This anti-pattern suffers from three critical flaws:
1. **Tight Coupling:** The client must know every concrete vehicle and engine class in the system.
2. **Configuration Inconsistency:** It is easy to mistakenly pair an electric motor with a gasoline fuel system.
3. **Violation of the Open/Closed Principle (OCP):** Introducing a new powertrain family (e.g., Hydrogen, Hybrid) requires modifying existing client code throughout the codebase.

---

## 2. The Abstract Factory Solution

The **Abstract Factory Pattern** (GoF) provides an interface for creating families of related or dependent objects without specifying their concrete classes.

### Participants:
1. **Abstract Factory (`IVehicleFactory`):**
   Declares abstract methods to create each member of the product family:
   - `createCar() -> IVehicle`
   - `createTruck() -> IVehicle`
   - `createEngine() -> IEngine`

2. **Concrete Factories (`GasolineVehicleFactory`, `ElectricVehicleFactory`):**
   Implement creation methods to produce products belonging exclusively to their specific variant family.

3. **Abstract Products (`IVehicle`, `IEngine`):**
   Define the operational interfaces exposed to consumers.
   - `IVehicle`: `ShowDetails()`, `drive()`, `getModel()`, `getEngine()`
   - `IEngine`: `Start()`, `Stop()`, `getEngineType()`, `getHorsepower()`

4. **Concrete Products:**
   - **Gasoline Family:** `GasolineCar`, `GasolineTruck`, `GasolineEngine`
   - **Electric Family:** `ElectricCar`, `ElectricTruck`, `ElectricEngine`

5. **Client (`VehicleClient`, `Main`):**
   Operates strictly on abstract interfaces. The client receives an `IVehicleFactory` instance at runtime and constructs vehicles without knowing or caring whether the concrete implementation is gasoline or electric.

---

## 3. Product Family Matrix

```
                      +-------------------------------------------------------+
                      |                    PRODUCT FAMILIES                   |
+---------------------+---------------------------+---------------------------+
| PRODUCT CATEGORY    |     GASOLINE FAMILY       |      ELECTRIC FAMILY      |
+---------------------+---------------------------+---------------------------+
| Passenger Car       | GasolineCar               | ElectricCar               |
|                     | - 2.5L Turbo ICE (250 HP) | - Dual Synchronous EV     |
|                     | - 55L Fuel Tank           | - 92 kWh Battery (540 km) |
+---------------------+---------------------------+---------------------------+
| Utility Truck       | GasolineTruck             | ElectricTruck             |
|                     | - 5.0L V8 ICE (395 HP)    | - Tri-Motor AWD (580 HP)  |
|                     | - 6,200 kg Towing         | - 7,200 kg Towing         |
+---------------------+---------------------------+---------------------------+
| Standalone Engine   | GasolineEngine            | ElectricEngine            |
|                     | - Spark Ignition          | - Inverter & Contactors   |
+---------------------+---------------------------+---------------------------+
```

---

## 4. SOLID Principles Alignment

- **Single Responsibility Principle (SRP):** Each concrete factory is responsible solely for producing its own product family. Product classes manage only their vehicle or engine behavior.
- **Open/Closed Principle (OCP):** New powertrain families (e.g., `HydrogenVehicleFactory` producing `HydrogenCar`, `HydrogenTruck`, `HydrogenEngine`) can be added without altering existing code.
- **Liskov Substitution Principle (LSP):** Any concrete factory implementing `IVehicleFactory` can seamlessly substitute for another in `VehicleClient`.
- **Interface Segregation Principle (ISP):** Clear, focused interfaces (`IVehicle`, `IEngine`, `IVehicleFactory`) avoid bloated "god" interfaces.
- **Dependency Inversion Principle (DIP):** High-level modules (`VehicleClient`, `Main`) depend on abstractions (`IVehicleFactory`, `IVehicle`, `IEngine`), never directly on low-level concrete classes (`GasolineCar`, `ElectricEngine`).

---

## 5. Specification Compliance Notes

The assignment specification defines:
- `IVehicle` with `void ShowDetails()`
- `IEngine` with `void Start();`

To ensure 100% compliance with both the assignment's PascalCase method names and standard Java camelCase conventions:
- `ShowDetails()` and `Start()` are defined directly in the interfaces.
- Default methods `showDetails()` and `start()` are provided, delegating seamlessly to `ShowDetails()` and `Start()`.
- Standard compilation with `javac` and execution with `java` require zero third-party dependencies, making it universally portable across NetBeans, Eclipse, IntelliJ, Command Prompt, and PowerShell.
