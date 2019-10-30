package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="MechnumWheels", group="DriveModes")
public class mechnumWheels extends OpMode {
    //lets go
    private DcMotor frontRight, frontLeft, backRight, backLeft;
    private Servo serLeft, serRight;
    private double x, x2, y, power;
    private double frontRightPower, frontLeftPower, backRightPower, backLeftPower;
    double theta = 0.0;

    @Override
    public void init() {
        x = 0.0;
        y = 0.0;
        frontRightPower = 0.0;
        frontLeftPower = 0.0;
        backRightPower = 0.0;
        backLeftPower = 0.0;
        serLeft = hardwareMap.servo.get("SL");
        serRight = hardwareMap.servo.get("SR");
        frontRight = hardwareMap.dcMotor.get("FR");
        frontLeft = hardwareMap.dcMotor.get("FL");
        backRight = hardwareMap.dcMotor.get("BR");
        backLeft = hardwareMap.dcMotor.get("BL");
    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_x;
        y = -1*gamepad1.left_stick_y;

        x2 = gamepad1.right_stick_x;

        power = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        /*
        if(x!=0.0 && y!=0.0){
            theta = (Math.atan(y/x));
            power = 1.0;
        }
        else if(x==0.0){
            if (y>0.0) {
                theta = Math.toRadians(-90.0);
            }
            else{
                theta = Math.toRadians(90.0);
            }
        }
        else if(y==0.0){
            if(x>0.0){
                theta = Math.toRadians(-90.0);
            }
            else{
                theta = Math.toRadians(90.0);
            }
        }

        frontRight.setPower(power*(Math.pow(y, 2)-Math.pow(x, 2)));
        frontLeft.setPower(-(power*(Math.pow(y, 2)+Math.pow(x, 2))));
        backRight.setPower(power*(Math.pow(y, 2)+Math.pow(x, 2)));
        backLeft.setPower(-(power*(Math.pow(y, 2)-Math.pow(x, 2))));
        */

        frontLeft.setPower(power*(x+y));
        frontRight.setPower(power*(x-y));
        backLeft.setPower(power*(-x+y));
        backRight.setPower(power*(-x-y));

        frontLeft.setPower(-x2);
        frontRight.setPower(-x2);
        backLeft.setPower(-x2);
        backRight.setPower(-x2);


        telemetry.addData("FR PWR", power*(-x+y));
        telemetry.addData("BR PWR", power*-(y+x));
        telemetry.addData("FL PWR", power*(y+x));
        telemetry.addData("BL PWR", power*(-x+y));
    }
}
