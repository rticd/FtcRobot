package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RGBColors;

public class ScanForParkingPosition extends BaseAction {
    int scans;
    int scaned;
    boolean scaningPartCompleted = false;
    Coordinates middleSquareCoordinates;
    RGBColors resultingColor;

    IAction moveAction;
    FieldModel fieldModel;

    RobotModel model;
    ArmComponent armComponent;
    DriveComponent driveComponent;

    ActionQueue queue;

    public ScanForParkingPosition(RobotModel model, FieldModel fieldModel,
                                  DriveComponent driveComponent, ArmComponent armComponent, int scans
                                    , Coordinates middleSquareCoordinates) {
        queue = new ActionQueue();
        this.model = model;
        this.fieldModel = fieldModel;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        this.scans = scans;
        this.middleSquareCoordinates = middleSquareCoordinates;
    }
    @Override
    public void start() {
        if(finished) return;
        moveAction = new MoveForDistanceAction(model, driveComponent, 1);
        moveAction.setTelemetry(telemetry);
        moveAction.start();
    }

    @Override
    public void update() {
        if(!finished) {
            if(!scaningPartCompleted) {
                if(moveAction.isFinished()) {
                    moveAction = new MoveForDistanceAction(model, driveComponent, 1);
                    moveAction.setTelemetry(telemetry);
                    moveAction.start();
                    scaned++;
                }
                if(armComponent.colorSensor.red() > armComponent.colorSensor.blue() &&
                        armComponent.colorSensor.red() > armComponent.colorSensor.green()){
                    resultingColor = RGBColors.Red;
                    model.parkingCoordinates = Coordinates.add(model.coordinates, fieldModel.getRedVectorFromColoredCone());
                }


                //if green component if the highest
                else if(armComponent.colorSensor.green() > armComponent.colorSensor.red() &&
                        armComponent.colorSensor.green() > armComponent.colorSensor.blue()) {
                    resultingColor = RGBColors.Green;
                    model.parkingCoordinates = Coordinates.add(model.coordinates, fieldModel.getGreenVectorFromColoredCone());
                }


                //if blue component is the highest
                else if(armComponent.colorSensor.blue() > armComponent.colorSensor.red() &&
                        armComponent.colorSensor.blue() > armComponent.colorSensor.green()) {
                    resultingColor = RGBColors.Blue;
                    model.parkingCoordinates = Coordinates.add(model.coordinates, fieldModel.getBlueVectorFromColoredCone());
                }
                model.color = resultingColor;

                if((scaned >= scans || resultingColor != RGBColors.Green))
                {
                    scaningPartCompleted = true;
                    queue = new ActionQueue();
                    MoveForDistanceAction moveABit = new MoveForDistanceAction(model, driveComponent, 20);
                    moveABit.setTelemetry(telemetry);
                    queue.setNextAction(moveABit);

                    IAction driveToParking = new MoveToPositionLongestPathAction(model, driveComponent, model.parkingCoordinates);
                    driveToParking.setTelemetry(telemetry);
                    queue.setNextAction(driveToParking);
                    queue.start();
                }
                    moveAction.update();

            } else {
                if(queue != null) { {
                    queue.update();
                    if(queue.isFinished())
                        finished = true;
                }
            }
        }
    }
}
}

