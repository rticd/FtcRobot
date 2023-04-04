package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.google.zxing.Result;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.SensorComponent;

public class ReadQRCodeAction extends BaseAction {

    SensorComponent sensorComponent;
    FieldModel fieldModel;
    Coordinates parkingPosition;
    public ReadQRCodeAction(RobotModel model, FieldModel fieldModel, Telemetry telemetry) {
        super(model, telemetry);
        this.fieldModel = fieldModel;
        this.sensorComponent = model.getSensorComponent();
    }

    @Override
    public void start() {
        //Camera scaning
        //
        //
        Result result = null;
        String text = result.getText();
        switch (text) {
            case "1":
                parkingPosition = fieldModel.getFirstParkingPosition();
                break;
            case "2":
                parkingPosition = fieldModel.getSecondParkingPosition();
                break;
            case "3":
                parkingPosition = fieldModel.getThirdParkingPosition();
                break;
        }
        model.setParkingCoordinates(parkingPosition);
        finished = true;
    }

    @Override
    public void update() {

    }

    @Override
    public void exit() {

    }
}
