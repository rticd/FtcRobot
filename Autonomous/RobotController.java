package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.ActionQueue;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.GrabAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveToPositionLongestPathAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveToPositionShortestPathAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.ScanForParkingPosition;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveForDistanceAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.UngrabAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.WaitAction;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class RobotController {
    Telemetry telemetry;
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    Coordinates selectedJunction;
    Coordinates middleSquare;
    RobotModel model;
    FieldModel fieldModel;
    DriveComponent driveComponent;
    ArmComponent armComponent;

    ActionQueue actionQueue;

    public RobotController(RobotModel model, FieldModel fieldModel, DriveComponent driveComponent, ArmComponent armComponent, Telemetry telemetry) {
        this.model = model;
        this.fieldModel = fieldModel;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        this.telemetry = telemetry;
        constructActionSequence();
    }

    void constructActionSequence() {
        actionQueue = new ActionQueue();
        selectedJunction = Coordinates.findClosestCoordinates(model.coordinates, fieldModel.higherJunctions);
        middleSquare = new Coordinates(selectedJunction.getX()-fieldModel.cmPerTile/2, selectedJunction.getY()-fieldModel.cmPerTile/2);
        //constructConePlacementQueue();
        //constructReturnToInitialPositionQueue();
        constructParkingQueue();
    }



    public void constructConePlacementQueue() {
        //0: Picking up the cone
        //0.1. action: Pick up the cone
        IAction pickUpInitialCone = new GrabAction(model, armComponent);
        pickUpInitialCone.setTelemetry(telemetry);
        actionQueue.setNextAction(pickUpInitialCone);

        //0.2. action: Wait
        IAction waitAction = new WaitAction(2000);
        waitAction.setTelemetry(telemetry);
        actionQueue.setNextAction(waitAction);

        //0.3. action: Lift the cone
        IAction putUpArmAction3 = new LiftArmAction(model, armComponent, ArmPosition.Third);
        putUpArmAction3.setTelemetry(telemetry);
        actionQueue.setNextAction(putUpArmAction3);

        //1: Moving to junction

        IAction moveToMiddleSquareAction = new MoveToPositionLongestPathAction(model, driveComponent, middleSquare);
        moveToMiddleSquareAction.setTelemetry(telemetry);
        actionQueue.setNextAction(moveToMiddleSquareAction);



        //1.3. action: Move to the junction itself
        IAction moveToJunction = new MoveToPositionShortestPathAction(model, driveComponent,
                new Coordinates(selectedJunction.getX(), selectedJunction.getY()), model.MOVEMENT_OFFSET_FOR_ROBOT);
        moveToJunction.setTelemetry(telemetry);
        actionQueue.setNextAction(moveToJunction);

        //2: Putting down the cone

        //2.1. action: Put down the arm
        IAction putDownArmAction = new LiftArmAction(model, armComponent, ArmPosition.First);
        putDownArmAction.setTelemetry(telemetry);
        actionQueue.setNextAction(putDownArmAction);
        //2.2. action: Release the cone
        IAction releaseConeAction = new UngrabAction(model, armComponent);
        releaseConeAction.setTelemetry(telemetry);
        actionQueue.setNextAction(releaseConeAction);
    }

    public void constructReturnToInitialPositionQueue() {
        // 3: Returning to the starting position
        //3.0 action: Return to middle square
        Coordinates vector = new Coordinates(
                middleSquare.getX() - model.coordinates.getX(),
                middleSquare.getY() - model.coordinates.getY()
        );
        double distanceToMove = Math.sqrt(
                vector.getX()*vector.getX() + vector.getY()*vector.getY()
        );
        IAction moveBackToMiddleSquare = new MoveForDistanceAction(model, driveComponent, -distanceToMove/2);
        moveBackToMiddleSquare.setTelemetry(telemetry);
        actionQueue.setNextAction(moveBackToMiddleSquare);

        //3.1 action: Return to initial square
        IAction moveBackToInitialPosition = new MoveToPositionLongestPathAction(model, driveComponent,
                fieldModel.startingPosition);
        moveBackToInitialPosition.setTelemetry(telemetry);
        actionQueue.setNextAction(moveBackToInitialPosition);
    }

    public void constructParkingQueue() {
        //4: Going and scanning the colored cone
        //4.1. action: Move to colored cone
        IAction rotateTo90 = new RotateAction(model,driveComponent, 90);
        rotateTo90.setTelemetry(telemetry);
        actionQueue.setNextAction(rotateTo90);

        IAction moveToColoredCone = new MoveForDistanceAction(model, driveComponent,
                fieldModel.getColoredConeVectorFromStatingPosition().getY() - model.MOVEMENT_OFFSET_FOR_ROBOT - 15);
        moveToColoredCone.setTelemetry(telemetry);
        actionQueue.setNextAction(moveToColoredCone);

        //4.2 action: Scan colored cone and move to parking position
        IAction scanCone = new ScanForParkingPosition(model, fieldModel, driveComponent,armComponent, 15
                , middleSquare);
        scanCone.setTelemetry(telemetry);
        actionQueue.setNextAction(scanCone);
//        //ВСЁ!
    }

//    public void everythingElseFucksUpMethod() {
//        //4: Going and scanning the colored cone
//        //4.1. action: Move to colored cone
//        if(model.absAngle != 90)
//        {
//            IAction rotateTo90 = new RotateAction(model, driveComponent, 90);
//            rotateTo90.setTelemetry(telemetry);
//            actionQueue.setNextAction(rotateTo90);
//        }
//        IAction moveToColoredCone = new MoveForDistanceAction(model, driveComponent,
//                fieldModel.getColoredConeVectorFromStatingPosition().getY() - model.MOVEMENT_OFFSET_FOR_ROBOT);
//        moveToColoredCone.setTelemetry(telemetry);
//        actionQueue.setNextAction(moveToColoredCone);
//
//        //4.2 action: Scan colored cone and move to parking position
//        IAction scanCone = new ScanForParkingPosition(model, fieldModel, driveComponent,armComponent, 100);
//        scanCone.setTelemetry(telemetry);
//        actionQueue.setNextAction(scanCone);
//
//        //4.3 action: Move to parking position
//
//        IAction moveToParkingPosition = new MoveToPositionLongestPathAction(model, driveComponent, model.parkingCoordinates);
//        moveToParkingPosition.setTelemetry(telemetry);
//        actionQueue.setNextAction(moveToParkingPosition);
//        //ВСЁ!
//    }
    public void start() {
        if(!actionQueue.isFinished())
            actionQueue.start();
    }
    public void update() {
        //telemetry.addData("current model coordinates x", model.coordinates.getX());
        //telemetry.addData("current model coordinates y", model.coordinates.getY());
        telemetry.addData("scaned color:", model.color);
        if(actionQueue.getCurrentAction() != null) {
             telemetry.addData("current action: ", actionQueue.getCurrentAction());
             telemetry.addData("current finished: ", actionQueue.getCurrentAction().isFinished());
        }
        if(!actionQueue.isFinished()) {
            actionQueue.update();
        }

    }
}
