package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

@Autonomous
public class LeftInitialization extends OpMode {

    AutonomousOpMode opMode;
    @Override
    public void init() {
        RobotModel model = new RobotModel(new Coordinates(1.5*60,38.3/2), Math.PI/2, ArmPosition.Zero, true);
        FieldModel fieldModel = new FieldModel(new Coordinates(1.5*60,38.3/2), //starting position
                new Coordinates(0, 1.5*55), //colored cone vector
                new Coordinates(-1*60, 0), //red parking position vector
                new Coordinates(0, 0), //green parking position vector
                new Coordinates(1*60, 0)); //blue parking position vector

        opMode = new AutonomousOpMode();
        opMode.init(model, fieldModel, telemetry, hardwareMap);
    }

    @Override
    public void loop() {
        opMode.loop();
    }
}
