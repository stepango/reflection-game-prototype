package com.stepango.laserlogic;

import android.util.DisplayMetrics;

import com.stepango.laserlogic.GameObject.Type;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import roboguice.util.Ln;

public class StageActivity extends BaseGameActivity {

    private static final int CAMERA_WIDTH = 800;
    private static final int CAMERA_HEIGHT = 480;

    private static final int NUMBER_OF_LAYERS = 2;

    private Camera mCamera;

    private Textures mTextures;

    private GameObjectsMap mGameObjectsMap;
    private GameObjectsController mGameObjectsController;

    @Override
    public void onLoadComplete() {
        Ln.i("onLoadComplete");
        initMap();
    }

    private void initMap() {
        mGameObjectsMap = new GameObjectsMap(mTextures);
        mGameObjectsMap.add(Type.LASER_GUN, 0, 0, 3);
        mGameObjectsMap.add(Type.LASER_GUN, 3, 2, 7);
        mGameObjectsMap.add(Type.LASER_GUN, 3, 4, 4);
        mGameObjectsMap.add(Type.MIRROR, 3, 3, 3);
        mGameObjectsMap.addToScene(mEngine.getScene());
        mGameObjectsController = new GameObjectsController(mGameObjectsMap, this);
        final Scene scene = getEngine().getScene();
        mGameObjectsMap.buildLasers();
        scene.setOnSceneTouchListener(mGameObjectsController);
        System.gc();
    }

    @Override
    public Engine onLoadEngine() {
        Ln.d("OnLoadEngine");
        this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        final EngineOptions options = new EngineOptions(true,
                ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
                getScreenResolutionRatio()), this.mCamera);
        return new Engine(options);
    }

    @Override
    public void onLoadResources() {
        mTextures = new Textures(this, getEngine());
    }

    @Override
    public Scene onLoadScene() {
        Ln.i("OnLoadScene");
        this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene = new Scene(NUMBER_OF_LAYERS);
        scene.setBackground(new SpriteBackground(new Sprite(0, 0, mTextures
                .getBackground())));
        return scene;
    }

    private float getScreenResolutionRatio() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return ((float) dm.widthPixels) / ((float) dm.heightPixels);
    }

}
