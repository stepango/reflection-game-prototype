package com.stepango.laserlogic;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import roboguice.util.Ln;

public abstract class DynamicGameObject extends GameObject {

    public static final int DEG_0 = 0;
    public static final int DEG_45 = 1;
    public static final int DEG_90 = 2;
    public static final int DEG_135 = 3;
    public static final int DEG_180 = 4;
    public static final int DEG_225 = 5;
    public static final int DEG_270 = 6;
    public static final int DEG_315 = 7;

    private static final float ANGLE = 45f;

    private int mAngle;

    public DynamicGameObject(final int posX, final int posY, final int angle,
                             final TextureRegion region) {
        super(posX, posY, region);
        setAngle(angle);
    }

    public int getAngle() {
        return mAngle;
    }

    public void setAngle(int angle) {
        this.mAngle = angle;
        rotateSprite();
    }

    public void rotateLeft() {
        mAngle--;
        if (mAngle < DEG_0) {
            mAngle = DEG_315;
        }
        rotateSprite();
    }

    public void rotateRigth() {
        mAngle++;
        if (mAngle > DEG_315) {
            mAngle = DEG_0;
        }
        rotateSprite();
    }

    public float getRotationAngle() {
        return ANGLE * mAngle;
    }

    public float getCenterX() {
        return mSprite.getWidth() / 2 + mSprite.getInitialX();
    }

    public float getCenterY() {
        return mSprite.getHeight() / 2 + mSprite.getInitialY();
    }

    protected void rotateSprite() {
        Ln.i("Start rotating ");
        mSprite.setRotation(getRotationAngle());
        Ln.i("End rotating: angle " + getAngle());
    }

}
