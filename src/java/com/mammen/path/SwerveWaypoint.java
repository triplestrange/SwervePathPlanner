package com.mammen.path;

// import com.mammen.path.Waypoint;
import jaci.pathfinder.Waypoint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
// import javafx.beans.Observable;

public class SwerveWaypoint extends Waypoint {
    private DoubleProperty x;
    private DoubleProperty y;
    private DoubleProperty angle;
    private DoubleProperty rotation;

    public SwerveWaypoint(double x, double y, double angle, double rotation) {
        super(x, y, angle);
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.angle = new SimpleDoubleProperty(angle);
        this.rotation = new SimpleDoubleProperty(rotation);
    }

    public double getX() {
        return x.get();
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public double getAngle() {
        return angle.get();
    }

    public void setAngle(double angle) {
        this.angle.set(angle);
    }

    public DoubleProperty angleProperty() {
        return angle;
    }

    public double getRotation() {
        return rotation.get();
    }

    public void setRotation(double rotation) {
        this.rotation.set(rotation);
    }

    public DoubleProperty rotationProperty() {
        return rotation;
    }

}
