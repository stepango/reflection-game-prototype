package com.stepango.laserlogic;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import roboguice.util.Ln;

public class GameObjectsController implements IOnSceneTouchListener {

    GameObjectsMap mMap;
    BaseGameActivity mActivity;

    public GameObjectsController(GameObjectsMap map, BaseGameActivity activity) {
        mMap = map;
        mActivity = activity;
        Ln.i("Game objects controller");
    }

    @Override
    public boolean onSceneTouchEvent(final Scene pScene, TouchEvent pSceneTouchEvent) {
        Ln.i("Click " + pSceneTouchEvent.getMotionEvent());

        if (pSceneTouchEvent.isActionDown()) {
            float x = pSceneTouchEvent.getX();
            float y = pSceneTouchEvent.getY();

            int cellNumX = (int) ((x - x % GameObjectsMap.CELL_SIZE_X)
                    / GameObjectsMap.CELL_SIZE_X);
            int cellNumY = (int) ((y - y % GameObjectsMap.CELL_SIZE_X)
                    / GameObjectsMap.CELL_SIZE_X);
            Ln.i("Click " + cellNumX + " " + cellNumY + "<>" + mMap.getMap().length + " " + mMap.getMap()[0].length);
            DynamicGameObject object = (DynamicGameObject) mMap.getMap()[cellNumX][cellNumY];
            if (object != null) {
                mActivity.runOnUpdateThread(new LaserUpdater(object));
            }
        }
        return false;
    }

    public class LaserUpdater implements Runnable {

        private final DynamicGameObject mObject;

        public LaserUpdater(DynamicGameObject object) {
            mObject = object;
        }

        @Override
        public void run() {
            mObject.rotateRigth();
            Ln.d("Start build lasers");
            mMap.buildLasers();
            Ln.d("End build lasers");
        }

    }

}
