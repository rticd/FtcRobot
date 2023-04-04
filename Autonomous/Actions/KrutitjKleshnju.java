package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class KrutitjKleshnju extends BaseAction {
    ArmComponent armComponent;
    boolean open;

    public KrutitjKleshnju(RobotModel model, boolean open, Telemetry telemetry)  {
        super(model, telemetry);
        this.armComponent = model.getArmComponent();
        this.open = open;
    }
    @Override
    public void start() {
        if(open) {
            armComponent.rightClaw.setPosition(0);
            armComponent.leftClaw.setPosition(0);
        } else {
            armComponent.rightClaw.setPosition(1);
            armComponent.rightClaw.setPosition(1);
        }
    }

    @Override
    public void update() {
        if(open && armComponent.rightClaw.getPosition() == 0 && armComponent.leftClaw.getPosition() == 0) {
            model.grabberOpen = true;
            finished = true;
        }
        else if(!open && armComponent.rightClaw.getPosition() == 1 && armComponent.leftClaw.getPosition() == 1){
            model.grabberOpen = false;
            finished = true;
        }
    }

    @Override
    public void exit() {

    }
}
