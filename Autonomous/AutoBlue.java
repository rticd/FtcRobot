package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Autonomous.Action.action;
import static org.firstinspires.ftc.teamcode.Autonomous.Decide.decide;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
@Autonomous
public class AutoBlue extends LinearOpMode {
    RobotModel model;
    public static FtcDashboard dashboard;
    ElapsedTime time;
    public static String parkingPosition = "1";
    public static State currentAction = State.toCone;
    public static double coneArea = 0;
    public static double poleArea = 0;
    public static RelativePosition conePosition;
    public static RelativePosition polePosition;


    @Override
    public void runOpMode(){
        initialise();
        waitForStart();
        time=new ElapsedTime();
        while(opModeIsActive()){
            currentAction = decide(model,time,currentAction);
            action(model,currentAction,telemetry);
            telemetry.addData("Current Action",currentAction);
            telemetry.update();
        }
    }
    public void initialise(){
        //components and model
        dashboard = FtcDashboard.getInstance();
        model = new RobotModel(hardwareMap);
        model.getCameraComponent().start();
    }

}

