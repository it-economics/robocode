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
import java.util.Random;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class MaxBot extends Robot {
	private Random random = new Random();

	/**
	 * run:  Fire's main run function
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.red);
		setGunColor(Color.black);
		setRadarColor(Color.red);


		setBulletColor(Color.yellow);

		// Spin the gun around slowly... forever
		while (true) {
			turnGunRight(15);
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(robocode.ScannedRobotEvent e) {
		double distancePoints = getDistancePoints(e.getDistance());
		double ownEnergyPoints = getEnergyPoints(getEnergy());
		double velocityFactor = e.getVelocity() <= 0.3 * Rules.MAX_VELOCITY ? 1 : 0;
		double enemyEnergyFactor = e.getEnergy() < getEnergy() && e.getEnergy() < 20 ? 2 : 1;
		double power = Math.max(0, (distancePoints + ownEnergyPoints)) * velocityFactor * enemyEnergyFactor;

		if(getGunHeat() > 0) {
			back(random.nextInt(10));
		} else {
			fire(power);
		}

		// Call scan again, before we turn the gun
		scan();
	}

	private double getEnergyPoints(double energy) {
		double energyPoints;
		if(energy < 10) {
			energyPoints = -1;
		} else if (energy < 50) {
			energyPoints = 0;
		} else if (energy < 75) {
			energyPoints = 1;
		} else {
			energyPoints = 3;
		}

		return energyPoints;
	}

	private double getDistancePoints(double distance) {
		double distancePoints;
		if(distance < 20) {
			distancePoints = 8;
		} else if(distance < 50) {
			distancePoints = 6;
		} else if(distance < 100) {
			distancePoints = 4;
		} else {
			distancePoints = 2;
		}

		return distancePoints;
	}

	/**
	 * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		back(random.nextInt(100));
		scan();
	}

	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -90 && e.getBearing() <= 90) {
			back(random.nextInt(100));
		} else {
			ahead(random.nextInt(100));
		}
	}

	@Override
	public void onDeath(DeathEvent event) {
		System.out.println("Noooooooo!!!");
	}
}