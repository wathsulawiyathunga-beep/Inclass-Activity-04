Write-Host "======================================================================" -ForegroundColor Cyan
Write-Host "       VEHICLE MANUFACTURING SYSTEM - UNIT TESTS RUNNER               " -ForegroundColor Cyan
Write-Host "======================================================================" -ForegroundColor Cyan

if (!(Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

Write-Host "[1/2] Compiling source and test files..." -ForegroundColor Yellow
$sources = Get-ChildItem -Path "src" -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName
& javac -d bin $sources

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Test compilation failed!" -ForegroundColor Red
    exit $LASTEXITCODE
}
Write-Host "[SUCCESS] Compilation completed into bin/" -ForegroundColor Green

Write-Host "[2/2] Running VehicleFactoryTest suite..." -ForegroundColor Yellow
Write-Host "======================================================================" -ForegroundColor Cyan
& java -cp bin com.vehiclefactory.VehicleFactoryTest
