package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class GrabAction extends BaseAction {
    RobotModel model;
    ArmComponent armComponent;

    public GrabAction(RobotModel model, ArmComponent armComponent, Telemetry telemetry)  {
        super(telemetry);
        this.model = model;
        this.armComponent = armComponent;
    }
    @Override
    public void start() {
        armComponent.rightClaw.setPosition(1);
        armComponent.leftClaw.setPosition(1);
    }

    @Override
    public void update() {
        if(armComponent.rightClaw.getPosition() == 1 && armComponent.leftClaw.getPosition() == 1) {
            model.grabberOpen = true;
            finished = true;
        }
    }

    @Override
    public void exit() {

    }
}
