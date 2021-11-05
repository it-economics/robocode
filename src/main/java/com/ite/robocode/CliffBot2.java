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

	int turnDirection = 1;
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
			turnRight(10 * turnDirection);
		}
	}

	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		if (e.getDistance() < 140) {
			fire(3);
		} else if (e.getDistance() < 240){
			fire(2);
		}  else if (e.getDistance() < 540){
			fire(1);
		}

		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());
		ahead(e.getDistance() + 5);
		scan(); // Might want to move ahead again!
	}

	/**
	 * onHitByBullet:  Ouch...our robot was hit by a bullet
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// let's back off
		back(30);
	}

	/**
	 * onHitRobot:  Yes, we hit a robot
	 */
	@Override
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());
		fire(1);
		ahead(40);
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		double bearing = event.getBearing(); //get the bearing of the wall
		turnRight(90 - bearing); //This isn't accurate but release your robot
	}
}
