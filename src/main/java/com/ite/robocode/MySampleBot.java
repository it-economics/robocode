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

import java.awt.*;

// IMPORTANT!!!
// DO NOT CHANGE THIS CLASS ==> CREATE YOUR OWN ROBOT, you can copy this class for starters
public class MySampleBot extends Robot {

	/**
	 * run:  Fire's main run function
	 */
	@Override
	public void run() {
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.black);

		// This will be the default behaviour of your robot
		while (true) {
			turnGunRight(10);
			ahead(20);
			turnLeft(15);
		}
	}

	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		// do something here
	}

	/**
	 * onHitByBullet:  Ouch...our robot was hit by a bullet
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// let's back off
		back(10);
	}

	/**
	 * onHitRobot:  Yes, we hit a robot
	 */
	@Override
	public void onHitRobot(HitRobotEvent e) {
		// fire again
		fire(3);
	}
}
