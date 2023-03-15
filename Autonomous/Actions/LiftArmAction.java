package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;

public class LiftArmAction extends BaseAction
{
    RobotModel model;
    ArmComponent armComponent;
    ArmPosition armPosition;
    public ArmPosition getArmPosition() {
        return armPosition;
    }
    int targetPosition;
    public LiftArmAction(RobotModel model, ArmComponent armComponent, ArmPosition armPosition,
                         Telemetry telemetry) {
        super(telemetry);
        this.model = model;
        this.armComponent = armComponent;
        this.armPosition = armPosition;
    }

    @Override
    public void start() {
        if(!finished) {
            switch (armPosition) {
                case Zero:
                    targetPosition = (int) (armComponent.ARM_TICKS_PER_CM * armComponent.GRAB_POSITION_CM);
                    break;
                case First:
                    targetPosition = (int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_1_CM);
                    break;
                case Second:
                    targetPosition = (int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_2_CM);
                    break;
                case Third:
                    targetPosition = (int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_3_CM);
                    break;
            }
            armComponent.armMotor.setTargetPosition(targetPosition);
            armComponent.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    @Override
    public void update() {
        if(!finished && armComponent.armMotor.getCurrentPosition() == targetPosition) {
            model.armPosition = this.armPosition;
            finished = true;
        }
    }

    @Override
    public void exit() {

    }
}
