import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Boomer", group="DriveModes")

public class Boomer extends OpMode {

    private double x, x2, y, y2, power, power2;//Instantiating the vars that will be used for the power and direction of the DcMotors
    private DcMotor frontRight, frontLeft, backRight, backLeft;//Instantiating the DcMotors for the wheels

    @Override
    public void init() {

        //Setting the x and y vars to the default value of 0.0
        x = 0.0;
        y = 0.0;

        //Setting all of the DcMotors the their current state by talking to the expansion hub
        frontRight = hardwareMap.dcMotor.get("FR");
        frontLeft = hardwareMap.dcMotor.get("FL");
        backRight = hardwareMap.dcMotor.get("BR");
        backLeft = hardwareMap.dcMotor.get("BL");

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

        //region Telemetry Data

        //Displaying all of the different motor powers to the Driver Controller
        telemetry.addData("FR PWR", power * ( - x + y ) );
        telemetry.addData("BR PWR", power * - ( y + x ) );
        telemetry.addData("FL PWR", power * ( y + x ) );
        telemetry.addData("BL PWR", power * ( - x + y ) );
        //endregion
    }

}
