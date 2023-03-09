package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.FigureOutParkingPositionAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.GrabAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveToPositionLongestPathAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveToPositionShortestPathAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.NoAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.UngrabAction;
import org.firstinspires.ftc.teamcode.Autonomous.BehaviourChains.ChainElement;
import org.firstinspires.ftc.teamcode.Autonomous.BehaviourChains.IChainElement;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class RobotController {
    Telemetry telemetry;
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    RobotModel model;
    FieldModel fieldModel;
    DriveComponent driveComponent;
    ArmComponent armComponent;


    IChainElement behaviourChain;

    public RobotController(RobotModel model, FieldModel fieldModel, DriveComponent driveComponent, ArmComponent armComponent) {
        this.model = model;
        this.fieldModel = fieldModel;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        currentAction = new NoAction();
        constructBehaviourChain();
    }

    void constructBehaviourChain() {
        //First chain: Move to the junction
        Coordinates closestJunction = Coordinates.findClosestCoordinates(model.coordinates, fieldModel.higherJunctions);
        IAction moveToJunctionAction = new MoveToPositionShortestPathAction(model, driveComponent, closestJunction);
        behaviourChain = new ChainElement(moveToJunctionAction);

        //Second chain: Put up the arm
        IAction putUpArmAction = new LiftArmAction(model, armComponent, ArmPosition.Third);
        behaviourChain.setNextElement(new ChainElement(putUpArmAction));

        //Third chain: Release the cone
        IAction releaseConeAction = new UngrabAction(model, armComponent);
        behaviourChain.setNextElement(new ChainElement(releaseConeAction));

        //Fourth chain: Put down the arm
        IAction putDownArmAction = new LiftArmAction(model, armComponent, ArmPosition.Zero);
        behaviourChain.setNextElement(new ChainElement(putDownArmAction));

        //Fifth chain: Return to starting position
        IAction moveToStartingPosition = new MoveToPositionShortestPathAction(model, driveComponent,
                                         fieldModel.startingPosition);
        behaviourChain = new ChainElement(moveToStartingPosition);

        //Sixth chain: Move to colored cone
        IAction moveToColoredCone = new MoveToPositionShortestPathAction(model, driveComponent,
                Coordinates.add(model.coordinates, fieldModel.getColoredConeVectorFromStatingPosition()));
        behaviourChain = new ChainElement(moveToColoredCone);

        //Seventh chain: Scan colored cone
        IAction scanCone = new FigureOutParkingPositionAction(model, armComponent);
        behaviourChain.setNextElement(new ChainElement(scanCone));

        //Eighth chain: Pick up colored cone
        IAction pickUpColoredCone = new GrabAction(model, armComponent);
        behaviourChain.setNextElement(new ChainElement(pickUpColoredCone));

        //Ninth chain: Move to parking position
        IAction moveToParkingPosition = new MoveToPositionShortestPathAction(model, driveComponent,
                                        model.parkingCoordinates);
        behaviourChain.setNextElement(new ChainElement(moveToParkingPosition));

        //ВСЁ!

    }

    public void start() {
        if(!behaviourChain.chainClosed())
            behaviourChain.start();
    }
    public void update() {
        currentAction.update(); //remove later;
        if(!behaviourChain.chainClosed())
            behaviourChain.update();
    }

    //Two ways to move to coordinates: short and long one


    //For testing
    IAction currentAction;
    public void testMoveToPositionAction(Coordinates position) {
        if(currentAction.isFinished()) {
            MoveToPositionLongestPathAction action = new MoveToPositionLongestPathAction(model, driveComponent, position);
            currentAction = action;
            currentAction.start();
        }
    }
}
