package com.stepango.laserlogic;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public abstract class GameObject extends Entity {

    protected Sprite mSprite;

    public GameObject(final int posX, final int posY, final TextureRegion region) {
        mSprite = new Sprite(posX * GameObjectsMap.CELL_SIZE_X, posY
                * GameObjectsMap.CELL_SIZE_X, region);
        attachChild(mSprite);
    }

    protected Sprite getSprite() {
        return mSprite;
    }

    /**
     * @param angle - Angle of laser
     * @return flase when laser stops, true otherwise
     */

    abstract int onLaser(int angle);

    abstract void attachTo(Scene scene);

    public enum Type {
        LASER_GUN, MIRROR, TARGET
    }

}
