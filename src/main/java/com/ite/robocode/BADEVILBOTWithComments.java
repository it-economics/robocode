/**
 * Copyright (c) 2001-2019 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package com.ite.robocode;

import static robocode.util.Utils.normalRelativeAngleDegrees;

// BAD: Class name should not in capital letters, should be camel case instead
public class BADEVILBOTWithComments extends robocode.Robot {

	// BAD: left side of of statement may be List<> and should have a type.
	// BAD: use private instead of protected
	// also: import
	protected static java.util.ArrayList<?> listofreports = new java.util.ArrayList<>();
	// BAD: access should not be public.
	// Solution: Use getter/setter because of encapsulation (OO)
	// BAD: unnecessary parsing
	// BAD: unnecessary use of boxed Integer
	// BAD: variables should start with lower case letter
	public Integer Dist = Integer.valueOf("50"); // distance to move when we're hit
	public boolean hitByBullet = false;

	/**
	 * run:  Bot's main run function. BAM!
	 */
	public void run() {

		// Spin the gun around slowly... forever
		// BAD: Should run forever ->
		// Solution: while (true) {
		while (true && hitByBullet) {
			if (Dist < 60.0) {
				// No reason to turn the gun
				// But even if there is a reason, it is back in the starting position
				turnGunLeft(90);
				turnGunRight(90);
				// BAD: This leaves room for interpretation. So far the robot only moves forward
				// Solution: Either implement a better strategy as default or
				// implement onHitWall from the parent class
				// BAD: use class variable instead of number
				ahead(50);
			}

			// Set colors
			// doesn't need to be in the loop
			setBodyColor(java.awt.Color.black);
			setGunColor(java.awt.Color.black);
			setRadarColor(java.awt.Color.black);
		}
	}

	// BAD: Use Integer.compare
	// Solution: return Integer.compare(Dist, i)
	// BAD: Objects can't be compared with ==
	// Solution: use equals()
	// BAD: method names does not represent what it is doing
	public int bulletColor(Integer i) {
		if (Dist < i) return -1;
		if (Dist== i) return 0;
		return 1;
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	@Override
	public void onScannedRobot(robocode.ScannedRobotEvent e) {
		// If the other robot is close by fire hard! BAM!
		// BAD: If clause without brackets
		if (e.getDistance() < 50)
			// BAD: Should fire but set the Value to a negative Value
			// Solution: fire(3);
			fire(-Math.pow(1,0));

		// otherwise, fire 1.
		// BAD: Will always turn Left because of missing parenthesis
		// Solution: if () {fire(); turnLeft()}
		if (e.getDistance() > 50)
			fire(3);
			turnLeft(10);

		// Call scan again, before we turn the gun
		// BAD: unnecessary switch case and cast
		// Solution: use if-clause
		switch ((int)getEnergy()) {
			// BAD: Only scans when the robot has no energy (is dead)
			// Solution: remove switch/if or change zero to a higher number
			case 0:
				scan();
				// BAD: missing break so default is always called
				// Solution: break;
			default:
				System.out.println("I am dead, can't scan");
		}
	}

	/**
	 * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
	 */
	@Override
	public void onHitByBullet(robocode.HitByBulletEvent e) {
		//turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
		// BAD: hitByBullet is unnecessary (Bonus)
		hitByBullet = true;
		ahead(Dist);
		scan();

		// add damage report
		// list adds itself in a new list
		// BAD: List has no members and clone itself
		// Solution: remove this line or add something to the list
		listofreports = new java.util.ArrayList<>(listofreports);

		// BAD: This leads to robot doing nothing after being hit
//		throw new Error("Help! I am hit!");
	}

	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(robocode.HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(3);
		//BAD: byte, cast, unnecessary stream and stream operations
		byte number = (byte) listofreports.stream().map(Object::toString).count();
		//BAD: use logger, string concat is ineffective, user String formatter or builder
		System.out.println("YOu have been hit " + number + " times so far");
	}

}
