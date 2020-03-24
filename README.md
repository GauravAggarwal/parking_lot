# Parking lot

`parking_lot/` contains An automated ticketing system that allows customers to use the parking lot without human intervention.

## Setup

First you will need to install maven to compile and build the application, Run following commands 

```
parking_lot $ apt-get install maven

parking_lot $ mvn -version # confirm Maven present
Apache Maven 3.6.0
Maven home: /usr/share/maven
Java version: 11.0.6, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en_US, platform encoding: ANSI_X3.4-1968
OS name: "linux", version: "4.19.76-linuxkit", arch: "amd64", family: "unix"
```

To Compile and build the application

```
parking_lot $ bin/setup
```

## Usage

You can run the application from `parking_lot` by running following commands in one of the two ways :

1) Inputs commands are read from the file specified

```
parking_lot $ bin/parking_lot src/main/resources/parking_lot_input.txt
```

2) Inputs commands are read from the console

```
parking_lot $ bin/parking_lot
```
