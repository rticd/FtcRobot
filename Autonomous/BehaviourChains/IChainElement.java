package org.firstinspires.ftc.teamcode.Autonomous.BehaviourChains;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;

//Basically, it's a wrapper around an action that automatically invokes another chain element after
//it's action has been finished.
public interface IChainElement {
    void start();
    void update();
    boolean chainClosed();
    void closeChain();
    public void setPreviousElement(IChainElement previousElement);
    public void setNextElement(IChainElement nextElement);
    IAction getAction();
}
