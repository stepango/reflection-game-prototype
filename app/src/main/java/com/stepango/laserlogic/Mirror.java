package com.stepango.laserlogic;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

public class Mirror extends DynamicGameObject {

    public Mirror(final int posX, final int posY, final int angle,
                  final TextureRegion region) {
        super(posX, posY, angle, region);
    }

    @Override
    void attachTo(Scene scene) {
        scene.getChild(GameObjectsMap.GAME_OBJECTS_LAYER).attachChild(
                getSprite());
    }

    @Override
    int onLaser(int angle) {
        int a = getAngle() % 4;
        if (angle == (a + 1) % 8) return (a + 7) % 8;
        if (angle == (a + 7) % 8) return (a + 1) % 8;
        if (angle == (a + 3) % 8) return (a + 5) % 8;
        if (angle == (a + 5) % 8) return (a + 3) % 8;
        return -1;
    }

}
