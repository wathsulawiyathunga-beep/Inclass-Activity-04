@echo off
setlocal

echo ======================================================================
echo       VEHICLE MANUFACTURING SYSTEM - UNIT TESTS RUNNER
echo ======================================================================

:: Create output directory
if not exist "bin" mkdir "bin"

:: Compile Java files including test suite
echo [1/2] Compiling source and test files...
javac -d bin src/main/java/com/vehiclefactory/product/*.java src/main/java/com/vehiclefactory/factory/*.java src/main/java/com/vehiclefactory/client/*.java src/main/java/com/vehiclefactory/main/*.java src/test/java/com/vehiclefactory/*.java
if %errorlevel% neq 0 (
    echo [ERROR] Test compilation failed!
    pause
    exit /b %errorlevel%
)
echo [SUCCESS] Compilation completed into bin/

:: Run test suite
echo [2/2] Running VehicleFactoryTest suite...
echo ======================================================================
java -cp bin com.vehiclefactory.VehicleFactoryTest

pause
