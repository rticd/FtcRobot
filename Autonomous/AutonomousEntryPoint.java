package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.sun.tools.javac.file.RelativePath;

import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.IBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.ParkingBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.ScanBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Pipeline;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.BlueConeDetectionUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.RedConeDetectionUtil;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.RobotTeamColor;

@Autonomous
public class AutonomousEntryPoint extends OpMode {
    RobotModel robotModel;
    FieldModel fieldModel;
    public static IBehaviour currentBehaviour;

   public static ElapsedTime elapsedTime;


    @Override
    public void init() {
        robotModel = new RobotModel(hardwareMap, RobotTeamColor.Blue,
                new Coordinates(61*1.5+35/2, 37/2));
        robotModel.absAngle = 90;
        fieldModel = new FieldModel(new Coordinates(61*1.5+35/2, 37/2));
        robotModel.getCameraComponent().start();
        elapsedTime = new ElapsedTime();
        currentBehaviour = new ScanBehaviour(robotModel,fieldModel,telemetry);

    }

    @Override
    public void loop() {
        currentBehaviour.update();
        telemetry.addData("parkingPoaition", Pipeline.parkingPosition);
        telemetry.update();
//        if (elapsedTime.seconds()>20 && !(currentBehaviour instanceof ParkingBehaviour)){
//            currentBehaviour = new ParkingBehaviour(robotModel,telemetry);
//        }
    }

}
