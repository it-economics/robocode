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


		// choose random corner
		Random random = new Random();
		headed_corner = new Point2D.Double(
				random.nextDouble()*this.getBattleFieldWidth(),
				random.nextDouble()*this.getBattleFieldHeight()
		);


		double heading = getHeading();
		turnLeft(heading);
		ahead(this.getBattleFieldHeight()-getY());
		turnLeft(90);
		turnGunLeft(90);





		// This will be the default behaviour of your robot
		while (true) {
			// left upper corner
			int steps = 20;
			double total_distnace = this.getBattleFieldWidth()-this.getX() + 10;
			for (int i = 0; i <= steps; i++){
				ahead(total_distnace/steps);
				fire(1);
			}
			turnLeft(180);
			turnGunLeft(180);
			total_distnace = this.getBattleFieldWidth() - 10;
			for (int i = 0; i <= steps; i++){
				ahead(total_distnace/steps);
				fire(1);
			}
		}
	}


	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		// do something here
		//e.get
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
