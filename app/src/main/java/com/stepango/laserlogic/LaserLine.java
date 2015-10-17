package com.stepango.laserlogic;

import android.graphics.Point;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.primitive.Line;

import java.util.LinkedList;

import roboguice.util.Ln;

public class LaserLine extends Entity {

    private static final float LINE_WIDTH = 3f;

    private static LaserLineBuilder sBuilder = new LaserLineBuilder();
    private final Point mStartPosition;
    LinkedList<Point> mPoints;
    private int mAngle;

    public LaserLine(Point startPosition, int angle) {
        init();
        mStartPosition = startPosition;
        mPoints.add(mStartPosition);
        mAngle = angle;
    }

    public void init() {
        mPoints = new LinkedList<>();
    }

    public void clearPoints() {
        mPoints.clear();
        mPoints.add(mStartPosition);
    }

    public void addPoint(final int cellNumX, final int cellNumY) {
        mPoints.add(new Point(cellNumX, cellNumY));
    }

    public void addPoint(Point point) {
        mPoints.add(new Point(point));
    }

    public void buildLines() {
        detachChildren();
        Point previousPoint = null;
        for (Point point : mPoints) {
            Ln.i(point.x + " " + point.y);
            if (previousPoint != null) {
                attachChild(makeLine(previousPoint, point));
            }
            previousPoint = point;
        }
        Ln.i("-------------");
    }

    public final Line makeLine(final Point start, final Point end) {
        final Line line = new Line(
                (start.x + 0.5f) * GameObjectsMap.CELL_SIZE_X,
                (start.y + 0.5f) * GameObjectsMap.CELL_SIZE_Y,
                (end.x + 0.5f) * GameObjectsMap.CELL_SIZE_X,
                (end.y + 0.5f) * GameObjectsMap.CELL_SIZE_Y);
        line.setLineWidth(LINE_WIDTH);
        line.setColor(1f, 0.1f, 0.1f, 0.75f);
        return line;
    }

    public Point getStartPosition() {
        return mStartPosition;
    }

    public int getAngle() {
        return mAngle;
    }

    public void setAngle(int angle) {
        mAngle = angle;
    }

    public void build(GameObject[][] map) {
        sBuilder.buildPath(this, map);
        buildLines();
    }
}
