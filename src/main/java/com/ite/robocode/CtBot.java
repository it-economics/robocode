package com.ite.robocode;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.Rules;
import robocode.ScannedRobotEvent;

import java.awt.*;
import java.util.Random;

public class CtBot extends Robot {

	/**
	 * run:  Fire's main run function
	 */
	@Override
	public void run() {
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.black);

		// This will be the default behaviour of your robot
		while (true) {
			turnGunRight(10);
			ahead(40);
			//turnLeft(15);
		}
	}

	/**
	 * onScannedRobot: if our robot sees another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		if(e.getDistance() < 200) {
			fire(Rules.MAX_BULLET_POWER);
		} else if(e.getDistance() < 300) {
			fire(Rules.MIN_BULLET_POWER);
		}
	}

	/**
	 * onHitByBullet:  Ouch...our robot was hit by a bullet
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// run....
		switchColor();
		double bearing = e.getBearing(); //Get the direction which is arrived the bullet.
		turnRight(-bearing); //This isn't accurate but release your robot.
		ahead(100); //The robot goes away from the enemy.
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		back(100);
		turnLeft(30);
	}

	/**
	 * onHitRobot:  Yes, we hit a robot
	 */
	@Override
	public void onHitRobot(HitRobotEvent e) {
		// fire again
		fire(3);
	}

	private void switchColor() {
		Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN};
		Color c = colors[new Random().nextInt(3) + 1];
		System.out.println("CtBot: set color to " + c);
		setBodyColor(c);
		setGunColor(c);
		setRadarColor(c);
	}
}
