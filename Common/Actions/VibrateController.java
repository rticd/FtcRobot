package org.firstinspires.ftc.teamcode.Common.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Common.Component.SensorComponent;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class VibrateController extends BaseAction{
    public boolean vibrated = false;

    SensorComponent sensorComponent;
    public VibrateController(RobotModel model, Telemetry telemetry){
        super(model,telemetry);
        this.model = model;
        this.sensorComponent = model.getSensorComponent();
    }
    @Override
    public void update(){
        double distance = sensorComponent.distanceSensor.getDistance(DistanceUnit.CM);
        if (distance < 12 && distance> 7 && vibrated==false){
            vibrated = true;
        } else if(!(distance <= 18 && distance >=12)){
            vibrated =false;
        }
    }
    public void start(){

    }
}
