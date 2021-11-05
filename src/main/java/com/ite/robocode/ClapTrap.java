/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.*;
import robocode.Robot;

import java.util.Random;

import java.awt.*;

enum BotMode {
	DEFAULT,
	SIMPLEBOT,
};
// IMPORTANT!!!
// DO NOT CHANGE THIS CLASS ==> CREATE YOUR OWN ROBOT, you can copy this class for starters
public class ClapTrap extends Robot {

	/**
	 * run:  Fire's main run function
	 */

	private boolean test = false;
	private int runMode = 0;
	private Random rand = new Random();

	private BotMode botMode = BotMode.SIMPLEBOT;

	public void claptrapRun1(){
			ahead(100); //Go ahead 100 pixels
			turnGunRight(360); //scan
			back(75); //Go back 75 pixels
			turnGunRight(360); //scan
	}
	public void claptrapRun2(){
		ahead(100); //Go ahead 100 pixels
		turnGunLeft(360); //scan
		back(90); //Go back 75 pixels
		turnGunLeft(360); //scan
	}
	public void simplebotRun(){
		ahead(100);
	}

	@Override
	public void run() {
		// Set colors
		setBodyColor(Color.yellow);
		setGunColor(Color.black);
		setRadarColor(Color.red);

		// This will be the default behaviour of your robot
		while(true){
			if(getTime() > 9999){
				botMode = BotMode.DEFAULT;
			}
			if(botMode == BotMode.DEFAULT){
				if(runMode % 2 == 0){
					claptrapRun1();
				}else{
					claptrapRun2();
				}
				runMode++;
			}else if(botMode == BotMode.SIMPLEBOT){
				simplebotRun();
			}

		}
	}

	public void betterTurnGunRight (double degree){
		turnGunRight(degree);
		turnRadarLeft(degree);
	}

	public void betterTurnGunLeft (double degree){
		turnGunLeft(degree);
		turnRadarRight(degree);
	}

	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e){
		double distance = e.getDistance(); //get the distance of the scanned robot
		if(distance > 800) //this conditions adjust the fire force according the distance of the scanned robot.
			fire(5);
		else if(distance > 600 && distance <= 800)
			fire(4);
		else if(distance > 400 && distance <= 600)
			fire(3);
		else if(distance > 200 && distance <= 400)
			fire(2);
		else if(distance < 200)
			fire(1);
		scan();
	}

	/**
	 * onHitByBullet:  Ouch...our robot was hit by a bullet
	 */

	private int onHitMode = 0;
	@Override
	public void onHitByBullet(HitByBulletEvent e){
		if(botMode == BotMode.DEFAULT){
			double energy = getEnergy();
			onHitMode++;
			if(onHitMode % 2 == 0){
				ahead(100);
				scan();
			}else{
				double bearing = e.getBearing(); //Get the direction which is arrived the bullet.
				if(energy < 100){ // if the energy is low, the robot go away from the enemy
					turnRight(-bearing); //This isn't accurate but release your robot.
					ahead(100); //The robot goes away from the enemy.
				}
				else
					turnRight(360); // scan
			}
		}else{
			double bearing = e.getBearing(); //get the bearing of the wall
			turnRight(-bearing); //This isn't accurate but release your robot.
			ahead(100); //The robot goes away from the wall.
		}

	}

	@Override
	public void onHitWall(HitWallEvent e){
		double bearing = e.getBearing(); //get the bearing of the wall
		turnRight(-bearing); //This isn't accurate but release your robot.
		ahead(100); //The robot goes away from the wall.
		// scan();
	}

	/**
	 * onHitRobot:  Yes, we hit a robot
	 */
	@Override
	public void onHitRobot(HitRobotEvent e) {
		if(botMode == BotMode.SIMPLEBOT){
			double bearing = e.getBearing(); //get the bearing of the wall
			turnRight(-bearing); //This isn't accurate but release your robot.
			ahead(100); //The robot goes away from the wall.
		}else{
			fire(3);
		}

	}



}
