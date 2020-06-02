package com.example.dhlivestreaminganimations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
  public void onCreate(Bundle savedState) {
    super.onCreate(savedState);
    setContentView(R.layout.activity_main);



    ImageButton angryButton = (ImageButton) findViewById(R.id.angry_emoji);
    angryButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            react_angry();
          }
        });
    ImageButton heartButton = (ImageButton) findViewById(R.id.heart_emoji);
    heartButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        react_heart();
      }
    });
    ImageButton loveeyeButton = (ImageButton) findViewById(R.id.loveeye_emoji);
    loveeyeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        react_loveeye();
      }
    });
    Button randomButton = (Button) findViewById(R.id.react_random);
    randomButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        for(int i=0;i<5;i++) {
          react_loveeye();
          react_heart();
          react_angry();
        }
      }
    });


  }

  public void flyEmoji(final int resId) {
    ZeroGravityAnimation animation = new ZeroGravityAnimation();
    animation.setCount(1);
    animation.setScalingFactor(0.07f);
    animation.setOriginationDirection(Direction.BOTTOM);
    animation.setDestinationDirection(Direction.TOP);
    animation.setImage(resId);
    animation.setAnimationListener(new Animation.AnimationListener() {
                                     @Override
                                     public void onAnimationStart(Animation animation) {

                                     }
                                     @Override
                                     public void onAnimationEnd(Animation animation) {

                                     }

                                     @Override
                                     public void onAnimationRepeat(Animation animation) {

                                     }
                                   }
    );

    ViewGroup container = findViewById(R.id.animation_holder);
    animation.play(this,container);

  }

  public void react_heart() {
      flyEmoji(R.drawable.heartt);
  }

  public void react_loveeye(){
      flyEmoji(R.drawable.lobeye);
  }

  public void react_angry(){
      flyEmoji(R.drawable.angry);
  }

/*
 This method will be used if You want to fly your Emois Over any view
   public void flyObject(final int resId, final int duration, final Direction from, final Direction to, final float scale) {

        ZeroGravityAnimation animation = new ZeroGravityAnimation();
        animation.setCount(1);
        animation.setScalingFactor(scale);
        animation.setOriginationDirection(from);
        animation.setDestinationDirection(to);
        animation.setImage(resId);
        animation.setDuration(duration);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                flyObject(resId, duration, from, to, scale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ViewGroup container = (ViewGroup) findViewById(R.id.animation_bigger_objects_holder);
        animation.play(this,container);

    }

 */


}