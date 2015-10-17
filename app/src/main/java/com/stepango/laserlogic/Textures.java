package com.stepango.laserlogic;

import android.util.Log;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class Textures {

    private Texture mTexture;
    private TextureRegion mLaserGunTextureRegion, mBackgroundTextureRegion, mMirrorTextureRegion;

    public Textures(final BaseGameActivity activity, final Engine engine) {
        Log.i("Laser Logic", "onLoadResources");
        this.mTexture = new Texture(1024, 512,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mLaserGunTextureRegion = TextureRegionFactory.createFromAsset(
                this.mTexture, activity, "gfx/laser_gun.png", 800, 0);
        this.mMirrorTextureRegion = TextureRegionFactory.createFromAsset(
                this.mTexture, activity, "gfx/mirror.png", 864, 0);
        this.mBackgroundTextureRegion = TextureRegionFactory.createFromAsset(
                this.mTexture, activity, "gfx/laser_logic_background.png", 0, 0);
        engine.getTextureManager().loadTexture(this.mTexture);
    }

    public TextureRegion getBackground() {
        return mBackgroundTextureRegion;
    }

    public TextureRegion getLaserGun() {
        return mLaserGunTextureRegion;
    }

    public TextureRegion getMirror() {
        return mMirrorTextureRegion;
    }

}
