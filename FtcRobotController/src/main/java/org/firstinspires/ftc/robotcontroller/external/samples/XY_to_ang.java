package org.firstinspires.ftc.robotcontroller.external.samples;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; //importation of data about the robot's hardware
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.adafruit.AdafruitBNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.util.ReadWriteFile;
import java.util.regex.Pattern;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import java.io.File;
import java.util.Locale;

@TeleOp
public class XY_to_ang extends LinearOpMode{
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");

        waitForStart();
        double DriveSpeed;
        double DriveAngle;
        double Rotate;
        double v0;
        double v1;
        double v2;
        double v3;
        double maxValue;


        while (opModeIsActive()) {
            DriveSpeed = JavaUtil.minOfList(JavaUtil.createListWith(1, Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y)));
            DriveAngle = Math.atan2(-gamepad1.left_stick_x, -gamepad1.left_stick_y) / Math.PI * 180;
            Rotate = Math.pow(gamepad1.right_stick_x, 3);
            DriveSpeed = Math.pow(DriveSpeed, 3);
            v0 = DriveSpeed * (Math.cos(DriveAngle / 180 * Math.PI) - Math.sin(DriveAngle / 180 * Math.PI)) + Rotate;
            v1 = DriveSpeed * (Math.cos(DriveAngle / 180 * Math.PI) + Math.sin(DriveAngle / 180 * Math.PI)) + Rotate;

            telemetry.addData("r (magnitude)", DriveSpeed);
            telemetry.addData("robotAngle", DriveAngle);
            telemetry.addData("leftMotor", Math.round(v0 * 100) / 100);
            telemetry.addData("rightMotor", Math.round(v1 * 100) / 100);

            telemetry.update();

            maxValue = JavaUtil.maxOfList(JavaUtil.createListWith(Math.abs(v0), Math.abs(v1), 1));
            //leftMotor.setPower(v0 / maxValue);
            //rightMotor.setPower(v1 / maxValue);

            }
        }
    }

