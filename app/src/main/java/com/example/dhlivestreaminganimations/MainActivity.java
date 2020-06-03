package com.example.dhlivestreaminganimations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.github.pgreze.reactions.PopupGravity;
import com.github.pgreze.reactions.ReactionPopup;
import com.github.pgreze.reactions.ReactionsConfig;
import com.github.pgreze.reactions.ReactionsConfigBuilder;

public class MainActivity extends Activity {

  public void onCreate(Bundle savedState) {
    super.onCreate(savedState);
    setContentView(R.layout.activity_main);

    ReactionsConfig config = new ReactionsConfigBuilder(this)
        .withReactions(new int[]{
            R.drawable.angry,
            R.drawable.lobeye,
            R.drawable.heartt
        })
        .withPopupGravity(PopupGravity.SCREEN_RIGHT)
        .build();

    ReactionPopup popup = new ReactionPopup(this, config, (position) -> {
      switch (position){
        case 0: react_angry(); break;
        case 1: react_loveeye(); break;
        case 2:    react_heart(); break;
      }
      return true;
    });

    View reactionButton = findViewById(R.id.react_button);
    reactionButton.setOnTouchListener(popup);
  }



  ZeroGravityAnimation animation = new ZeroGravityAnimation();
  public void flyEmoji(final int resId) {
    animation.setScalingFactor(0.07f);
    animation.setOriginationDirection(Direction.BOTTOM);
    animation.setDestinationDirection(Direction.TOP);
    animation.setImage(resId);
    ViewGroup container =(ViewGroup) findViewById(R.id.animation_holder);
    animation.play(this,container);

  }

  public void react_heart() {  flyEmoji(R.drawable.heartt);  }

  public void react_loveeye(){
      flyEmoji(R.drawable.lobeye);
  }

  public void react_angry(){
      flyEmoji(R.drawable.angry);
  }

}