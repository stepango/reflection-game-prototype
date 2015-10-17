package com.stepango.laserlogic;

import android.graphics.Point;

import com.stepango.laserlogic.GameObject.Type;

import org.anddev.andengine.entity.scene.Scene;

import java.util.Arrays;
import java.util.LinkedList;

public class GameObjectsMap {

    public static final int HEIGHT = 7;
    public static final int WIDTH = 13;
    public static final float CELL_SIZE_X = 64f;
    public static final float CELL_SIZE_Y = 64f;

    public static final int LASER_LAYER = 0;
    public static final int GAME_OBJECTS_LAYER = LASER_LAYER + 1;

    private GameObject[][] mMap = new GameObject[WIDTH][HEIGHT];
    private LinkedList<LaserGun> mLaserGuns;

    private Textures mTextures;

    public GameObjectsMap(final Textures textures) {
        mTextures = textures;
        mLaserGuns = new LinkedList<>();
    }

    public static boolean outOfArea(Point pos) {
        return pos.x < 0 || pos.y < 0 || pos.x > WIDTH - 1
                || pos.y > HEIGHT - 1;
    }

    public void add(Type type, final int posH, final int posW, final int angle) {
        GameObject object = null;
        switch (type) {
            case LASER_GUN:
                LaserGun l = new LaserGun(posH, posW, angle, mTextures
                        .getLaserGun());
                mLaserGuns.add(l);
                object = l;
                break;
            case MIRROR:
                object = new Mirror(posH, posW, angle, mTextures.getMirror());
                break;
            case TARGET:

                break;
            default:
                break;
        }
        mMap[posH][posW] = object;
    }

    public GameObject[][] getMap() {
        return this.mMap;
    }

    public void clear() {
        for (GameObject[] aMMap : mMap) {
            Arrays.fill(aMMap, null);
        }
    }

    public void addToScene(Scene scene) {
        for (GameObject[] aMMap : mMap) {
            for (GameObject o : aMMap) {
                if (o != null) {
                    o.attachTo(scene);
                }
            }
        }
    }

    public void buildLasers() {
        for (LaserGun gun : mLaserGuns) {
            gun.buildLaser(mMap);
        }
    }

}
