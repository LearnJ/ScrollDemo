package com.example.jiamao.uisample;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * Created by jiamao on 2018/4/25.
 */

public class CustomTextView extends AppCompatTextView {

    private int currNum;
    private int duration;
    private int finalNum;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void setNumText(int num, final int duration){
        currNum=0;
        finalNum=num;
        ValueAnimator valueAnimator =new ValueAnimator();
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction()<1) {

                    setText(""+(int)(finalNum*animation.getAnimatedFraction()));
                    postInvalidate();
                }
                if(animation.getAnimatedFraction()==1){
                    setText("完成了");
                }
            }
        });
        valueAnimator.setIntValues(0,100);
        valueAnimator.start();

    }

}
