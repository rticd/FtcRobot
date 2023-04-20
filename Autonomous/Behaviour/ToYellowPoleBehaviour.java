package org.firstinspires.ftc.teamcode.Autonomous.Behaviour;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousEntryPoint;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Pipeline;
import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.Actions.ClawMotionAction;
import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Common.Actions.MoveToCoordinatesAction;
import org.firstinspires.ftc.teamcode.Common.Actions.TickMotionAction;
import org.firstinspires.ftc.teamcode.Common.Components.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

import java.util.LinkedList;
import java.util.Queue;

public class ToYellowPoleBehaviour implements IBehaviour {
    Queue<IAction> actionSequence;
    IAction currentAction;
    RobotModel robotModel;
    Telemetry telemetry;

    FieldModel fieldModel;
    public ToYellowPoleBehaviour (RobotModel robotModel, FieldModel fieldModel, Telemetry telemetry){
        this.robotModel = robotModel;
        this.telemetry = telemetry;
        this.fieldModel = fieldModel;
        this.robotModel.getCameraComponent().webcam.stopRecordingPipeline();
        this.robotModel.getCameraComponent().webcam.stopStreaming();
        Pipeline.parkingPosition = null;

        actionSequence = new LinkedList<>();

        initActionSequence();
        currentAction = actionSequence.poll();
        currentAction.start();
    }

    void initActionSequence() {
        actionSequence.add(new LiftArmAction(robotModel, 30, telemetry));
        Coordinates initCoordinates = fieldModel.getHigherJunctions().get(0); //change later
        Coordinates targetCoordinates = new Coordinates(initCoordinates.getX()+0.14*FieldModel.CM_PER_TILE,initCoordinates.getY()-0.6* FieldModel.CM_PER_TILE);
        actionSequence.add(new MoveToCoordinatesAction(robotModel, targetCoordinates, telemetry));


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
