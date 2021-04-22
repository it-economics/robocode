package com.ite.robocode;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;


public class AenisBot extends Robot{
<<<<<<< HEAD
    int dist = 20; // distance to move when we're hit
    public void run() {
        // Set colors
        setBodyColor(Color.green);
=======
    int dist = 400; // distance to move when we're hit
    public void run() {
        // Set colors
        setBodyColor(Color.black);
>>>>>>> a631623f668a6861556a48ae65843e33281bb9a2
        setGunColor(Color.black);
        setRadarColor(Color.black);


        setBulletColor(Color.red);

        // Spin the gun around slowly... forever
        while (true) {
            turnGunRight(10);

        }
    }

    /**
     * onScannedRobot:  Fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // If the other robot is close by, and we have plenty of life,
        // fire hard!
        if (e.getDistance() < 100 && getEnergy() > 50) {
<<<<<<< HEAD
            fire(30);
        } // otherwise, fire 1.
        else {
            fire(10);
            ahead(400);
            turnRight(40);
=======
            fire(25);
        } // otherwise, fire 1.
        else {
            fire(10);
>>>>>>> a631623f668a6861556a48ae65843e33281bb9a2
        }
        // Call scan again, before we turn the gun
        scan();
    }

    /**
     * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
     */
    public void onHitByBullet(HitByBulletEvent e) {
<<<<<<< HEAD
        turnRight(normalRelativeAngleDegrees((90 - getHeading() - e.getHeading())));
=======
        turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
>>>>>>> a631623f668a6861556a48ae65843e33281bb9a2

        ahead(dist);
        dist *= -1;
        scan();
    }

    /**
     * onHitRobot:  Aim at it.  Fire Hard!
     */
    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

        turnGunRight(turnGunAmt);
        fire(3);
    }
}
