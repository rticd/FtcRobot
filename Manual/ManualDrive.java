package org.firstinspires.ftc.teamcode.Manual;

import org.firstinspires.ftc.robotcore.external.Telemetry;
<<<<<<< Updated upstream
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.SensorComponent;
=======
import org.firstinspires.ftc.teamcode.Common.Actions.Action;
import org.firstinspires.ftc.teamcode.Common.Actions.RotationDirection;
import org.firstinspires.ftc.teamcode.Common.Actions.Turn;
import org.firstinspires.ftc.teamcode.Common.Component.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Component.SensorComponent;
>>>>>>> Stashed changes

//Not going to update model, since it's only really needed for autonomous.
public class ManualDrive {
    RobotModel model;
    DriveComponent driveComponent;
    SensorComponent sensorComponent;
<<<<<<< Updated upstream
    IAction rotationAction;
=======
    Action rotationAction;
>>>>>>> Stashed changes
    Telemetry telemetry;

    Turn actionTurn;
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


    public ManualDrive(RobotModel model, Telemetry telemetry) {
        this.model = model;
        this.driveComponent = model.getDriveComponent();
        this.sensorComponent = model.getSensorComponent();
        this.telemetry = telemetry;
<<<<<<< Updated upstream
    }

    //Короче, автоматический поворот на 90 градусов будет блокировать движение.
    //Иначе никак/не имеет смысла.
    //Блокируется только движение колёс, всё остальное (руку там, клешню) можно использовать.


    public void startTurning(double deltaAngle) {
        if(rotationAction == null) {
            driveComponent.lowerRight.setPower(0);
            driveComponent.upperRight.setPower(0);
            driveComponent.lowerLeft.setPower(0);
            driveComponent.upperLeft.setPower(0);
            rotationAction = new RotateAction(model, deltaAngle, telemetry);
            rotationAction.start();
        }
=======
>>>>>>> Stashed changes
    }

    //Короче, автоматический поворот на 90 градусов будет блокировать движение.
    //Иначе никак/не имеет смысла.
    //Блокируется только движение колёс, всё остальное (руку там, клешню) можно использовать.


    public void startTurning(double deltaAngle){
        actionTurn = new Turn(model,deltaAngle,telemetry);
        actionTurn.start();
    }
    public RotationDirection continueTurning(){
        actionTurn.update();
        return actionTurn.rotationDirection;
    }


    public void update() {
        if(rotationAction == null) {
            //Movement
            double ulPower = y + x + turn;
            double llPower = y - x + turn;
            double urPower = y - x - turn;
            double lrPower = y + x - turn;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);
            driveComponent.upperLeft.setPower(ulPower / denominator);
            driveComponent.lowerLeft.setPower(llPower / denominator);
            driveComponent.upperRight.setPower(urPower / denominator);
            driveComponent.lowerRight.setPower(lrPower / denominator);
        } else {
            rotationAction.update();
            if(rotationAction.isFinished())
                rotationAction = null;
        }
    }
}
