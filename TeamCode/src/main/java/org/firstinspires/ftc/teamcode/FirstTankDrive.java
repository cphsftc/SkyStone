package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "YeetBoizDrive", group = "DriveModes")
public class FirstTankDrive extends OpMode {

    //Variables
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;
    double leftStickSpeed;
    double rightStickSpeed;

    @Override
    public void init()
    {
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        rearLeft = hardwareMap.dcMotor.get("RL");
        rearRight = hardwareMap.dcMotor.get("RR");
        leftStickSpeed = 0;
        rightStickSpeed = 0;
    }

    @Override
    public void loop()
    {
        leftStickSpeed = gamepad1.left_stick_y;
        rightStickSpeed = -gamepad1.right_stick_y;
        frontLeft.setPower(leftStickSpeed);
        rearLeft.setPower(leftStickSpeed);
        frontRight.setPower(rightStickSpeed);
        rearRight.setPower(rightStickSpeed);
    }
}//Class
