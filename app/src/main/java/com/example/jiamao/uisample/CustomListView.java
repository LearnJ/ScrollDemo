package com.example.jiamao.uisample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;



/**
 * Created by jiamao on 2018/4/26.
 */

public class CustomListView extends ListView {
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
      public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action=ev.getAction();
        if(action== MotionEvent.ACTION_DOWN){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("jm--", "onTouch: "+getScrollX());
                return true;

            case MotionEvent.ACTION_MOVE:
                Log.i("jm--", "onTouch: "+getScrollX());

                break;

            case MotionEvent.ACTION_UP:
                Log.i("jm--", "onTouch: "+getScrollX());
                break;

        }
        boolean intercept2=false;
        intercept2=super.onTouchEvent(event);
        Log.i("jm--", "onTouch: "+intercept2);
        return intercept2;
    }


}
