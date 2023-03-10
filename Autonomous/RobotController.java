package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.ActionQueue;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.FigureOutParkingPositionAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.GrabAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveToPositionLongestPathAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveToPositionShortestPathAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.NoAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.UngrabAction;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RGBColors;

public class RobotController {
    Telemetry telemetry;
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    RobotModel model;
    FieldModel fieldModel;
    DriveComponent driveComponent;
    ArmComponent armComponent;


    ActionQueue actionQueue;

    public RobotController(RobotModel model, FieldModel fieldModel, DriveComponent driveComponent, ArmComponent armComponent) {
        this.model = model;
        this.fieldModel = fieldModel;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        currentAction = new NoAction();
        constructActionSequence();
    }

    void constructActionSequence() {
        actionQueue = new ActionQueue();

        //First chain: Move to the junction
        Coordinates closestJunction = Coordinates.findClosestCoordinates(model.coordinates, fieldModel.higherJunctions);
        IAction moveToJunctionAction = new MoveToPositionShortestPathAction(model, driveComponent, closestJunction);
        actionQueue.setNextAction(moveToJunctionAction);

        //Second chain: Put up the arm
        IAction putUpArmAction = new LiftArmAction(model, armComponent, ArmPosition.Third);
        actionQueue.setNextAction(putUpArmAction);

        //Third chain: Release the cone
        IAction releaseConeAction = new UngrabAction(model, armComponent);
        actionQueue.setNextAction(releaseConeAction);


        //Fourth chain: Put down the arm
        IAction putDownArmAction = new LiftArmAction(model, armComponent, ArmPosition.Zero);
        actionQueue.setNextAction(putDownArmAction);


        //Fifth chain: Return to starting position
        IAction moveToStartingPosition = new MoveToPositionShortestPathAction(model, driveComponent,
                                         fieldModel.startingPosition);
        actionQueue.setNextAction(moveToStartingPosition);


        //Sixth chain: Move to colored cone
        IAction moveToColoredCone = new MoveToPositionShortestPathAction(model, driveComponent,
                Coordinates.add(model.coordinates, fieldModel.getColoredConeVectorFromStatingPosition()));
        actionQueue.setNextAction(moveToColoredCone);


        //Seventh chain: Scan colored cone
        IAction scanCone = new FigureOutParkingPositionAction(model, fieldModel,armComponent);
        actionQueue.setNextAction(scanCone);


        //Eighth chain: Pick up colored cone
        IAction pickUpColoredCone = new GrabAction(model, armComponent);
        actionQueue.setNextAction(pickUpColoredCone);

        //Ninth chain: Move to parking position
        IAction moveToParkingPosition = new MoveToPositionShortestPathAction(model, driveComponent,
                                        model.parkingCoordinates);
        actionQueue.setNextAction(moveToParkingPosition);


        //ВСЁ!

    }

    public void start() {
        if(!actionQueue.isFinished())
            actionQueue.start();
    }
    public void update() {
        //currentAction.update(); //remove later;
        if(!actionQueue.isFinished())
            actionQueue.update();
    }


    //For testing
    IAction currentAction;
    public void testMoveToPositionAction(Coordinates position) {
        if(currentAction.isFinished()) {
            MoveToPositionShortestPathAction action = new MoveToPositionShortestPathAction(model, driveComponent, position);
            currentAction = action;
            currentAction.start();
        }
    }
}
