package org.firstinspires.ftc.teamcode.Common.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.Components.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

import java.util.LinkedList;
import java.util.Queue;

public class MoveToCoordinatesAction extends BaseAction {
    Queue<IAction> actions;
    IAction currentAction;

    DriveComponent driveComponent;

    Coordinates targetCoordinates;

    public MoveToCoordinatesAction(RobotModel robotModel,
                                   Coordinates targetCoordinates, Telemetry telemetry) {
        super(robotModel, telemetry);
        driveComponent = robotModel.getDriveComponent();
        this.targetCoordinates = targetCoordinates;
    }

    void initializeActions() {
        actions = new LinkedList<>();
        double absAngle = 90;
        double deltaAngle = robotModel.absAngle - absAngle;
        IAction rotateTo90Deg = new TurnAction(robotModel, deltaAngle, telemetry);
        actions.add(rotateTo90Deg);

        Coordinates vector = new Coordinates(
                targetCoordinates.getX() - robotModel.coordinates.getX(),
                targetCoordinates.getY() - robotModel.coordinates.getY()
        );

        IAction moveVertically = new TickMotionAction(robotModel, 1, vector.getY(),
                MotionDirection.vertical, telemetry);
        actions.add(moveVertically);

        IAction moveHorizontally = new TickMotionAction(robotModel, 1, vector.getX(),
                MotionDirection.horizontal, telemetry);
        actions.add(moveHorizontally);


    }

    //No async await :(
    @Override
    public void start() {
        if(finished) return;
        initializeActions();
         currentAction = actions.poll();
         currentAction.start();
    }

    //Sorry for such a messy solution. In don't know how to do it without coroutines or tasks.
    @Override
    public void update() {
        if(finished) return;
        telemetry.addData("current action in mtoa", currentAction);
        if(currentAction.isFinished())
            if(actions.peek() != null) {
                currentAction = actions.poll();
                currentAction.start();
            }
            else {
                finished = true;
                return;
            }
        currentAction.update();
    }
}
