package org.firstinspires.ftc.teamcode.Common;



import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class SensorComponent {
    public BNO055IMU imu;

    public DistanceSensor sensorRange;

    public SensorComponent(BNO055IMU imu, DistanceSensor sensorRange){
        this.imu = imu;
        this.sensorRange = sensorRange;

    }
}
