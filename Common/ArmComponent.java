package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmComponent {
    //TurnsPerSecond * diameter * PI
    public final int ARM_TICKS_PER_CM = (int)(1120/(5.75 * Math.PI));

    public final double GRAB_POSITION_CM = 0.1;
    public final double PALKA_1_CM = 34.29;
    public final double PALKA_2_CM = 58.42;
    public final double PALKA_3_CM = 89;

    public final int cleshnjaClosedTicks = 1;

    public DcMotor armMotor;
    public Servo cleshnja;

    public ColorSensor colorSensor;

    public ArmComponent(DcMotor armMotor, Servo cleshnja, ColorSensor colorSensor) {
        this.cleshnja = cleshnja;
        this.armMotor = armMotor;
        this.colorSensor = colorSensor;
        cleshnja.getController().pwmEnable();
        cleshnja.setDirection(Servo.Direction.FORWARD);
        cleshnja.setPosition(0);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setPower(1);
    }
}
