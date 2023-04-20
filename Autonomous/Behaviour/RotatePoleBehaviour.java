package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.Actions.TurnAction;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class RotatePoleBehaviour implements IBehaviour{

    Telemetry telemetry;
    RobotModel robotModel;
    TurnAction rotate;

    public RotatePoleBehaviour(RobotModel robotModel, Telemetry telemetry) {
        this.robotModel = robotModel;
        this.telemetry = telemetry;
    }

    @Override
    public void update() {
        if (rotate==null){
            if (robotModel.coordinates.getX()>3){
                rotate = new TurnAction(robotModel,180,telemetry);
            }else {
                rotate = new TurnAction(robotModel,-180,telemetry);
            }
            rotate.start();
        }
        rotate.update();
    }

    void finishTheBehaviour() {
        //Maybe do some finishing stuff
        //...
        //...
        //...
        //AutonomousEntryPoint.currentBehaviour = new IBehaviour();
    }
}
