package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

public class FigureOutParkingPositionAction extends BaseAction {
    FieldModel fieldModel;

    RobotModel model;
    ArmComponent armComponent;

    public FigureOutParkingPositionAction(RobotModel model, FieldModel fieldModel,ArmComponent armComponent) {
        this.model = model;
        this.fieldModel = fieldModel;
        this.armComponent = armComponent;
    }
    @Override
    public void start() {
        if(finished) return;
        //If red component is the highest
        if(armComponent.colorSensor.red() > armComponent.colorSensor.blue() &&
                armComponent.colorSensor.red() > armComponent.colorSensor.green())
            model.parkingCoordinates = Coordinates.add(model.coordinates, fieldModel.getRedVectorFromColoredCone());

        //if green component if the highest
        else if(armComponent.colorSensor.green() > armComponent.colorSensor.red() &&
                armComponent.colorSensor.green() > armComponent.colorSensor.blue())
            model.parkingCoordinates = fieldModel.getGreenVectorFromColoredCone();

        //if blue component is the highest
        else if(armComponent.colorSensor.blue() > armComponent.colorSensor.red() &&
                armComponent.colorSensor.blue() > armComponent.colorSensor.green())
            model.parkingCoordinates = fieldModel.getBlueVectorFromColoredCone();
        finished = true;
    }

    @Override
    public void update() {

    }
}

