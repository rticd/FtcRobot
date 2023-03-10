package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RGBColors;

public class FigureOutParkingPositionAction extends BaseAction {
    public RGBColors resultingColor;
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
                armComponent.colorSensor.red() > armComponent.colorSensor.green()){
            resultingColor = RGBColors.Red;
            model.parkingCoordinates = Coordinates.add(model.coordinates, fieldModel.getRedVectorFromColoredCone());
        }


        //if green component if the highest
        else if(armComponent.colorSensor.green() > armComponent.colorSensor.red() &&
                armComponent.colorSensor.green() > armComponent.colorSensor.blue()) {
            resultingColor = RGBColors.Green;
            model.parkingCoordinates = fieldModel.getGreenVectorFromColoredCone();
        }


        //if blue component is the highest
        else if(armComponent.colorSensor.blue() > armComponent.colorSensor.red() &&
                armComponent.colorSensor.blue() > armComponent.colorSensor.green()) {
            resultingColor = RGBColors.Blue;
            model.parkingCoordinates = fieldModel.getBlueVectorFromColoredCone();
        }

        finished = true;
    }

    @Override
    public void update() {

    }
}

