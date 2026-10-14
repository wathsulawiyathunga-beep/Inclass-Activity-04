@echo off
setlocal

echo ======================================================================
echo       VEHICLE MANUFACTURING SYSTEM (Abstract Factory Pattern)
echo ======================================================================

:: Create output directory
if not exist "bin" mkdir "bin"

:: Compile Java files
echo [1/2] Compiling Java source files...
javac -d bin src/main/java/com/vehiclefactory/product/*.java src/main/java/com/vehiclefactory/factory/*.java src/main/java/com/vehiclefactory/client/*.java src/main/java/com/vehiclefactory/main/*.java
if %errorlevel% neq 0 (
    echo [ERROR] Compilation failed!
    pause
    exit /b %errorlevel%
)
echo [SUCCESS] Compilation completed into bin/

:: Run the application
echo [2/2] Running Vehicle Manufacturing System...
echo ======================================================================
java -cp bin com.vehiclefactory.main.Main %*

pause
