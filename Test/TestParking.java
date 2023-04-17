package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Actions.MoveToCoordinatesAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

@Autonomous
public class TestParking extends OpMode {
    boolean started;
    RobotModel robotModel;
    IAction parkingAction;
    @Override
    public void init() {
        robotModel = new RobotModel(hardwareMap);
        parkingAction = new MoveToCoordinatesAction(robotModel, new Coordinates(40, 40), telemetry);
    }

    @Override
    public void loop() {
        telemetry.addData("finished:", parkingAction.isFinished());
        if(started) {
            if(!parkingAction.isFinished())
                parkingAction.update();
        }
        else {
            parkingAction.start();
            started = true;
        }

        telemetry.addData("robotCoordinatesX", robotModel.coordinates.getX());
        telemetry.addData("robotCoordinatesY", robotModel.coordinates.getY());
        telemetry.addData("robot angle", robotModel.absAngle);

    }
}
