package org.firstinspires.ftc.teamcode.Common.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Components.ArmComponent;

public class ClawMotionAction extends BaseAction {
    ArmComponent armComponent;
    public boolean open;

    public ClawMotionAction(RobotModel robotModel, boolean open, Telemetry telemetry)  {
        super(robotModel, telemetry);
        this.armComponent = robotModel.getArmComponent();
        this.open = open;
    }
    @Override
    public void start() {
        if (open) {
            robotModel.withCone = true;
            armComponent.rightClaw.setPosition(0);
            armComponent.leftClaw.setPosition(0.5);
        } else {
            robotModel.withCone = false;
            armComponent.rightClaw.setPosition(0.5);
            armComponent.leftClaw.setPosition(0);
        }

    }

    @Override
    public void update() {
      /*  if(open && armComponent.rightClaw.getPosition() == 0 && armComponent.leftClaw.getPosition() == 0) {
            model.grabberOpen = true;
            finished = true;
        }
        else if(!open && armComponent.rightClaw.getPosition() == 1 && armComponent.leftClaw.getPosition() == 1){
            model.grabberOpen = false;
            finished = true;
        }

       */
    }

}
