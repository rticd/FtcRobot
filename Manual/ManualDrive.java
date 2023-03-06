package org.firstinspires.ftc.teamcode.Manual;

import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Autonomous.IAction;

//Not going to update model, since it's only really needed for autonomous.
public class ManualDrive {

    DriveComponent driveComponent;

    double x;
    public void setX(double x) {
        this.x = x;
    }

    double y;
    public void setY(double y) {
        this.y = y;
    }

    double turn;
    public double getTurn() {
        return turn;
    }
    public void setTurn(double turn) {
        this.turn = turn;
    }


    public ManualDrive(DriveComponent component) {
        this.driveComponent = component;
    }


    //In case if the power or direction changes. Could put this code into set methods.

    public void update() {
        double ulPower = y + x + turn;
        double llPower = y - x - turn;
        double urPower = y - x + turn;
        double lrPower = y + x - turn;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);
        driveComponent.upperLeft.setPower(ulPower / denominator);
        driveComponent.lowerLeft.setPower(llPower / denominator);
        driveComponent.upperRight.setPower(urPower / denominator);
        driveComponent.lowerRight.setPower(lrPower / denominator);

    }
}
