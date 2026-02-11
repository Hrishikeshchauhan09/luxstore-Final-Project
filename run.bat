@echo off
echo Starting LuxeStore Application...
echo.

cd /d "%~dp0"

echo Checking Java version...
java -version
echo.

echo Running application on port 8081...
java -Dserver.port=8081 -jar target\Luxestore-p2-0.0.1-SNAPSHOT.jar

pause
