package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class Decide {
    public static State decide(RobotModel model, ElapsedTime elapsedTime, State state){
        State result = state;
        if (AutoBlue.parkingPosition==null ){
            // if action same as before don't do anything
            result = State.Scan;
        }/*else if (elapsedTime.seconds() > 20){
            result = State.toPark;
        }*/ else if (result==State.Empty){
            if (model.withCone) {
                result = State.toPole;
            } else {
                result = State.toCone;
            }
        }
        return result;
    }
}

