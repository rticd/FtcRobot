package org.firstinspires.ftc.teamcode.Manual;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmMode;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class ManualArm {


    public ArmMode mode = ArmMode.manual;
    RobotModel model;
    ArmComponent armComponent;
    ArmPosition position = ArmPosition.None;
    public ArmPosition getPosition() {
        return position;
    }
    public void setPosition(ArmPosition position) {
        this.position = position;
    }
    public boolean cleshnjaOpen;

    public boolean getCleshnjaOpen() {
        return cleshnjaOpen;
    }


    public ManualArm(RobotModel model) {
        this.model = model;
        this.armComponent = model.getArmComponent();
    }

   /* public void setCleshnja(boolean mode){
        if (mode==true){
            this.cleshnjaOpen=true;
        }else{
            this.cleshnjaOpen=false;
        }
        if (this.cleshnjaOpen==true){
            armComponent.rightClaw.setPosition(1);
            armComponent.leftClaw.setPosition(1);
        } else {
            armComponent.rightClaw.setPosition(0);
            armComponent.leftClaw.setPosition(0);
        }
    }

    */

    public void update() {
        switch (position) {
            case Zero:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.GRAB_POSITION_CM));
                armComponent.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                break;
            case First:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_1_CM));
                armComponent.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                break;
            case Second:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_2_CM));
                armComponent.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                break;
            case Third:
                armComponent.armMotor.setTargetPosition((int) (armComponent.ARM_TICKS_PER_CM * armComponent.PALKA_3_CM));
                armComponent.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
            default:
                position=ArmPosition.None;
                break;
            }
        if (!armComponent.armMotor.isBusy()){
            if (this.mode==ArmMode.auto){
                this.mode=ArmMode.manual;
            }
        }

    }
    public void indefinite(int dir){
        if (dir > 0){

            armComponent.armMotor.setPower(1);
            armComponent.armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } else if (dir<0){

            armComponent.armMotor.setPower(-1);
            armComponent.armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } else if(dir==0){
            armComponent.armMotor.setPower(0);
        }
    }
}



