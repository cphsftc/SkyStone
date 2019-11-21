package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Thread.sleep;

@Autonomous(name = "Vector Auto", group = "DriveModes")

public class Vector_Auto extends LinearOpMode {

    private DcMotor frontRight, frontLeft, backRight, backLeft;//Instantiating the DcMotors for the wheels

    private Servo serLeft, serRight;//Instantiating the Servo motors for the collection mechanism
    private boolean servoOpen;//Instantiating a boolean that will be used for the collection system
    private int count;//Instantiating a count that will be used for the collection system

    private double x, x2, y, y2, power, power2;//Instantiating the vars that will be used for the power and direction of the DcMotors
    private double frontRightPower, frontLeftPower, backRightPower, backLeftPower;//Instantiating the different power vars for the different DcMotors
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        //Setting the x and y vars to the default value of 0.0
        x = 0.0;
        y = 0.0;

        //Setting all of the power vars to the default value of 0.0
        frontRightPower = frontLeftPower = backRightPower = backLeftPower = 0.0;

        //Setting the servos to their current state by talking to the expansion hub
        serLeft = hardwareMap.servo.get("SL");
        serRight = hardwareMap.servo.get("SR");

        //Setting the other vars that will be needed for the collection system to their default values
        servoOpen = false;
        serLeft.setPosition(1.0000);
        serRight.setPosition(0.000);
        count = 0;

        ///*

        //Setting all of the DcMotors the their current state by talking to the expansion hub
        frontRight = hardwareMap.dcMotor.get("FR");
        frontLeft = hardwareMap.dcMotor.get("FL");
        backRight = hardwareMap.dcMotor.get("BR");
        backLeft = hardwareMap.dcMotor.get("BL");
        //*/

        waitForStart();
        servoOpen = false;
        runtime.reset();
        while (opModeIsActive()){
            servoOpen = !servoOpen;

            if(servoOpen){
                serLeft.setPosition(0.00000);
                serRight.setPosition(1.0000);
            }
            else{
                serLeft.setPosition(1.0000);
                serRight.setPosition(0.000);
            }

            //Setting the different motors to their respective power for lateral movement
            frontLeft.setPower(power * ( x + y ) );
            frontRight.setPower(power * ( x - y ) );
            backLeft.setPower(power * ( - x + y ) );
            backRight.setPower(power * ( - x - y ) );

            //Setting different motors to their respective power for rotational movement
            frontLeft.setPower(power2 * -x2);
            frontRight.setPower(power2 * -x2);
            backLeft.setPower(power2 * -x2);
            backRight.setPower(power2 * -x2);

            //*/

            sleep(1000);
        }
    }
}
