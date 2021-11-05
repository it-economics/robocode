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
import java.awt.geom.Point2D;
import java.util.Objects;
import java.util.Random;

public class MMMMMMMMM extends Robot {

	/**
	 * run:  Fire's main run function
	 */

	private Point2D headed_corner;
	private String mode = "turning";

	@Override
	public void run() {
		// Set colors
		setBodyColor(Color.pink);
		setGunColor(Color.pink);
		setRadarColor(Color.pink);
		setScanColor(Color.pink);
		setRadarColor(Color.YELLOW);


		// choose random corner
		Random random = new Random();
		headed_corner = new Point2D.Double(
				random.nextDouble()*this.getBattleFieldWidth(),
				random.nextDouble()*this.getBattleFieldHeight()
		);


		double heading = getHeading();
		turnLeft(heading);
		//turnRadarLeft(90);
		//turnGunLeft(90);
		ahead(this.getBattleFieldHeight()-getY());

		// This will be the default behaviour of your robot
		while (true) {
			// left upper corner
			ahead(20);
			turnGunLeft(180);
			scan();
			ahead(20);
			turnGunRight(180);
		}
	}


	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		// do something here
		//e.get
		//turnGunLeft(getGunHeading() - event.getBearing());
		//event.getHeading()

		if(event.getDistance() >= 100) {
			fire(3);
		} else if(event.getDistance() < 100) {
			fire(20);
		} else if(event.getDistance() < 60) {
			fire(50);
		}
		scan();
	}

	/**
	 * onHitByBullet:  Ouch...our robot was hit by a bullet
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// let's back off
		//back(10);
	}

	/**
	 * onHitRobot:  Yes, we hit a robot
	 */
	@Override
	public void onHitRobot(HitRobotEvent e) {
		// fire again
		back(20);
		//fire(3);
	}


	@Override
	public void onHitWall(HitWallEvent event) {
		turnLeft(90);
		//turnRadarLeft(90);
	}
}
