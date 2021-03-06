/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.Robot;
import robocode.*;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Botfather1 extends Robot {
	int dist = 50; // distance to move when we're hit

	/**
	 * run:  Fire's main run function
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.white);
		setGunColor(Color.red);
		setRadarColor(Color.blue);


		setBulletColor(Color.red);

		// Spin the gun around slowly... forever
		while (true) {
			if (getOthers() < 2) {
				turnGunRight(180);
				ahead(dist);
				turnRight(40);
			} else {
				turnGunRight(90);
			}
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// If the other robot is close by, and we have plenty of life,
		// fire hard!
		if (e.getDistance() < 50 && getEnergy() > 50) {
			fire(3);
		} // otherwise, fire 1.
		else {
			fire(1);
		}
		// Call scan again, before we turn the gun
		scan();
	}

	/**
	 * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(dist);
		dist *= -1;
		scan();
	}

	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(3);
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		turnRight(80);
		ahead(10);
	}

}
