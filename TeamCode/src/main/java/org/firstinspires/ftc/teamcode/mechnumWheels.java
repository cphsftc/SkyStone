package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="MechnumWheels", group="DriveModes")
public class mechnumWheels extends OpMode {
    //39a5c0

    private DcMotor frontRight, frontLeft, backRight, backLeft;//Instantiating the DcMotors for the wheels

    private Servo serLeft, serRight;//Instantiating the Servo motors for the collection mechanism
    private boolean servoOpen;//Instantiating a boolean that will be used for the collection system
    private int count;//Instantiating a count that will be used for the collection system

    private double x, x2, y, power, power2;//Instantiating the vars that will be used for the power and direction of the DcMotors
    private double frontRightPower, frontLeftPower, backRightPower, backLeftPower;//Instantiating the different power vars for the different DcMotors

    /**
     * The init method sets up all of the variables with default values.
     * The method will be run once upon clicking the init button on the Driver Controller app.
     */
    @Override
    public void init() {
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
    }

    /**
     * The loop method runs infinitely once the play button on the Driver Controller has been pressed.
     * The loop only stops once the play button on the Driver Controller has been pressed again.
     */
    @Override
    public void loop() {
        x = gamepad1.left_stick_x;//Setting the x var to the current state of the gamepad1 left stick x value (this is the robots horizontal movement)
        x2 = gamepad1.right_stick_x;//Setting the x2 var to the current state of the gamepad1 right stick x value (this is the robots rotational movement)
        y = -1*gamepad1.left_stick_y;//Setting the y var to the current state of the gamepad1 left stick y value (this is the robots vertical movement)

        power = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));//Setting the power var to the magnitude of the x and y vector (this is the speed of the robot)
        power = Math.pow(power, 2);

        power2 = Math.sqrt(Math.pow(x2, 2));//Setting the power var to the magnitude of the x and y vector (this is the speed of the robot)
        power2 = Math.pow(power2, 2);

        //Setting the power to either normal speed or half speed based on the gamepad1 right bumper
        if(gamepad1.left_bumper){
            power /= 2;
            power2 /= 2;
        }

        //region PickUp Mechanism

        //Count makes sure that the 'a' button can be held down and the servos will not spas out
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
            serLeft.setPosition(0.00000);
            serRight.setPosition(1.0000);
        }
        else{
            serLeft.setPosition(1.0000);
            serRight.setPosition(0.000);
        }

        //count must decrease so that we can change the servos after they have been pressed once
        count--;
        //endregion

        //region Drive Mechanism

        ///*

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

        //endregion

        //region Telemetry Data

        //Displaying the servo position and the servoOpen vars to the Driver Controller
        telemetry.addData("Servo Left: ", serLeft.getPosition());
        telemetry.addData("Servo Right: ", serRight.getPosition());
        telemetry.addData("Servo Open: ", servoOpen);

        //Displaying all of the different motor powers to the Driver Controller
        telemetry.addData("FR PWR", power * ( - x + y ) );
        telemetry.addData("BR PWR", power * - ( y + x ) );
        telemetry.addData("FL PWR", power * ( y + x ) );
        telemetry.addData("BL PWR", power * ( - x + y ) );
        //endregion
    }
}
