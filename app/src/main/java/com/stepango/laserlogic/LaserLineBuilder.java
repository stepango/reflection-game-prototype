package com.stepango.laserlogic;

import android.graphics.Point;

public class LaserLineBuilder {

    private static final int MAX_STEPS = 200;

    public void buildPath(LaserLine laserLine, GameObject[][] map) {
        laserLine.clearPoints();
        int step = 0;
        int angle = laserLine.getAngle();
        Point position = new Point(laserLine.getStartPosition());
        GameObject gameObject;
        while (step < MAX_STEPS) {
            step++;
            nextPosition(position, angle);
            if (GameObjectsMap.outOfArea(position)) {
                laserLine.addPoint(position);
                return;
            }
            gameObject = map[position.x][position.y];
            if (gameObject != null) {
                laserLine.addPoint(position);
                int reflection = gameObject.onLaser(angle);
                if (reflection < 0) {
                    return;
                } else {
                    angle = reflection;
                }
            }
        }
        laserLine.addPoint(position);
    }

    private void nextPosition(Point position, final int angle) {
        switch (angle) {
            case DynamicGameObject.DEG_0:
                position.x++;
                break;
            case DynamicGameObject.DEG_90:
                position.y++;
                break;
            case DynamicGameObject.DEG_180:
                position.x--;
                break;
            case DynamicGameObject.DEG_270:
                position.y--;
                break;
            case DynamicGameObject.DEG_45:
                position.x++;
                position.y++;
                break;
            case DynamicGameObject.DEG_135:
                position.x--;
                position.y++;
                break;
            case DynamicGameObject.DEG_225:
                position.x--;
                position.y--;
                break;
            case DynamicGameObject.DEG_315:
                position.x++;
                position.y--;
                break;
            default:
                break;
        }

    }

}
