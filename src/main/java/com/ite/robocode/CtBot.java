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
            ahead(40);
            turnGunLeft(30);
            scan();
        }
    }

    /**
     * onScannedRobot: if our robot sees another robot
     */
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getDistance() < 100) {
            fire(Rules.MAX_BULLET_POWER);
        } else if (e.getDistance() < 400) {
            fire(2);
        }
    }

    /**
     * onHitByBullet:  Ouch...our robot was hit by a bullet
     */
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // run....
        switchColor();
        double bearing = e.getBearing();
        turnRight(-bearing + 10);
        ahead(30); 
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        back(50);
        turnLeft(45);
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
