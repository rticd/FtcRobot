package org.firstinspires.ftc.teamcode.Manual;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class CloseClaw extends OpMode {

    private Servo leftClaw;
    private Servo rightClaw;

    @Override
    public void init() {
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        // set initial positions of the servos
        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.5);
    }

    @Override
    public void loop() {
        if(gamepad1.right_bumper) {
            // rotate the servos inwards
            leftClaw.setPosition(1.0);
            rightClaw.setPosition(1.0);

        } else if(gamepad1.left_bumper) {
            // rotate the servos outwards
            leftClaw.setPosition(-1.0);
            rightClaw.setPosition(-1.0);

        }
    }
}
