package org.firstinspires.ftc.teamcode.Autonomous.Actions;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class ReleaseAction extends BaseAction {
    RobotModel model;

    ArmComponent armComponent;

    public ReleaseAction(RobotModel model, ArmComponent armComponent, Telemetry telemetry) {
        super(telemetry);
        this.model = model;
        this.armComponent = armComponent;
    }

    @Override
    public void start() {
        armComponent.rightClaw.setPosition(0);
        armComponent.leftClaw.setPosition(0);
    }
    @Override
    public void update() {
        if(armComponent.rightClaw.getPosition() == 0 && armComponent.leftClaw.getPosition() == 0) {
            model.grabberOpen = false;
            finished = true;
        }

    }

    @Override
    public void exit() {

    }
}
