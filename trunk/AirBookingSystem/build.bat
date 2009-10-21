@echo off
echo Building in progess. Please wait...
path = %PATH%;C:\Program Files\Java\jdk1.6.0_14\bin
cd src
set CLASSPATH=%CLASSPATH%;%CD%
cd model
javac *.java
cd ..
cd ucm
javac *.java
cd ..
cd view
javac *.java
cd ..
echo Process finished.
pause
