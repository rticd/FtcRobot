package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Common.Actions.ClawMotionAction;
import org.firstinspires.ftc.teamcode.Common.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Common.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.Actions.PowerMotionAction;
import org.firstinspires.ftc.teamcode.Common.Actions.TurnAction;
import org.firstinspires.ftc.teamcode.Common.Actions.VibrateController;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class Action {
    static PowerMotionAction powerMotionAction;
    static ClawMotionAction clawMotionAction;
    static VibrateController vibrate;
    static LiftArmAction lift;
    public static TurnAction rotate;
    static double power = 0.5;
    public static void action(RobotModel model, State state, Telemetry telemetry){
        if (state == State.Scan){
            
        } else if (state==State.toCone){
            toCone.act();
        } else if (state ==State.toPole){
            if (AutoBlue.polePosition== RelativePosition.center){
                if (AutoBlue.poleArea > 2000 && AutoBlue.poleArea < 3000){
                    if (lift == null){
                        lift = new LiftArmAction(model,89,telemetry);
                        lift.start();
                    } else{
                        lift.update();
                    }
                    powerMotionAction = new PowerMotionAction(model,power,MotionDirection.vertical,telemetry);

                } else if(AutoBlue.poleArea > 3000){
                    powerMotionAction = new PowerMotionAction(model, power, MotionDirection.vertical, telemetry);
                    clawMotionAction = new ClawMotionAction(model,false,telemetry);
                }
            } else if(AutoBlue.polePosition == RelativePosition.right){
                powerMotionAction = new PowerMotionAction(model,power, MotionDirection.horizontal,telemetry);
            } else{
                powerMotionAction = new PowerMotionAction(model,-power, MotionDirection.horizontal,telemetry);
            }

            powerMotionAction.update();
        } else if (state==State.toPark){

        } else if (state==State.RotateCone ||state==State.RotatePole){
            if (rotate==null){
                rotate = new TurnAction(model,15,telemetry);
                rotate.start();
            }
            rotate.update();
        }

    }
}
