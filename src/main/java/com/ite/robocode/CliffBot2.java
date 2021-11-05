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

import java.awt.*;

// IMPORTANT!!!
// CL4P-TP is best Trap
public class CliffBot2 extends Robot {

	/**
	 * run:  Fire's main run function
	 */
	@Override
	public void run() {
		// Set colors
		setBodyColor(Color.blue);
		setGunColor(Color.white);
		setRadarColor(Color.black);

		// This will be the default behaviour of your robot
		while (true) {
			turnGunRight(30);
			ahead(30);
		}
	}

	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
		scan();
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

	@Override
	public void onHitWall(HitWallEvent event) {
		double bearing = event.getBearing(); //get the bearing of the wall
		turnRight(90 - bearing); //This isn't accurate but release your robot
	}
}
