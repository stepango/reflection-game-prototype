package com.stepango.laserlogic;

import android.graphics.Point;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class LaserGun extends DynamicGameObject {

    private LaserLine mLaserLine;

    public LaserGun(final int posX, final int posY, final int angle,
                    final TextureRegion region) {
        super(posX, posY, angle, region);
        mLaserLine = new LaserLine(new Point(posX, posY), angle);
    }

    @Override
    int onLaser(int angle) {
        return -1;
    }

    @Override
    void attachTo(Scene scene) {
        scene.getChild(GameObjectsMap.GAME_OBJECTS_LAYER).attachChild(getSprite());
        scene.getChild(GameObjectsMap.LASER_LAYER).attachChild(mLaserLine);
    }

    public void buildLaser(GameObject[][] map) {
        mLaserLine.setAngle(getAngle());
        mLaserLine.build(map);
    }

}
