package com.example.jiamao.uisample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiamao on 2018/4/25.
 */

public class CustomScrollView extends LinearLayout{

    private View leftView;
    private View rightView;
    private Context context;
    private Scroller mScroller;
    private float rightOffset;

    public static List<CustomScrollView>scrollViews=new ArrayList<CustomScrollView>();

    private boolean isOpen=false;

    public CustomScrollView(Context context) {
        this(context,null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //setOnTouchListener(this);
        this.context=context;
        mScroller=new Scroller(context);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 0) {//如果没有子View,当前ViewGroup没有存在的意义，不用占用空间
            setMeasuredDimension(0, 0);
            return;
        }

        leftView=getChildAt(0);
        rightView=getChildAt(1);
        rightWidth=rightView.getWidth();
        int h1=leftView.getMeasuredHeight();
        int h2=rightView.getMeasuredHeight();
        int w1=leftView.getMeasuredWidth();
        int w2=rightView.getMeasuredWidth();
        int w11=leftView.getWidth();
        int w22=rightView.getWidth();
        rightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollTo(0,0);
                Toast.makeText(context,"点击了删除按键",Toast.LENGTH_SHORT).show();
            }
        });

        int with = MeasureSpec.getSize(widthMeasureSpec);
        int height =  MeasureSpec.getSize(heightMeasureSpec);
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);

        if (withMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(leftView.getMeasuredWidth(),heightMeasureSpec);
        }

    }

    private float lastX=0;
    private float lastY=0;
    private float rightWidth;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//
//        rightWidth=rightView.getWidth();
//        switch (event.getAction()){
//
//            case MotionEvent.ACTION_DOWN:
//
//                for (CustomScrollView scrollView:scrollViews){
//
//                    if (scrollView!=this&&scrollView.isOpen){
//                        scrollView.resetScroll();
//                    }
//                }
//
//                lastX=event.getX();
//                lastY=event.getY();
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                float dx=lastX-event.getX();
//
//                float dy=lastY-event.getY();
//
//                if (Math.abs(dy)<Math.abs(dx)){
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
//
//                if (getScrollX()+dx<0){
//                    scrollTo(0,0);
//                    lastX=event.getX();
//                    break;
//                }
//                //左边已到边，右滑时不再滑动
//                if (getScrollX()<=0 &&dx<0) {//往右滑 dx<0;getScrollX()>0表示左边已经滑出边界
//                    break ;
//                }
//                float xx=rightWidth;
//                if (getScrollX()+dx>rightWidth){
//                    scrollTo((int) rightWidth,0);
//                    lastX=event.getX();
//                    break;
//                }
//                if(getScrollX()>=rightWidth&&dx>0){
//                    break;
//                }
//                scrollBy((int) dx, 0);
//                int sx=getScrollX();
//                Log.i("jm--", "onTouch: "+getScrollX());
//                lastX=event.getX();
//                break;
//
//            case MotionEvent.ACTION_UP:
//                if (getScrollX()>rightWidth/2){
//                    smoothScoller((int) rightWidth,0);
//                    isOpen=true;
//                }
//                else{
//                    smoothScoller(0,0);
//                    isOpen=false;
//                }
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//
//        }
//
//        return super.dispatchTouchEvent(event);
//    }

    private void smoothScoller(int destX,int destY){
        int scrollX = getScrollX();
        int dx = destX - scrollX;
        mScroller.startScroll(scrollX,0,dx,0,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * 获取所有子view最大宽度
     * @return
     */
    private int getMaxChildWidth() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getMeasuredWidth() > maxWidth) {
                maxWidth = childView.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    /**
     * 获取所有子view最大高度
     * @return
     */
    private int getMaxChildHeight() {
        int childCount = getChildCount();
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getMeasuredWidth() > maxWidth) {
                maxWidth = childView.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    /***
     * 将所有子View的高度相加
     **/
    private int getTotleHeight() {
        int childCount = getChildCount();
        int height = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            height += childView.getMeasuredHeight();
        }
        return height;
    }

    /***
     * 将所有子View的宽度相加
     **/
    private int getTotleWidth() {
        int childCount = getChildCount();
        int width = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            width += childView.getMeasuredWidth();
        }
        return width;
    }

    public void resetScroll(){
        smoothScoller(0,0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        boolean intercept=false;

        rightWidth=rightView.getWidth();
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                for (CustomScrollView scrollView:scrollViews){

                    if (scrollView.isOpen){
                        scrollView.resetScroll();
                    }
                }
//                if (!isOpen){//关着时 不拦截
//                    intercept=false;
//                    lastX=event.getX();
//                    lastY=event.getY();
//                    break;
//                }
                lastX=event.getX();
                lastY=event.getY();
                intercept=true;
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:
                float dx=lastX-event.getX();

                float dy=lastY-event.getY();

                if (Math.abs(dy)>Math.abs(dx)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                    intercept= true;
                }

//                if (getScrollX()+dx<0){
//                    scrollTo(0,0);
//                    lastX=event.getX();
//                    break;
//                }
//                //左边已到边，右滑时不再滑动
//                if (getScrollX()<=0 &&dx<0) {//往右滑 dx<0;getScrollX()>0表示左边已经滑出边界
//                    break ;
//                }
//                float xx=rightWidth;
//                if (getScrollX()+dx>rightWidth){
//                    scrollTo((int) rightWidth,0);
//                    lastX=event.getX();
//                    break;
//                }
//                if(getScrollX()>=rightWidth&&dx>0){
//                    break;
//                }
//                scrollBy((int) dx, 0);
//                int sx=getScrollX();
//                Log.i("jm--", "onTouch: "+getScrollX());
//                lastX=event.getX();
                break;

            case MotionEvent.ACTION_UP:
                intercept= false;
//                if (getScrollX()>rightWidth/2){
//                    smoothScoller((int) rightWidth,0);
//                    isOpen=true;
//                }
//                else{
//                    smoothScoller(0,0);
//                    isOpen=false;
//                }
//                getParent().requestDisallowInterceptTouchEvent(false);
//                intercept= false;
                break;

        }
        return super.dispatchTouchEvent(event);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("jm--", "onTouch: "+getScrollX());
               return true;

            case MotionEvent.ACTION_MOVE:

                float dx=lastX-event.getX();
                float dy=lastY-event.getY();

                if (getScrollX()+dx<0){
                    scrollTo(0,0);
                    lastX=event.getX();
                    break;
                }
                //左边已到边，右滑时不再滑动
                if (getScrollX()<=0 &&dx<0) {//往右滑 dx<0;getScrollX()>0表示左边已经滑出边界
                    break ;
                }
                float xx=rightWidth;
                if (getScrollX()+dx>rightWidth){
                    scrollTo((int) rightWidth,0);
                    lastX=event.getX();
                    break;
                }
                if(getScrollX()>=rightWidth&&dx>0){
                    break;
                }
                scrollBy((int) dx, 0);
                int sx=getScrollX();
                Log.i("jm--", "onTouch: "+getScrollX());
                lastX=event.getX();
                break;

            case MotionEvent.ACTION_UP:
                if (getScrollX()>rightWidth/2){
                    smoothScoller((int) rightWidth,0);
                    isOpen=true;
                }
                else{
                    smoothScoller(0,0);
                    isOpen=false;
                }
                break;

        }

        Log.i("jm--", "onTouch: ");
        return false;
    }
}
