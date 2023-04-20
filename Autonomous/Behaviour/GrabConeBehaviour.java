package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousEntryPoint;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Pipeline;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Common.Actions.MoveToCoordinatesAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.RobotTeamColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GrabConeBehaviour implements IBehaviour {
    Queue<IAction> actionSequence;
    IAction currentAction;
    RobotModel robotModel;
    Telemetry telemetry;

    FieldModel fieldModel;
    public GrabConeBehaviour(RobotModel robotModel, FieldModel fieldModel, Telemetry telemetry){
        this.robotModel = robotModel;
        this.telemetry = telemetry;
        this.fieldModel = fieldModel;
        this.robotModel.getCameraComponent().webcam.stopRecordingPipeline();
        this.robotModel.getCameraComponent().webcam.stopStreaming();
        Pipeline.parkingPosition = null;

        initActionSequence();
        currentAction = actionSequence.poll();
        currentAction.start();
    }

    void initActionSequence() {
        List<Coordinates> blueConesCoordinates = new ArrayList<>();
        blueConesCoordinates.add(fieldModel.leftBlueConePile);
        blueConesCoordinates.add(fieldModel.rightBlueConePile);

        List<Coordinates> redConesCoordinates = new ArrayList<>();
        blueConesCoordinates.add(fieldModel.leftRedConePile);
        blueConesCoordinates.add(fieldModel.rightRedConePile);
        if(robotModel.robotTeamColor == RobotTeamColor.Red) {
            Coordinates conePileCoordinates = Coordinates.findClosestCoordinates(robotModel.coordinates, redConesCoordinates);

        }


    }


    @Override
    public void update() {
        if(currentAction.isFinished()) {

            if(actionSequence.peek() != null) {
                currentAction = actionSequence.poll();
                currentAction.start();
            } else {
                finishTheBehaviour();
            }

        } else {
            currentAction.update();
        }
    }



    void finishTheBehaviour() {
        AutonomousEntryPoint.currentBehaviour = new PlaceConeBehaviour(robotModel,telemetry);
        //AutonomousEntryPoint.currentBehaviour = new ToPoleBehaviour(robotModel,telemetry);

    }
}
