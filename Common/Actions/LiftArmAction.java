package org.firstinspires.ftc.teamcode.Common.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Component.ArmComponent;

<<<<<<< Updated upstream:Autonomous/Actions/LiftArmAction.java
public class LiftArmAction extends BaseAction
{
=======
public class LiftArmAction extends BaseAction {
>>>>>>> Stashed changes:Common/Actions/LiftArmAction.java
    ArmComponent armComponent;
    double position;
    int targetTicks;

<<<<<<< Updated upstream:Autonomous/Actions/LiftArmAction.java
    public LiftArmAction(RobotModel model, double position,
                         Telemetry telemetry) {
=======
    public LiftArmAction(RobotModel model, double position, Telemetry telemetry) {
>>>>>>> Stashed changes:Common/Actions/LiftArmAction.java
        super(model, telemetry);
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

}
