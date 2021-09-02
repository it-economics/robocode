package com.ite.robocode;

import robocode.HitWallEvent;
import robocode.Robot;

import java.awt.*;

public class Ramrod extends Robot {

    private boolean turn = true;

    /**
     * run:  Fire's main run function
     */
    public void run() {
        setBodyColor(Color.BLUE);
        setGunColor(Color.WHITE);

        while (true) {
//            ahead(10);
            if (turn) {
//                turnRight(5);
                turnGunRight(10);
            }
        }
    }

    /**
     * onScannedRobot:
     */
    @Override
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        if (e.getDistance() < 300) {
            turnRight(e.getBearing());
            ahead(e.getDistance() + 30);
            turnGunRight(e.getBearing());
            fireBullet(3);
            turn = true;
        } else {
            ahead(20);
        }
    }

    /**
     * onHitByBullet:
     */
    @Override
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        turnRight(90);
        ahead(50);
        turn = true;
    }

    /**
     * onHitRobot:
     */
    @Override
    public void onHitRobot(robocode.HitRobotEvent e) {
        back(50);
        turnRight(90);
        turn = true;
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        turnRight(90);
    }

}
