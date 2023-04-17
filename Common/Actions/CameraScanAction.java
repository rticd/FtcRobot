package org.firstinspires.ftc.teamcode.Common.Actions;

import com.google.zxing.Result;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.Components.CameraComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class CameraScanAction extends BaseAction {
    CameraComponent cameraComponent;
    FieldModel fieldModel;
    Coordinates parkingPosition;
    Result result;
    public CameraScanAction(RobotModel robotModel, Telemetry telemetry) {
        super(robotModel, telemetry);
        this.cameraComponent = robotModel.getCameraComponent();
    }

    @Override
    public void start() {
        this.cameraComponent.start();;
    }

    @Override
    public void update() {
        if (this.cameraComponent.pipeline.result != null){
            this.result = this.cameraComponent.pipeline.result;
            String text = result.getText();
            switch (text) {
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
            robotModel.setParkingCoordinates(parkingPosition);
            finished = true;
            this.cameraComponent.webcam.stopStreaming();
        }
    }
}
