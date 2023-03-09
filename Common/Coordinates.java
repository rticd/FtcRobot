package org.firstinspires.ftc.teamcode.Common;

import java.util.List;

public class Coordinates {
    double x;
    public double getX() { return x; }
    double y;
    public double getY() { return y; }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinates coordinates) {
        if(coordinates.getX() == this.x && coordinates.getY() == this.y)
            return true;
        return false;
    }

    public static Coordinates add(Coordinates coordinates1, Coordinates coordinates2) {
        double newX = coordinates1.x + coordinates2.x;
        double newY = coordinates1.y + coordinates2.y;
        return new Coordinates(newX, newY);
    }

    public static Coordinates findClosestCoordinates(Coordinates targetCoordinates, List<Coordinates> coordinates) {
        Coordinates closestCoordinates = coordinates.get(0);
        for (Coordinates coordinate: coordinates) {
            double distance = Math.abs(targetCoordinates.getX()-coordinate.getX()) +
                              Math.abs(targetCoordinates.getY()-coordinate.getY());
            double previousDistance = Math.abs(targetCoordinates.getX()-closestCoordinates.getX()) +
                                      Math.abs(targetCoordinates.getY()-closestCoordinates.getY());
            if(distance > previousDistance)
                closestCoordinates = coordinate;
        }
        return closestCoordinates;
    }
}
