Write-Host "======================================================================" -ForegroundColor Cyan
Write-Host "       VEHICLE MANUFACTURING SYSTEM (Abstract Factory Pattern)        " -ForegroundColor Cyan
Write-Host "======================================================================" -ForegroundColor Cyan

if (!(Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

Write-Host "[1/2] Compiling Java source files..." -ForegroundColor Yellow
$sources = Get-ChildItem -Path "src\main\java" -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName
& javac -d bin $sources

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Compilation failed!" -ForegroundColor Red
    exit $LASTEXITCODE
}
Write-Host "[SUCCESS] Compilation completed into bin/" -ForegroundColor Green

Write-Host "[2/2] Running Vehicle Manufacturing System..." -ForegroundColor Yellow
Write-Host "======================================================================" -ForegroundColor Cyan
& java -cp bin com.vehiclefactory.main.Main $args
