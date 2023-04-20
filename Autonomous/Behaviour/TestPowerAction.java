package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Common.Actions.PowerMotionAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.RobotTeamColor;
@Autonomous
public class TestPowerAction extends OpMode {
    RobotModel robotModel;
    PowerMotionAction action;
    boolean firstIteration = true;

    FtcDashboard dashboard;
    TelemetryPacket telemetryPacket;
    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();
        robotModel = new RobotModel(hardwareMap, RobotTeamColor.Red, new Coordinates(0, 0));
        action = new PowerMotionAction(robotModel, 1, MotionDirection.vertical, telemetry);
        telemetryPacket = new TelemetryPacket();
    }

    @Override
    public void loop() {
        if(firstIteration) {
            action.start();
            firstIteration = false;
        } else {
            action.update();
            telemetryPacket.put("Current coordinate X",robotModel.coordinates.getX());
            telemetryPacket.put("Current coordinate Y",robotModel.coordinates.getY());
            dashboard.sendTelemetryPacket(telemetryPacket);
        }
    }

}
