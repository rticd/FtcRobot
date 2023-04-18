package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousEntryPoint;
import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Actions.MoveToCoordinatesAction;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class ParkingBehaviour implements IBehaviour {
    RobotModel robotModel;
    Telemetry telemetry;
    IAction moveToPosition;

    public ParkingBehaviour(RobotModel robotModel, Telemetry telemetry){
        this.robotModel = robotModel;
        this.telemetry = telemetry;
    }
    @Override
    public void update() {
        if(moveToPosition == null) {
            moveToPosition = new MoveToCoordinatesAction(robotModel, robotModel.getParkingCoordinates(), telemetry);
            moveToPosition.start();
        } else if(!moveToPosition.isFinished())
            moveToPosition.update();
        else if(moveToPosition.isFinished()) { //Если робот доехал до парковки, то...
            finishTheBehaviour();
        }
    }

    void finishTheBehaviour() {
        //Maybe do some finishing stuff
        //...
        //...
        //...
        //AutonomousEntryPoint.currentBehaviour = new IBehaviour();
    }
}
