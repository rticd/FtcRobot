package org.firstinspires.ftc.teamcode.Manual;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;

@TeleOp
public class ManualOpMode extends LinearOpMode {
    RobotModel model;
    ManualDrive driveController;
    ManualArm armController;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        while(true) {
            driveControl();
            armControl();
            telemetry.update();
        }
    }
    void initialize() {
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), 0, true);
        armController = new ManualArm(model);
        driveController = new ManualDrive(model, telemetry);
    }
    void driveControl() {
        //Если не движение, то проверяет ввод для поворота
        {
            double turn = gamepad1.right_stick_x;
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            driveController.setTurn(turn);
            driveController.setX(x);
            driveController.setY(y);
        }
         if(gamepad1.left_bumper)
             driveController.startTurning(90);
         else if(gamepad1.right_bumper)
             driveController.startTurning(-90);

        driveController.update();
    }

    void armControl() {
        if (gamepad2.left_trigger > 0.1f){
            armController.controlLiftManually(1);
        }
        else if (gamepad2.right_trigger > 0.1f){
            armController.controlLiftManually(-1);
        }
        else if(gamepad2.circle) {
            armController.liftArmToPosition(ArmPosition.Zero);
        }
        else if(gamepad2.triangle) {
            armController.liftArmToPosition(ArmPosition.First);
        }
        else if(gamepad2.square) {
            armController.liftArmToPosition(ArmPosition.Second);
        }
        else if(gamepad2.cross){
            armController.liftArmToPosition(ArmPosition.Third);
        }

        if(gamepad2.left_bumper) //Тут ставишь куда ты хочешь жать для контроля клешни вместо true и false
            armController.setCleshnja(true);
        else if(gamepad2.right_bumper)
            armController.setCleshnja(false);
    }
}
