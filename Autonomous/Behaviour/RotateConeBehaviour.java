package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.Actions.TurnAction;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class RotateConeBehaviour implements IBehaviour {
    Telemetry telemetry;
    RobotModel robotModel;
    TurnAction rotate;

    public RotateConeBehaviour(RobotModel robotModel, Telemetry telemetry) {
        this.robotModel = robotModel;
        this.telemetry = telemetry;
    }

    @Override
    public void update() {
        if (rotate==null){
            rotate = new TurnAction(robotModel,15,telemetry);
            rotate.start();
        }

        if(true) //Next behaviour condition met
            finishTheBehaviour();
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
