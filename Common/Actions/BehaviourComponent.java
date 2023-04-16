package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.teamcode.Autonomous.FieldModel;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public abstract class BehaviourComponent {
    RobotModel robotModel;
    FieldModel fieldModel;


    public abstract void Act();
}
