package org.firstinspires.ftc.teamcode.Autonomous;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class FieldModel {
    final double cmPerTile = 61;

    double maxHeight;
    public double getMaxHeight() {
        return maxHeight;
    }

    double maxWidth;
    public double getMaxWidth() {
        return maxWidth;
    }

    List<Coordinates> lowerJunctions;
    public List<Coordinates> getLowerJunctions() {
        return lowerJunctions;
    }

    List<Coordinates> mediumJunctions;
    public List<Coordinates> getMediumJunctions() {
        return mediumJunctions;
    }

    List<Coordinates> higherJunctions;
    public List<Coordinates> getHigherJunctions() {
        return higherJunctions;
    }

    Coordinates startingPosition;
    public Coordinates getStartingPosition() {
        return startingPosition;
    }

    Coordinates parkingCone;
    public Coordinates getParkingCone() {
        return parkingCone;
    }

    Coordinates firstParkingPosition;
    public Coordinates getFirstParkingPosition() {
        return firstParkingPosition;
    }

    Coordinates secondParkingPosition;
    public Coordinates getSecondParkingPosition() {
        return secondParkingPosition;
    }
    Coordinates thirdParkingPosition;
    public Coordinates getThirdParkingPosition() {
        return thirdParkingPosition;
    }



    public FieldModel(Coordinates startingPosition, Coordinates parkingCone,
                      Coordinates firstParkingPosition, Coordinates secondParkingPosition,
                      Coordinates thirdParkingPosition) {
        this.startingPosition = startingPosition;
        this.parkingCone = parkingCone;
        this.firstParkingPosition = firstParkingPosition;
        this.secondParkingPosition = secondParkingPosition;
        this.thirdParkingPosition = thirdParkingPosition;

        maxHeight = 6*cmPerTile;
        maxWidth = 6*cmPerTile;
        lowerJunctions = new ArrayList<>();
        lowerJunctions.add(new Coordinates(1*cmPerTile, 2*cmPerTile));
        lowerJunctions.add(new Coordinates(2*cmPerTile, 1*cmPerTile));
        lowerJunctions.add(new Coordinates(1*cmPerTile, 4*cmPerTile));
        lowerJunctions.add(new Coordinates(4*cmPerTile, 1*cmPerTile));
        lowerJunctions.add(new Coordinates(2*cmPerTile, 5*cmPerTile));
        lowerJunctions.add(new Coordinates(4*cmPerTile, 5*cmPerTile));
        lowerJunctions.add(new Coordinates(5*cmPerTile, 4*cmPerTile));
        lowerJunctions.add(new Coordinates(5*cmPerTile, 2*cmPerTile));

        mediumJunctions = new ArrayList<>();
        mediumJunctions.add(new Coordinates(2*cmPerTile, 2*cmPerTile));
        mediumJunctions.add(new Coordinates(2*cmPerTile, 4*cmPerTile));
        mediumJunctions.add(new Coordinates(4*cmPerTile, 4*cmPerTile));
        mediumJunctions.add(new Coordinates(4*cmPerTile, 2*cmPerTile));

        higherJunctions = new ArrayList<>();
        higherJunctions.add(new Coordinates(3*cmPerTile, 2*cmPerTile));
        higherJunctions.add(new Coordinates(2*cmPerTile, 3*cmPerTile));
        higherJunctions.add(new Coordinates(3*cmPerTile, 4*cmPerTile));
        higherJunctions.add(new Coordinates(4*cmPerTile, 3*cmPerTile));
    }
}
