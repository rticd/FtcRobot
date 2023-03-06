package org.firstinspires.ftc.teamcode.Manual;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;

public class ManualArm {

    Telemetry telemetry;

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    ArmComponent armComponent;
    ArmPosition position = ArmPosition.Zero;
    public ArmPosition getPosition() {
        return position;
    }
    public void setPosition(ArmPosition position) {
        this.position = position;
    }

    boolean cleshnjaOpen = true;
    public boolean getCleshnjaOpen() {
        return cleshnjaOpen;
    }
    public void setCleshnjaOpen(boolean cleshnjaOpen) {
        this.cleshnjaOpen = cleshnjaOpen;
    }

    public ManualArm(ArmComponent armComponent) {
        this.armComponent = armComponent;
    }

    public void update() {

        switch (position) {
            case Zero:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.GRAB_POSITION_CM));
                break;
            case First:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_1_CM));
                break;
            case Second:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_2_CM));
                break;
            case Third:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_3_CM));
                break;
            }
        armComponent.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (cleshnjaOpen) {
            armComponent.cleshnja.setPosition(0);
        } else {
            armComponent.cleshnja.setPosition(1);
        }
    }
}



