package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmComponent {
    public final double ARM_WHEEL_DIAMETER = 5.75;
    public final double TICKS_PER_REVOLUTION = 1120;
    public final int ARM_TICKS_PER_CM = (int)(TICKS_PER_REVOLUTION/(ARM_WHEEL_DIAMETER * Math.PI));

    public final double GRAB_POSITION_CM = 0.1;
    public final double PALKA_1_CM = 34.29;
    public final double PALKA_2_CM = 58.42;
    public final double PALKA_3_CM = 89;

    public DcMotor armMotor;
    public Servo rightClaw;
    public Servo leftClaw;

    public ArmComponent(DcMotor armMotor, Servo rightClaw, Servo leftClaw) {
        this.rightClaw = rightClaw;
        this.armMotor = armMotor;
        rightClaw.getController().pwmEnable();
        rightClaw.setDirection(Servo.Direction.FORWARD);
        rightClaw.setPosition(0);
        leftClaw.getController().pwmEnable();
        leftClaw.setDirection(Servo.Direction.FORWARD);
        leftClaw.setPosition(0);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setPower(1);
    }
}
