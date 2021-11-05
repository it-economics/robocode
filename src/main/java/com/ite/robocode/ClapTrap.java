/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

// import java.util.Random;

import java.awt.*;

// IMPORTANT!!!
// DO NOT CHANGE THIS CLASS ==> CREATE YOUR OWN ROBOT, you can copy this class for starters
public class ClapTrap extends Robot {

	/**
	 * run:  Fire's main run function
	 */

	private boolean test = false;
	@Override
	public void run() {
		// Set colors
		if(test){
			setBodyColor(Color.yellow);
			setGunColor(Color.black);
			setRadarColor(Color.red);
			test = !test;
		}else{
			/*Random rand = new Random();
			// Java 'Color' class takes 3 floats, from 0 to 1.
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();

			Color randomColor = new Color(r, g, b);

			setBodyColor(randomColor);
			setGunColor(randomColor);
			setRadarColor(randomColor); */

			setBodyColor(Color.black);
			setGunColor(Color.yellow);
			setRadarColor(Color.red);
		}

		// This will be the default behaviour of your robot
		while (true) {
			betterTurnGunRight(10);
			ahead(1);
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
	public void onScannedRobot(ScannedRobotEvent e) {
		// do something
		fire(1);
	}

	/**
	 * onHitByBullet:  Ouch...our robot was hit by a bullet
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		betterTurnGunLeft(10);
		ahead(10);
	}
	/*
	@Override
	public void onHitWall(){
		turnRadarLeft(90);
		ahead(40);
	}*/

	/**
	 * onHitRobot:  Yes, we hit a robot
	 */
	@Override
	public void onHitRobot(HitRobotEvent e) {
		// fire again
		fire(3);
	}
}
