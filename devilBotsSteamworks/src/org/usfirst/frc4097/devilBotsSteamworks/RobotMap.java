// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4097.devilBotsSteamworks;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveTrainleftMotor;
    public static SpeedController driveTrainrightMotor;
    public static RobotDrive driveTrainrobotDrive;
    public static SpeedController climberclimberMotor;
    public static SpeedController gearCatchergearMotor;
    public static DigitalInput gearCatchertopSwitch;
    public static DigitalInput gearCatcherbottomSwitch;
    public static SpeedController shootershooterMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainleftMotor = new Victor(0);
        LiveWindow.addActuator("driveTrain", "leftMotor", (Victor) driveTrainleftMotor);
        
        driveTrainrightMotor = new Victor(1);
        LiveWindow.addActuator("driveTrain", "rightMotor", (Victor) driveTrainrightMotor);
        
        driveTrainrobotDrive = new RobotDrive(driveTrainleftMotor, driveTrainrightMotor);
        
        driveTrainrobotDrive.setSafetyEnabled(true);
        driveTrainrobotDrive.setExpiration(0.1);
        driveTrainrobotDrive.setSensitivity(0.5);
        driveTrainrobotDrive.setMaxOutput(1.0);
        driveTrainrobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        driveTrainrobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        climberclimberMotor = new Spark(3);
        LiveWindow.addActuator("climber", "climberMotor", (Spark) climberclimberMotor);
        
        gearCatchergearMotor = new Spark(2);
        LiveWindow.addActuator("gearCatcher", "gearMotor", (Spark) gearCatchergearMotor);
        
        gearCatchertopSwitch = new DigitalInput(0);
        LiveWindow.addSensor("gearCatcher", "topSwitch", gearCatchertopSwitch);
        
        gearCatcherbottomSwitch = new DigitalInput(1);
        LiveWindow.addSensor("gearCatcher", "bottomSwitch", gearCatcherbottomSwitch);
        
        shootershooterMotor = new Victor(4);
        LiveWindow.addActuator("shooter", "shooterMotor", (Victor) shootershooterMotor);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
