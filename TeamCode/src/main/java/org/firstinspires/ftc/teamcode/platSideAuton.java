package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Platform Auton")

public class platSideAuton extends LinearOpMode {

    private double speedOne;
    private double speedTwo;
    private double speedThree;
    private double speedFour;

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        speedOne = 0.0;
        speedTwo = 0.0;
        speedThree = 0.0;
        speedFour = 0.0;

        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");

        waitForStart();
        runtime.reset();

        speedOne = -1.0;
        speedTwo = 1.0;
        speedThree = 1.0;
        speedFour = -1.0;

        frontLeft.setPower(speedOne);
        frontRight.setPower(speedTwo);
        backLeft.setPower(speedThree);
        backRight.setPower(speedFour);

        sleep(400);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }
}
