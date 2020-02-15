package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "chillyTrash")

public class ChillyCookOffIsDumb extends OpMode {
    private DcMotor frontR, backR, frontL, backL;
    private Servo leftS, rightS;
    private double x, y;
    private boolean servoOpen;
    private int count;

    @Override
    public void init() {
        frontR = hardwareMap.dcMotor.get("FR");
        backR = hardwareMap.dcMotor.get("BR");
        frontL = hardwareMap.dcMotor.get("FL");
        backL = hardwareMap.dcMotor.get("BL");

        leftS = hardwareMap.servo.get("left");
        rightS = hardwareMap.servo.get("right");

        servoOpen = false;
        leftS.setPosition(1.0000);
        rightS.setPosition(0.000);
        count = 0;

        x = y = 0.;
    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_y;
        y = gamepad1.right_stick_y;

        frontR.setPower(-y);
        backR.setPower(-y);
        frontL.setPower(x);
        backL.setPower(x);

        if(count < 0) count = 0;

        //Opens the servo if the 'a' button is pressed on gamepad1
        if(gamepad1.a){
            if(count==0) {
                servoOpen = !servoOpen;
            }
            count=2;

        }

        //Sets the servo positions based on if they should be open or closed (the servoOpen var)
        if(servoOpen){
            leftS.setPosition(0.00000);
            rightS.setPosition(1.0000);
        }
        else{
            leftS.setPosition(1.0000);
            rightS.setPosition(0.000);
        }

        //count must decrease so that we can change the servos after they have been pressed once
        count--;
    }
}
