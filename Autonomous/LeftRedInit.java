package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.IBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.ToPoleBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Pipeline;
import org.firstinspires.ftc.teamcode.Common.Actions.ClawMotionAction;
import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.RobotTeamColor;
@Autonomous

public class LeftRedInit extends OpMode {
    AutonomousEntryPoint autonomousEntryPoint;

    @Override
    public void init() {
        autonomousEntryPoint = new AutonomousEntryPoint(
                new Coordinates(FieldModel.CM_PER_TILE*1.5, 37/2), RobotTeamColor.Red, hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        autonomousEntryPoint.loop();
    }
}
