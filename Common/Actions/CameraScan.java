package org.firstinspires.ftc.teamcode.Common.Actions;

import com.google.zxing.Result;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.Component.CameraComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class CameraScan extends BaseAction {
    RobotModel model;
    CameraComponent cameraComponent;
    FieldModel fieldModel;
    Coordinates parkingPosition;
    Result result;
    public CameraScan(RobotModel model, Telemetry telemetry) {
        super(model, telemetry);
        this.model = model;
        this.cameraComponent = model.getCameraComponent();
    }

    @Override
    public void start() {
        this.cameraComponent.start();;
    }

    @Override
    public void update() {
        telemetry.addData("inside action",true);
        telemetry.update();
        if (this.cameraComponent.pipeline.result != null){
            this.result = this.cameraComponent.pipeline.result;
            String text = result.getText();
            switch (text) {
                case "1":
                    this.model.parkingPosition = -1;
                    break;
                case "2":
                    //this.model.setParkingCoordinates(fieldModel.getSecondParkingPosition());
                    this.model.parkingPosition = 0;
                    break;
                case "3":
                    //this.model.setParkingCoordinates(fieldModel.getThirdParkingPosition());
                    this.model.parkingPosition = 1;
                    break;
            }
            model.setParkingCoordinates(parkingPosition);
            finished = true;
            this.cameraComponent.webcam.stopStreaming();
        }
    }
}
