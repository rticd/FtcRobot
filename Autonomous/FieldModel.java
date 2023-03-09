package org.firstinspires.ftc.teamcode.Autonomous;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class FieldModel {
    final double  cmPerTile = 61;
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

    Coordinates coloredConeVectorFromStatingPosition;
    public Coordinates getColoredConeVectorFromStatingPosition() {
        return coloredConeVectorFromStatingPosition;
    }
    Coordinates redVectorFromColoredCone;
    public Coordinates getRedVectorFromColoredCone() {
        return redVectorFromColoredCone;
    }

    Coordinates blueVectorFromColoredCone;
    public Coordinates getBlueVectorFromColoredCone() {
        return blueVectorFromColoredCone;
    }

    Coordinates greenVectorFromColoredCone;
    public Coordinates getGreenVectorFromColoredCone() {
        return greenVectorFromColoredCone;
    }



    public FieldModel(Coordinates startingPosition, Coordinates coloredConeVectorFromStatingPosition,
                      Coordinates redVectorFromColoredCone, Coordinates greenVectorFromColoredCone,
                      Coordinates blueVectorFromColoredCone) {
        this.startingPosition = startingPosition;
        this.coloredConeVectorFromStatingPosition = coloredConeVectorFromStatingPosition;
        this.redVectorFromColoredCone = redVectorFromColoredCone;
        this.greenVectorFromColoredCone = greenVectorFromColoredCone;
        this.blueVectorFromColoredCone = blueVectorFromColoredCone;

        maxHeight = 6*cmPerTile;
        maxWidth = 6*cmPerTile;
        lowerJunctions = new ArrayList<>();
        lowerJunctions.add(new Coordinates(1*cmPerTile,1*cmPerTile));
        lowerJunctions.add(new Coordinates(1*cmPerTile,3*cmPerTile));
        lowerJunctions.add(new Coordinates(1*cmPerTile,5*cmPerTile));
        lowerJunctions.add(new Coordinates(3*cmPerTile,1*cmPerTile));
        lowerJunctions.add(new Coordinates(3*cmPerTile,3*cmPerTile));
        lowerJunctions.add(new Coordinates(3*cmPerTile,5*cmPerTile));
        lowerJunctions.add(new Coordinates(5*cmPerTile,1*cmPerTile));
        lowerJunctions.add(new Coordinates(5*cmPerTile,3*cmPerTile));
        lowerJunctions.add(new Coordinates(5*cmPerTile,5*cmPerTile));

        mediumJunctions = new ArrayList<>();
        mediumJunctions.add(new Coordinates(2*cmPerTile, 1*cmPerTile));
        mediumJunctions.add(new Coordinates(1*cmPerTile, 2*cmPerTile));
        mediumJunctions.add(new Coordinates(4*cmPerTile, 1*cmPerTile));
        mediumJunctions.add(new Coordinates(1*cmPerTile, 4*cmPerTile));
        mediumJunctions.add(new Coordinates(2*cmPerTile, 5*cmPerTile));
        mediumJunctions.add(new Coordinates(4*cmPerTile, 5*cmPerTile));
        mediumJunctions.add(new Coordinates(5*cmPerTile, 4*cmPerTile));
        mediumJunctions.add(new Coordinates(5*cmPerTile, 2*cmPerTile));
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
