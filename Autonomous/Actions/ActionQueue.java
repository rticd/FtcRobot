package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class ActionQueue {
    Queue<IAction> actionQueue;
    public void setNextAction(IAction action) {
        actionQueue.add(action);
    }

    IAction currentAction;
    public IAction getCurrentAction() {
        return currentAction;
    }

    boolean finished;
    public boolean isFinished() {
        return finished;
    }

    public ActionQueue() {
        actionQueue = new LinkedList<>();
    }

    public void start() {
        if(finished) return;
        currentAction = actionQueue.element();
        currentAction.start();
    }

    public void update() {
        if(finished) return;
        currentAction.update();
        if(currentAction.isFinished()) {
            currentAction = actionQueue.poll();
            if(currentAction != null)
                currentAction.start();
            else
                finished = true;
        }
    }
}
