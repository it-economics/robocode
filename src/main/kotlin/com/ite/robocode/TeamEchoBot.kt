package com.ite.robocode

import java.awt.Color
import kotlin.math.abs
import robocode.BulletHitEvent
import robocode.BulletMissedEvent
import robocode.HitByBulletEvent
import robocode.HitRobotEvent
import robocode.HitWallEvent
import robocode.Robot
import robocode.ScannedRobotEvent

class TeamEchoBot : Robot() {
    var ticksSinceFight = 0 // this lock onto the target when we're in fight (per tick counts down when not hitting target)

    /**
     * run:  Fire's main run function
     */
    override fun run() {
        setBodyColor(Color.getHSBColor(0.280555556f, .97f, .36f))
        setGunColor(Color.getHSBColor(0.280555556f, .97f, .36f))
        setRadarColor(Color.getHSBColor(0.280555556f, .97f, .36f))
        while (true) {
            if (ticksSinceFight > 0) {
                ticksSinceFight--
            } else {
                turnGunRight(8.0)
            }
        }
    }

    override fun onScannedRobot(e: ScannedRobotEvent) {
        var absoluteBearing = heading + e.bearing
        if (absoluteBearing < 0) absoluteBearing = 360 - absoluteBearing
        val leadCorrection = (e.heading - absoluteBearing) * 0.02 * e.velocity
        println("heading: " + e.heading)
        println("absoluteBearing: $absoluteBearing")
        println("velocity: " + e.velocity)
        println("Correction: $leadCorrection")
        //        turnGunRight(leadCorrection);
        if (e.distance > 300) {
            fire(strengthRampup.toDouble())
            return
        }
        val headingBonus = if (e.heading == gunHeading) 10 else 0
        val rangeBonus = if (e.distance < 30) 5 else 0
        fire((2 + rangeBonus + headingBonus + strengthRampup).toDouble())
        scan()
    }

    var strengthRampup = 1
    override fun onBulletHit(e: BulletHitEvent) {
        ticksSinceFight = 10
        strengthRampup *= 1.1.toInt()
        if (e.energy > 10) {
            val bulletHeading = e.bullet.heading
            val diff = heading - bulletHeading
            if (diff < 0 && diff > -180) {
                turnRight(abs(diff) + 10)
            } else {
                turnLeft(abs(diff) + 10)
            }
            ahead(20.0)
        }
        scan()
    }

    override fun onHitByBullet(e: HitByBulletEvent) {
        strengthRampup = 1
        turnRight(e.bearing + 90)
        ahead(200.0)
        scan()
    }

    override fun onHitRobot(e: HitRobotEvent) {
        scan()
    }

    override fun onBulletMissed(e: BulletMissedEvent) {
        strengthRampup = 1
    }

    override fun onHitWall(e: HitWallEvent) {
        turnRight(e.bearing + 150)
        ahead(100.0)
        scan()
    }
}
