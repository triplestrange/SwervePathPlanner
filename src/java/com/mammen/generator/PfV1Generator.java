package com.mammen.generator;

import com.mammen.generator.generator_vars.SharedGeneratorVars;
import com.mammen.generator.generator_vars.DriveBase;
import com.mammen.generator.generator_vars.PfV1GeneratorVars;
import com.mammen.path.Path;
import com.mammen.path.SwerveWaypoint;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
//import org.scijava.nativelib.NativeLoader;

import java.util.List;

public class PfV1Generator implements Generator {
    public PfV1Generator() {
    }

    private static SwerveWaypoint[] wp2pf(List<SwerveWaypoint> wpList) {
        SwerveWaypoint[] wpArray = new SwerveWaypoint[wpList.size()];

        for (int i = 0; i < wpList.size(); i++) {
            double x = wpList.get(i).getX();
            double y = wpList.get(i).getY();
            double angle = Pathfinder.d2r(wpList.get(i).getAngle());
            double rotation = Pathfinder.d2r(wpList.get(i).getRotation());

            wpArray[i] = new SwerveWaypoint(x, y, angle, rotation);
        }

        return wpArray;
    }

    private static Path.Segment[] traj2Path(Trajectory fl) {
        if (fl == null)
            return null;

        Path.Segment[] segments = new Path.Segment[fl.length()];

        for (int i = 0; i < fl.length(); i++) {
            segments[i] = new Path.Segment(fl.segments[i].dt, fl.segments[i].x, fl.segments[i].y,
                    fl.segments[i].position, fl.segments[i].velocity, fl.segments[i].acceleration, fl.segments[i].jerk,
                    fl.segments[i].heading);
        }

        return segments;
    }

    @Override
    public Path generate(List<SwerveWaypoint> waypointList) throws PathGenerationException, NotEnoughPointsException {
        PfV1GeneratorVars vars = PfV1GeneratorVars.getInstance();
        SharedGeneratorVars sharedVars = SharedGeneratorVars.getInstance();
        SwerveTrajectory source, fr, fl, br, bl;

        // We need at least 2 points to generate a trajectory.
        if (waypointList.size() > 1) {
            SwerveTrajectory.Config config = new SwerveTrajectory.Config(vars.getFitMethod().pfFitMethod(),
                    Trajectory.Config.SAMPLES_HIGH, sharedVars.getTimeStep(), vars.getVelocity(), vars.getAccel(),
                    vars.getJerk());

            try {
                source = SwerveTrajectory.generate(config, wp2pf(waypointList), 180.0, 180.0);
            } catch (Exception e) {
                throw new PathGenerationException("Pathfinder V1 failed to generate the path.");
            }

            if (sharedVars.getDriveBase() == DriveBase.SWERVE) {
                // SwerveModifier swerve = new SwerveModifier(source);
                // swerve.modify(sharedVars.getWheelBaseW(), sharedVars.getWheelBaseD(),
                // SwerveModifier.Mode.SWERVE_DEFAULT);

                // fr = swerve.getFrontRightTrajectory();
                // fl = swerve.getFrontLeftTrajectory();
                // br = swerve.getBackRightTrajectory();
                // bl = swerve.getBackLeftTrajectory();
            } else // DriveBase.Tank
            {
                // TankModifier tank = new TankModifier(source);
                // tank.modify(sharedVars.getWheelBaseW());

                // fr = tank.getRightTrajectory();
                // fl = tank.getLeftTrajectory();
                // br = null;
                // bl = null;
            }

            return new Path(sharedVars.getDriveBase(), traj2Path(source));
        }

        throw new NotEnoughPointsException("There are not enough points to generate a Path.");
    }

}
