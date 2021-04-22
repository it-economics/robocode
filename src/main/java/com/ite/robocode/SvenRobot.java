/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.HitWallEvent;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class SvenRobot extends robocode.Robot {
	int dist = 50; // distance to move when we're hit

	/**
	 * run:  Fire's main run function
	 */
	public void run() {
		// Set colors
		setBodyColor(java.awt.Color.YELLOW);
		setGunColor(java.awt.Color.BLACK);
		setRadarColor(java.awt.Color.BLACK);
		setScanColor(java.awt.Color.BLUE);
		setBulletColor(java.awt.Color.GRAY);

		// Spin the gun around slowly... forever
		while (true) {
			ahead(50);
			turnGunRight(360);
		}
	}

	public void onHitWall(HitWallEvent event) {
		turnRight(90);
		ahead(90);
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(robocode.ScannedRobotEvent e) {
		// If the other robot is close by, and we have plenty of life,
		// fire hard!
		if (e.getDistance() < 150) {
			fire(3);
		} // otherwise, fire 1.
		else {
			fire(2);
		}
		// Call scan again, before we turn the gun
		scan();
	}

	/**
	 * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
	 */
	public void onHitByBullet(robocode.HitByBulletEvent e) {
		turnRight(e.getBearingRadians() + getHeading() - getGunHeading());
		ahead(100);
		scan();
	}

	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(robocode.HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(2);
		scan();
	}
}
