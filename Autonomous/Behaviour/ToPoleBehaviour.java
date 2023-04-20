package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousEntryPoint;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Pipeline;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.Actions.ClawMotionAction;
import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Common.Actions.MoveToCoordinatesAction;
import org.firstinspires.ftc.teamcode.Common.Actions.PowerMotionAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class ToPoleBehaviour implements IBehaviour{
    Telemetry telemetry;
    RobotModel robotModel;
    FieldModel fieldModel;
    LiftArmAction lift;
    PowerMotionAction powerMotionAction;
    ClawMotionAction clawMotionAction;
    IAction moveNearHighestPole;
    final double power = 0.5;

    public ToPoleBehaviour(RobotModel robotModel, Telemetry telemetry) {
        this.robotModel = robotModel;
        this.telemetry = telemetry;
    }

    @Override
    public void update() {
            //Object detection
            if (Pipeline.polePosition== RelativePosition.center){
                if (Pipeline.poleArea > 2000 && Pipeline.poleArea < 3000){
                    if (lift == null){
                        lift = new LiftArmAction(robotModel,89,telemetry);
                        lift.start();
                    } else{
                        lift.update();
                    }
                    powerMotionAction = new PowerMotionAction(robotModel,power, MotionDirection.vertical,telemetry);

                } else if(Pipeline.poleArea > 3000){
                    powerMotionAction = new PowerMotionAction(robotModel, power, MotionDirection.vertical, telemetry);
                    clawMotionAction = new ClawMotionAction(robotModel,true,telemetry);
                    finishTheBehaviour();

                }
            } else if(Pipeline.polePosition == RelativePosition.right){
                powerMotionAction = new PowerMotionAction(robotModel,power, MotionDirection.horizontal,telemetry);
            } else{
                powerMotionAction = new PowerMotionAction(robotModel,-power, MotionDirection.horizontal,telemetry);
            }

            powerMotionAction.update();

        }

    void finishTheBehaviour() {
        this.robotModel.withCone =false;
        AutonomousEntryPoint.currentBehaviour = new RotateConeBehaviour(this.robotModel,fieldModel,telemetry);
    }
}
