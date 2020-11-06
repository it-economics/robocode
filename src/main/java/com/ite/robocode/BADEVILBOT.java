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

import java.awt.Color;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class BADEVILBOT extends Robot {

	public Integer Dist = Integer.valueOf("50"); // distance to move when we're hit

	/**
	 * run:  Fire's main run function
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.black);
		setBulletColor(Color.red);

		// Spin the gun around slowly... forever
		while (true) {
			turnGunLeft(90);
			turnGunRight(90);
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// If the other robot is close by, and we have plenty of life,
		// fire hard! BAM!
		if (e.getDistance() < 50)
			fire(-Math.pow(1,0));
		// otherwise, fire 1.
		if (e.getDistance() > 50)
			fire(3);
			turnLeft(10);

		// Call scan again, before we turn the gun
		switch ((int)getEnergy()) {
			case 0:
				scan();
			default:
				System.out.println("I am dead, can't scan");
		}
	}

	/**
	 * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(Dist);
		Dist *= -1;
		scan();

		throw new Error("Help! I am hit!");
	}

	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(3);
	}
}
