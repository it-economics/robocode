package com.ite.otd;

import robocode.*;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * MyRobot - a robot by Christian & Cliff
 */
public class IteFighter extends AdvancedRobot
{
	private String trackName = null;

	/**
	 * run: MyRobot's default behavior
	 */
	@Override
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		 setColors(Color.green,Color.blue,Color.lightGray,Color.red,Color.CYAN); // body,gun,radar,bullet,scan arc

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			setTurnRight(90);
			ahead(100);
			turnGunRight(360);
			setMaxVelocity(5);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		if (trackName == null) {
			circleTactics(e);
		} else {
			trackerTactics(e);
		}
	}

	private void trackerTactics(ScannedRobotEvent e) {
		// If we have a target, and this isn't it, return immediately
		// so we can get more ScannedRobotEvents.
		if (trackName != null && !e.getName().equals(trackName)) {
			return;
		}

		// If we don't have a target, well, now we do!
		if (trackName == null) {
			trackName = e.getName();
			out.println("Tracking " + trackName);
		}
		// This is our target.  Reset count (see the run method)
		// If our target is too far away, turn and move toward it.
		if (e.getDistance() > 150) {

			setTurnGunRight(normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading())));
			turnRight(e.getBearing()); // and see how much Tracker improves...
			fire(.5);
			// (you'll have to make Tracker an AdvancedRobot)
			ahead(e.getDistance() - 140);
			return;
		}

		// Our target is close.
		turnGunRight(normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading())));
		fire(3);

		// Our target is too close!  Back up.
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
		scan();
	}

	private void circleTactics(ScannedRobotEvent e) {
		if (e.getDistance() > 100) {
			fire(Rules.MAX_BULLET_POWER);
		} else if (e.getDistance() > 200) {
			fire(Rules.MAX_BULLET_POWER / 2);
		} else {
			fire(1);
		}
	}

	@Override
	public void onRobotDeath(RobotDeathEvent event) {
		if (event.getName().equals(trackName)) {
			System.out.println("Resetting tracker from " + trackName);
			trackName = null;
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		setTurnRight(e.getBearing() +90);
		back(20);
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		event.getBullet();
//		if (event.getEnergy() > 0) {
//			turnGunRight(normalRelativeAngleDegrees(this.bearing + (getHeading() - getRadarHeading())));
//			fire(1);
//		}
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnLeft(Math.random() * 90);
		back(40);
	}


}
