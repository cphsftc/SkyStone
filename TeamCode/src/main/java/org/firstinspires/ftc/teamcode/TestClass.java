package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="testAuto")

public class TestClass extends LinearOpMode {
    private double speed;
    private DcMotor motorOne;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        speed = 0.0;
        motorOne = hardwareMap.dcMotor.get("motorOne");

        waitForStart();
        runtime.reset();
        while(true){
            speed = 1.00000000000000;
            motorOne.setPower(speed);
            sleep(1000);
            motorOne.setPower(0);
            sleep(2000);
            motorOne.setPower(-speed);
            sleep(1000);
            motorOne.setPower(0);
            sleep(2000);
        }
    }
}
