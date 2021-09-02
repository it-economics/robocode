/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import robocode.Robot;
import robocode.Rules;

public class ChrisBot2 extends Robot {

	/**
	 * run:  Fire's main run function
	 */
	public void run() {
		while (true) {
			ahead(5);
			turnRight(5);
		}
	}

	/**
	 * onScannedRobot:
	 */
	public void onScannedRobot(robocode.ScannedRobotEvent e) {
		if (e.getDistance() < 20) {
			fire(Rules.MAX_BULLET_POWER);
		} else {
			fire(Rules.MIN_BULLET_POWER / 2);
		}
		scan();
	}

	/**
	 * onHitByBullet:
	 */
	public void onHitByBullet(robocode.HitByBulletEvent e) {
		ahead(100); // run away
		scan();
	}

	/**
	 * onHitRobot:
	 */
	public void onHitRobot(robocode.HitRobotEvent e) {

		scan();
	}


}
