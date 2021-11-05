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

public class BomaxBot extends Robot {
	private final Random random = new Random();

	/**
	  Mit der Hauptmethode run() wird das Standardverhalten des Roboters definiert, das unabhängig von aufgetretenen Ereignissen ist.
	  Die Methode wird genau einmal beim Rundenstart aufgerufen. Die Aktionen des Roboters sollten hier innerhalb einer Endlosschleife definiert werden.
	  Die Befehle werden dabei sequentiell und nicht parallel abgearbeitet. Im Code-Beispiel wird die Farbe des Roboters auf grün gesetzt.
	  Danach fährt der Roboter zuerst 20 Pixel nach vorne, dreht sich dann um 45 Grad
	  nach links, scannt die Umgebung nach anderen Robotern und feuert anschließend mit einer Stärke von 1. Dieser Vorgang wiederholt sich zum Spielende.

	  In den nächsten Posts gehen wir auf verschiedene Ereignisse ein, auf die ein Roboter reagieren kann.
	 */
	@Override
	public void run() {
		setBodyColor(Color.GREEN);
		setBulletColor(Color.RED);
		setGunColor(Color.BLUE);
		setRadarColor(Color.YELLOW);
		while (getEnergy() > 0) {
			ahead(Rules.MAX_VELOCITY);
			turnLeft(Rules.MAX_TURN_RATE);
			turnRadarLeft(10);
			scan();
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		turnGunLeft(getGunHeading() - event.getBearing());
		if(event.getDistance() >= 20) {
			fire(1);
		} else if(event.getDistance() < 20) {
			fire(2);
		} else if(event.getDistance() < 10) {
			fire(3);
		}
		scan();
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		fire(event.getBullet().getPower());
		ahead(Rules.MAX_VELOCITY);
		scan();
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		turnLeft(90 - event.getBearing());
		back(Rules.MAX_VELOCITY);
		scan();
	}

	@Override
	public void onBulletMissed(BulletMissedEvent event) {
		if(random.nextBoolean()) {
			turnGunRight(Rules.MAX_TURN_RATE);
		} else {
			turnGunLeft(Rules.MAX_TURN_RATE);
		}
		scan();
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		// event.getBearing() == 0 means enemy straight ahead
		if(event.getBearing() < -90 || event.getBearing() > 90) {
			back(Rules.MAX_VELOCITY);
		} else {
			ahead(Rules.MAX_VELOCITY);
		}
		scan();
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		if(event.getBearing() < -90 || event.getBearing() > 90) {
			turnLeft(90 - event.getBearing());
			back(Rules.MAX_VELOCITY);
		} else {
			turnLeft(90 + event.getBearing());
			ahead(Rules.MAX_VELOCITY);
		}
		scan();
	}


}
