package com.example.dhlivestreaminganimations;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;


public class ZeroGravityAnimation {

  private static final int RANDOM_DURATION = -1;

  private Direction mOriginationDirection = Direction.RANDOM;
  private Direction mDestinationDirection = Direction.RANDOM;
  private int mDuration = RANDOM_DURATION;
  private int mImageResId;
  private float mScalingFactor = 1f;


  /**
   * Sets the orignal direction. The animation will originate from the given direction.

   */
  public ZeroGravityAnimation setOriginationDirection(Direction direction) {
    this.mOriginationDirection = direction;
    return this;
  }

  /**
   * Sets the animation destination direction. The translate animation will proceed towards the given direction.
   * @param direction
   * @return
   */
  public ZeroGravityAnimation setDestinationDirection(Direction direction) {
    this.mDestinationDirection = direction;
    return this;
  }


  public ZeroGravityAnimation setRandomDuration() {
    return setDuration(RANDOM_DURATION);
  }


  public ZeroGravityAnimation setDuration(int duration) {
    this.mDuration = duration;
    return this;
  }


  /**
   * Sets the image reference id for drawing the image
   * @param resId
   * @return
   */
  public ZeroGravityAnimation setImage(int resId) {
    this.mImageResId = resId;
    return this;
  }

  /**
   * Sets the image scaling value.
   * @param scale
   * @return
   */
  public ZeroGravityAnimation setScalingFactor(float scale) {
    this.mScalingFactor = scale;
    return this;
  }


  /**
   * Starts the Zero gravity animation by creating an OTT and attach it to th given ViewGroup
   * @param activity
   * @param ottParent
   */
  public void play(Activity activity, ViewGroup ottParent) {

    DirectionGenerator generator = new DirectionGenerator();

        Direction origin = mOriginationDirection == Direction.RANDOM ? generator.getRandomDirection() : mOriginationDirection;
        Direction destination = mDestinationDirection == Direction.RANDOM ? generator.getRandomDirection(origin) : mDestinationDirection;

        int startingPoints[] = generator.getPointsInDirection(activity, origin);
        int endPoints[] = generator.getPointsInDirection(activity,destination);


        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), mImageResId);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * mScalingFactor), (int) (bitmap.getHeight() * mScalingFactor), false);

        switch (origin) {
          case LEFT:
            startingPoints[0] -= scaledBitmap.getWidth();
            break;

          case RIGHT:
            startingPoints[0] += scaledBitmap.getWidth();
            break;

          case TOP:
            startingPoints[1] -= scaledBitmap.getHeight();
            break;
          case BOTTOM:
            startingPoints[1] += scaledBitmap.getHeight();
            break;
        }

        switch (destination) {
          case LEFT:
            endPoints[0] -= scaledBitmap.getWidth();
            break;

          case RIGHT:
            endPoints[0] += scaledBitmap.getWidth();
            break;

          case TOP:
            endPoints[1] -= scaledBitmap.getHeight();
            break;
          case BOTTOM:
            endPoints[1] += scaledBitmap.getHeight();
            break;
        }


        final OverTheTopLayer layer = new OverTheTopLayer();

        FrameLayout ottLayout = layer.with(activity)
            .scale(mScalingFactor)
            .attachTo(ottParent)
            .setBitmap(scaledBitmap, startingPoints)
            .create();


        int deltaX = endPoints[0]  - startingPoints[0];
        int deltaY = endPoints[1] - startingPoints[1];

        int duration = mDuration;
        if (duration == RANDOM_DURATION) {
          duration = RandomUtil.generateRandomBetween(3500, 7000);
        }

        TranslateAnimation animation = new TranslateAnimation(0, deltaX, 0, deltaY);
        animation.setDuration(duration);
        layer.applyAnimation(animation);
      }
    }



