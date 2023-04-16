package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Common.Actions.ClawMotion;
import org.firstinspires.ftc.teamcode.Common.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Common.Actions.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.Actions.PlaneMotion;
import org.firstinspires.ftc.teamcode.Common.Actions.Turn;
import org.firstinspires.ftc.teamcode.Common.Actions.VibrateController;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class Action {
    static PlaneMotion planeMotion;
    static ClawMotion clawMotion;
    static VibrateController vibrate;
    static LiftArmAction lift;
    public static Turn rotate;
    static double power = 0.5;
    public static void action(RobotModel model, State state, Telemetry telemetry){
        if (state == State.Scan){
            
        } else if (state==State.toCone){
            if (AutoBlue.conePosition== RelativePosition.center){
                    vibrate = new VibrateController(model,telemetry);
                    vibrate.update();
                if (vibrate.vibrated){
                    clawMotion = new ClawMotion(model,true,telemetry);
                    planeMotion = new PlaneMotion(model,power,MotionDirection.none,telemetry);
                    AutoBlue.currentAction = State.toPole;
                }else {
                    planeMotion = new PlaneMotion(model, power, MotionDirection.vertical, telemetry);
                }
            } else if(AutoBlue.conePosition == RelativePosition.right){
                planeMotion= new PlaneMotion(model,power, MotionDirection.horizontal,telemetry);
            } else{
                planeMotion= new PlaneMotion(model,-power, MotionDirection.horizontal,telemetry);
            }

           planeMotion.update();
        } else if (state ==State.toPole){
            if (AutoBlue.polePosition== RelativePosition.center){
                if (AutoBlue.poleArea > 2000 && AutoBlue.poleArea < 3000){
                    if (lift == null){
                        lift = new LiftArmAction(model,89,telemetry);
                        lift.start();
                    } else{
                        lift.update();
                    }
                    planeMotion = new PlaneMotion(model,power,MotionDirection.vertical,telemetry);

                } else if(AutoBlue.poleArea > 3000){
                    planeMotion = new PlaneMotion(model, power, MotionDirection.vertical, telemetry);
                    clawMotion = new ClawMotion(model,false,telemetry);
                }
            } else if(AutoBlue.polePosition == RelativePosition.right){
                planeMotion= new PlaneMotion(model,power, MotionDirection.horizontal,telemetry);
            } else{
                planeMotion= new PlaneMotion(model,-power, MotionDirection.horizontal,telemetry);
            }

            planeMotion.update();
        } else if (state==State.toPark){

        } else if (state==State.RotateCone ||state==State.RotatePole){
            if (rotate==null){
                rotate = new Turn(model,15,telemetry);
                rotate.start();
            }
            rotate.update();
        }

    }
}
