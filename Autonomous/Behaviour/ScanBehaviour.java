package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousEntryPoint;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Pipeline;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class ScanBehaviour implements IBehaviour {
    RobotModel robotModel;

    FieldModel fieldModel;

    Telemetry telemetry;

    public boolean finished = false;
    public ScanBehaviour(RobotModel robotModel, FieldModel fieldModel, Telemetry telemetry) {
        this.robotModel = robotModel;
        this.fieldModel = fieldModel;
        this.telemetry = telemetry;
    }




    @Override
    public void update() {
        if (Pipeline.parkingPosition !=null){
            switch (Pipeline.parkingPosition) {
                case "1":
                    this.robotModel.setParkingCoordinates(fieldModel.getFirstParkingPosition());
                    break;
                case "2":
                    this.robotModel.setParkingCoordinates(fieldModel.getSecondParkingPosition());
                    break;
                case "3":
                    this.robotModel.setParkingCoordinates(fieldModel.getThirdParkingPosition());
                    break;
            }
            finishTheBehaviour();
        }
    }

    void finishTheBehaviour() {
//        if (this.robotModel.withCone){
//            AutonomousEntryPoint.currentBehaviour = new ToPoleBehaviour(this.robotModel,this.telemetry);
//        } else {
//            AutonomousEntryPoint.currentBehaviour = new ToConeBehaviour(this.robotModel,this.telemetry);
//        }
        AutonomousEntryPoint.currentBehaviour = new ParkingBehaviour(robotModel, telemetry);
    }
}
