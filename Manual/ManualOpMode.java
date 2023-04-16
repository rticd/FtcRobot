package org.firstinspires.ftc.teamcode.Manual;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.Vuforia;

<<<<<<< Updated upstream
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
=======
import org.firstinspires.ftc.teamcode.Common.Actions.RotationDirection;
import org.firstinspires.ftc.teamcode.Common.Actions.Turn;
import org.firstinspires.ftc.teamcode.Common.Actions.VibrateController;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Component.ArmPosition;
>>>>>>> Stashed changes

@TeleOp
public class ManualOpMode extends LinearOpMode {
    RobotModel model;
    ManualDrive driveController;
    ManualArm armController;

<<<<<<< Updated upstream
=======
    boolean liftActionOccurred;
   public static RotationDirection rot =RotationDirection.none;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), 0, true);
=======
        model = new RobotModel(hardwareMap);
>>>>>>> Stashed changes
        armController = new ManualArm(model);
        driveController = new ManualDrive(model, telemetry);
    }
    void driveControl() {
<<<<<<< Updated upstream
        //Если не движение, то проверяет ввод для поворота
=======
>>>>>>> Stashed changes
        {
            double turn = gamepad1.right_stick_x;
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            driveController.setTurn(turn);
            driveController.setX(x);
            driveController.setY(y);
        }
<<<<<<< Updated upstream
         if(gamepad1.left_bumper)
             driveController.startTurning(90);
         else if(gamepad1.right_bumper)
             driveController.startTurning(-90);

=======
        if (gamepad1.left_bumper && rot==RotationDirection.none){
            driveController.startTurning(-90);
            rot = RotationDirection.left;
            telemetry.addData("Pressed",rot);
        } else if (gamepad1.right_bumper && rot== RotationDirection.none){
            driveController.startTurning( 90);
            rot = RotationDirection.right;
            telemetry.addData("Pressed",rot);
        }
        if (rot!=RotationDirection.none ){
            rot = driveController.continueTurning();
        }
        telemetry.addData("Pressed",rot);
>>>>>>> Stashed changes
        driveController.update();
    }

    void armControl() {
<<<<<<< Updated upstream
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
=======
        telemetry.addData("Busy",liftActionOccurred);

        VibrateController vibrateController = new VibrateController(model,telemetry);
        if (gamepad2.left_trigger > 0.1f){
            armController.controlLiftManually(-1);
            liftActionOccurred = true;

        }
        else if (gamepad2.right_trigger > 0.1f){
            armController.controlLiftManually(1);
            liftActionOccurred = true;

        } else if (gamepad2.right_trigger < 0.1f ||gamepad2.left_trigger < 0.1f){
            armController.controlLiftManually(0);
            liftActionOccurred = false;

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

        if(gamepad2.left_bumper) { //Тут ставишь куда ты хочешь жать для контроля клешни вместо true и false
            armController.setCleshnja(true);

        }else if(gamepad2.right_bumper) {
            armController.setCleshnja(false);

        }
        if (!liftActionOccurred){
            vibrateController.update();
            if (vibrateController.vibrated){
                gamepad2.rumble(500);
            }
        }
>>>>>>> Stashed changes
    }
}
