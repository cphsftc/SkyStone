import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Boomer", group="DriveModes")

public class Boomer extends OpMode {

    private double x, x2, y, y2, power, power2, aliPower;//Instantiating the vars that will be used for the power and direction of the DcMotors
    private DcMotor frontRight, frontLeft, backRight, backLeft, ali;//Instantiating the DcMotors for the
    private Servo leftArm, rightArm;
    private int count;
    private boolean armsOpen;

    @Override
    public void init() {

        //Setting all of the DcMotors the their current state by talking to the expansion hub
        frontRight = hardwareMap.dcMotor.get("FR");//1 - hub2
        frontLeft = hardwareMap.dcMotor.get("FL");//0 - hub2
        backRight = hardwareMap.dcMotor.get("BR");//2 - hub2
        backLeft = hardwareMap.dcMotor.get("BL");//3 - hub2
        ali = hardwareMap.dcMotor.get("ALI");//0 - hub1
        leftArm = hardwareMap.servo.get("LEFTARM");//0 - hub1
        rightArm = hardwareMap.servo.get("RIGHTARM");//1 - hub1

        //Setting the x and y vars to the default value of 0.0
        x = 0.0;
        y = 0.0;
        aliPower = 0;

        //Setting the other vars that will be needed for the collection system to their default values
        armsOpen = false;
        leftArm.setPosition(1.0000);
        rightArm.setPosition(0.000);
        count = 0;

    }

    @Override
    public void loop() {
        x = gamepad1.left_stick_x;//Setting the x var to the current state of the gamepad1 left stick x value (this is the robots horizontal movement)
        x2 = gamepad1.right_stick_x;//Setting the x2 var to the current state of the gamepad1 right stick x value (this is the robots rotational movement)
        y = -gamepad1.left_stick_y;//Setting the y var to the current state of the gamepad1 left stick y value (this is the robots vertical movement)

        power = 1;
        power2 = 1;

        // TODO: 11/20/2019 fiddle around with the numbers below to find something that works well
        //Setting the power and power2 to either normal speed or half speed based on the gamepad1 right bumper
        if(gamepad1.left_bumper){
            power /= 2;
            power2 /= 2;
        }

        if(gamepad1.right_bumper){
            power /= 3;
            power2 /= 3;
        }

        aliPower = 0;
        aliPower += gamepad1.right_trigger;
        aliPower -= gamepad1.left_trigger;

        //Count makes sure that the 'a' button can be held down and the servos will not spas out
        if(count < 0) count = 0;

        //Opens the servo if the 'a' button is pressed on gamepad1
        if(gamepad1.a){
            if(count==0) {
                armsOpen = !armsOpen;
            }
            count=2;

        }

        //Sets the servo positions based on if they should be open or closed (the servoOpen var)
        if(armsOpen){
            leftArm.setPosition(0.00000);
            rightArm.setPosition(1.0000);
        }
        else{
            leftArm.setPosition(1.0000);
            rightArm.setPosition(0.000);
        }

        //count must decrease so that we can change the servos after they have been pressed once
        count--;
        //endregion

        //Setting the different motors to their respective power for lateral movement
        frontLeft.setPower(power * ( x - y ) );
        frontRight.setPower(power * ( x + y ) );
        backLeft.setPower(power * ( - x - y ) );
        backRight.setPower(power * ( - x + y ) );

        //Setting different motors to their respective power for rotational movement
        frontLeft.setPower(power2 * -x2);
        frontRight.setPower(power2 * -x2);
        backLeft.setPower(power2 * -x2);
        backRight.setPower(power2 * -x2);

        ali.setPower(aliPower);

        //region Telemetry Data

        //Displaying all of the different motor powers to the Driver Controller
        telemetry.addData("FL PWR", power * ( x - y ) );
        telemetry.addData("FR PWR", power * ( x + y ) );
        telemetry.addData("BL PWR", power * ( - x - y ) );
        telemetry.addData("BR PWR", power * ( - x + y ) );
        telemetry.addData("ALI PWR", aliPower );
        //endregion
    }

}