/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import java.awt.Color;

import robocode.Robot;
import robocode.Rules;

public class ChrisBot2 extends Robot {

	public void run() {
		this.setAllColors(Color.WHITE);
		while (true) {
			ahead(5);
			turnLeft(5);
			scan();
		}
	}

	public void onScannedRobot(robocode.ScannedRobotEvent e) {
		if (e.getDistance() < 25) {
			fire(Rules.MAX_BULLET_POWER);
		} else {
			fire(Rules.MIN_BULLET_POWER / 2);
		}
		scan();
	}

	public void onHitByBullet(robocode.HitByBulletEvent e) {
		ahead(100); // run away
	}

	public void onHitRobot(robocode.HitRobotEvent e) {
		back(10);
		fire(Rules.MAX_BULLET_POWER);
		ahead(10);
		scan();
	}

	public void onHitWall(robocode.HitWallEvent e) {
		turnRight(e.getBearing() + 180);
		ahead(50);
	}
}
