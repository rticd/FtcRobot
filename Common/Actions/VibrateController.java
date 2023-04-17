package org.firstinspires.ftc.teamcode.Common.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Common.Components.SensorComponent;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class VibrateController extends BaseAction {
    SensorComponent sensorComponent;
    public VibrateController(RobotModel robotModel, Telemetry telemetry){
        super(robotModel, telemetry);
        this.sensorComponent = robotModel.getSensorComponent();
    }
    @Override
    public void update(){
        double distance = sensorComponent.distanceSensor.getDistance(DistanceUnit.CM);
        if (distance < 12 && distance> 7 && robotModel.vibrated==false){
            robotModel.vibrated = true;
        } else if(!(distance <= 18 && distance >=12)){
            robotModel.vibrated = false;
        }
    }
    public void start(){

    }
}
