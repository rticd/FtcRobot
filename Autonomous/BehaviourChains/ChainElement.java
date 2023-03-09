package org.firstinspires.ftc.teamcode.Autonomous.BehaviourChains;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;

public class ChainElement implements IChainElement {
    IChainElement previousElement;
    @Override
    public void setPreviousElement(IChainElement previousElement) {
        this.previousElement = previousElement;
    }

    IChainElement nextElement;
    @Override
    public void setNextElement(IChainElement nextElement) {
        if(this.nextElement != null)
        {
            this.setNextElement(nextElement);
            return;
        } else if(nextElement != null) {
            this.nextElement = nextElement;
            nextElement.setPreviousElement(this);
        }

    }

    IAction currentAction;
    @Override
    public IAction getAction() {
        return currentAction;
    }

    @Override
    public void start() {
        currentAction.start();
    }

    @Override
    public void update() {
        currentAction.update();
        if(currentAction.isFinished()) {
            if(nextElement != null)
                nextElement.start();
            else
                closeChain();
        }
    }

    boolean chainClosed;
    @Override
    public boolean chainClosed() {
        return false;
    }
    @Override
    public void closeChain() {
        chainClosed = true;
        if(previousElement != null)
            previousElement.closeChain();
    }
    public ChainElement(IAction currentAction) {
        this.currentAction = currentAction;
    }
}
