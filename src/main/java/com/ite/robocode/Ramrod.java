package com.ite.robocode;

import robocode.HitWallEvent;
import robocode.Robot;

public class Ramrod extends Robot {

    boolean turn = true;

    /**
     * run:  Fire's main run function
     */
    public void run() {
        fire(1);
        while (true) {
            ahead(5);
            if (turn) {
                turnRight(5);
            }
        }
    }

    /**
     * onScannedRobot:
     */
    public void onScannedRobot(robocode.ScannedRobotEvent e) {
        ahead(e.getDistance());
        fireBullet(2);
        turn = false;
    }

    /**
     * onHitByBullet:
     */
    public void onHitByBullet(robocode.HitByBulletEvent e) {
        turnRight(90);
        turn = true;
        scan();
    }

    /**
     * onHitRobot:
     */
    public void onHitRobot(robocode.HitRobotEvent e) {
        turnRight(180);
        turn = true;
        scan();
    }

    public void onHitWall(HitWallEvent event) {
        turnRight(130);
    }

}
