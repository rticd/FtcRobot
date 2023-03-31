package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class LiftArmAction extends BaseAction
{
    RobotModel model;
    ArmComponent armComponent;
    double position;
    public double getPosition() {
        return position;
    }
    int targetTicks;

    public LiftArmAction(RobotModel model, double position,
                         Telemetry telemetry) {
        super(telemetry);
        this.model = model;
        this.position = position;
        armComponent = model.getArmComponent();
    }

    @Override
    public void start() {
        if(!finished) {
            if(position > armComponent.MAXIMUM_ARM_POSITION)
                position = armComponent.MAXIMUM_ARM_POSITION;

            targetTicks = (int)(armComponent.ARM_TICKS_PER_CM * position);
            armComponent.armMotor.setTargetPosition(targetTicks);
            armComponent.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    @Override
    public void update() {
        if(!finished && armComponent.armMotor.getCurrentPosition() == targetTicks) {
            model.armPosition = targetTicks / armComponent.ARM_TICKS_PER_CM;
            finished = true;
        }
    }
    @Override
    public void exit() {

    }
}
