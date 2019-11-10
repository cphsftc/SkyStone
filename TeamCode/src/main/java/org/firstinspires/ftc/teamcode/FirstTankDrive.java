package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Trinity", group = "DriveModes")
public class FirstTankDrive extends OpMode {

    //Variables
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;
    DcMotor lift;
    private Servo serLeft, serRight;
    double leftStickSpeed;
    double rightStickSpeed;
    boolean servoOpen;
    int count;

    @Override
    public void init()
    {
        //frontLeft = hardwareMap.dcMotor.get("FL");
        //frontRight = hardwareMap.dcMotor.get("FR");
        //lift = hardwareMap.dcMotor.get("Lift");
        rearLeft = hardwareMap.dcMotor.get("RL");
        rearRight = hardwareMap.dcMotor.get("RR");

        /*
        serLeft = hardwareMap.servo.get("SL");
        serRight = hardwareMap.servo.get("SR");

        // */
        leftStickSpeed = 0;
        rightStickSpeed = 0;
        servoOpen = false;
        /*
        serLeft.setPosition(1.0000);
        serRight.setPosition(0.000);
        count = 0;

        // */
    }

    @Override
    public void loop()
    {
        leftStickSpeed = gamepad1.left_stick_y;
        rightStickSpeed = -gamepad1.right_stick_y;

        /*
        if(gamepad1.right_bumper){
            lift.setPower(-.1);
        }
        if(gamepad1.left_bumper){
            lift.setPower(.1);
        }

        // */
        //frontLeft.setPower(leftStickSpeed);
        rearLeft.setPower(leftStickSpeed);
        //frontRight.setPower(rightStickSpeed);
        rearRight.setPower(rightStickSpeed);

        /*

        if(count < 0) count = 0;
        //Opens the servo if the 'a' button is pressed on gamepad1
        if(gamepad1.a){
            if(count==0) {
                servoOpen = !servoOpen;
            }
            count=2;

        }
        if(servoOpen){
            serLeft.setPosition(0.00000);
            serRight.setPosition(1.0000);
        }
        else{
            serLeft.setPosition(1.0000);
            serRight.setPosition(0.000);
        }
        //count must decrease so that we can change the servos after they have been pressed once
        count--;

        // */
    }
}//Class
