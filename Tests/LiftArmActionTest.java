package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
@Autonomous
public class LiftArmActionTest extends OpMode {
    RobotModel model;
    IAction liftArmAction;
    @Override
    public void init() {
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), 0, true);
        liftArmAction = new LiftArmAction(model, model.getArmComponent().PALKA_3_CM, telemetry);
        liftArmAction.start();
    }

    @Override
    public void loop() {
        //ArmComponent armComponent = model.getArmComponent();
        //armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_3_CM));
        telemetry.addData("Rotation action finished:", liftArmAction.isFinished());
        if(!liftArmAction.isFinished())
            liftArmAction.update();
    }
}
