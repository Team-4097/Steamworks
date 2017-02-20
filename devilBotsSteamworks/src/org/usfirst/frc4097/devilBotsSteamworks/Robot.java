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

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Rect;

import grip.GripPipeline;

import org.usfirst.frc4097.devilBotsSteamworks.commands.*;
import org.usfirst.frc4097.devilBotsSteamworks.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	private double width = 0.0;
	private RobotDrive driveBot;
	
	private final Object imgLock = new Object();
	

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static driveTrain driveTrain;
    public static climber climber;
    public static gearCatcher gearCatcher;
    public static shooter shooter;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
    
    UsbCamera visionCamera = CameraServer.getInstance().startAutomaticCapture(0);
    //UsbCamera driveCamera = CameraServer.getInstance().startAutomaticCapture(1);
    visionCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    
    visionThread = new VisionThread(visionCamera, new GripPipeline(), pipeline -> {
        if (!pipeline.filterContoursOutput().isEmpty()) {
            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            //Rect q = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));

            
            synchronized (imgLock) {
                centerX = (r.x + (r.width / 2));
                width = r.width;
            }
        }
    });
    visionThread.start();
    
    
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new driveTrain();
        climber = new climber();
        gearCatcher = new gearCatcher();
        shooter = new shooter();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        double centerX;
        double width;
    	synchronized (imgLock) {
    		centerX = this.centerX;
    		width = this.width;
    		SmartDashboard.putNumber("Center of Target", centerX);
    		SmartDashboard.putNumber("Target Width", width);
    	}
    	double turn = centerX - (IMG_WIDTH / 2);
    	double drive = (width - 39)/39;
    	
    	double turnSpeed = map(turn,90,26,0.3,-0.3);
    	
    	for(int i = 50; i > 0; i--){
    		drive += (width - 39)/39;
    	}
    	drive /= 50;
    	drive /= 1.15;
    	
    	if(width < 35){
    		//Robot.driveTrain.altDrive(-0.5f,0);
    	}else if(width > 35 && width < 50){
    		//Robot.driveTrain.altDrive(-0.45f,0);
    	}else{
    		//Robot.driveTrain.altDrive(0, 0);
    	}
    	SmartDashboard.putNumber("Turn Distance", turn);
    	SmartDashboard.putNumber("Turn Speed", turnSpeed);
    	SmartDashboard.putNumber("Drive Speed", drive);
        
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        boolean driveWithLeap = false;
        
        if(driveWithLeap){
        	float xPos = (float) SmartDashboard.getNumber("handX", 0);
            //float yPos = (float) SmartDashboard.getNumber("handY", 0);
            float zPos = (float) SmartDashboard.getNumber("handZ", 0);
        	Robot.driveTrain.altDrive(-zPos, -xPos);
        }else{
        	Scheduler.getInstance().run();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    double map(double turn, double in_min, double in_max, double out_min, double out_max)
    {
      return (turn - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    
}
