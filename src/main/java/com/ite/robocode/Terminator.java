/**
/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.Bullet;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class Terminator extends Robot {

	/**
	 * run: Fire's main run function
	 */
	@Override
	public void run() {
		// Set colors
		setBodyColor(Color.pink);
		setGunColor(Color.pink);
		setRadarColor(Color.pink);

		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);

		// This will be the default behaviour of your robot
		while (true) {
			this.turnRadarLeft(15);
			ahead(5);
			turnLeft(3);
			scan();
		}
	}

	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		this.turnGunLeft(this.getGunHeading() - e.getBearing());
		this.fire(5);
	}

	/**
	 * onHitByBullet: Ouch...our robot was hit by a bullet
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// let's back off
		System.out.println("Screw you");
		this.turnGunLeft(this.getGunHeading() - e.getBearing());
		this.fire(1);
	}

	/**
	 * onHitRobot: Yes, we hit a robot
	 */
	@Override
	public void onHitRobot(HitRobotEvent e) {
		// fire again
		this.turnGunLeft(this.getGunHeading() - e.getBearing());
		this.fire(5);
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		double deg = event.getBearing();
		turnLeft(deg * 2);
	}

	@Override
	public void fire(double power) {
		if (this.getEnergy() > 10) {
			super.fire(power);
		}
	}

	@Override
	public Bullet fireBullet(double power) {
		if (this.getEnergy() > power) {
			return super.fireBullet(power);
		} else {
			return null;
		}
	}

	@Override
	public void turnLeft(double degrees) {
		this.turnGunLeft(degrees);
		super.turnLeft(degrees);
	}
}
