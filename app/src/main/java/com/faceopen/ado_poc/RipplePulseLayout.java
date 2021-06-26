package com.faceopen.ado_poc;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.graphics.Paint.DITHER_FLAG;
public class RipplePulseLayout extends RelativeLayout{
    int width, height;
    private Paint glowingPaint;

    public RipplePulseLayout(Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public RipplePulseLayout(Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    public RipplePulseLayout(Context context, @NonNull AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }
    private RippleView mRippleView;
    float startRadius,endRadius;
    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        TypedArray attrValues = context.obtainStyledAttributes(attrs, R.styleable.RipplePulseLayout);
        startRadius = attrValues.getDimension(R.styleable.RipplePulseLayout_startRadius, getMeasuredWidth());
        endRadius = attrValues.getDimension(R.styleable.RipplePulseLayout_endRadius, getMeasuredWidth() * 2);
        Log.i("testing_no",startRadius+"    "+endRadius);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        glowingPaint = new Paint(ANTI_ALIAS_FLAG | DITHER_FLAG);
        glowingPaint.setStyle(Paint.Style.STROKE);
        glowingPaint.setStrokeWidth(8);
        glowingPaint.setColor(getResources().getColor(R.color.teal_200));
        mRippleView = new RippleView(getContext(), glowingPaint,startRadius,endRadius);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        addView(mRippleView, params);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.device);
        Log.i("testing_size",smallValue()+"");
//        LayoutParams params1 = new LayoutParams((int)(startRadius-30),(int)(startRadius-30));
//        params1.addRule(CENTER_IN_PARENT, TRUE);
//        addView(imageView, params1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public int smallValue(){
        if (getWidth()<getHeight()){
            return getWidth();
        }else {
            return getHeight();
        }
    }

    public int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        Log.i("testing_test",density+"");
        return (int) (dp * density + 0.5f);
    }
    private static class RippleView extends View {
        int width, height;
        private float startRadius;
        private float endRadius;
        private float midRadius;
        Handler handler = new Handler();
        ValueAnimator animator1;
        Paint paint;
        public RippleView(Context context, Paint p,float startRadius,float endRadius) {
            super(context);
            this.paint= p;
            this.startRadius = startRadius;
            this.endRadius = endRadius;
            handler.post(initAnimRunnable);
        }
        //Runnables
        private Runnable initAnimRunnable = new Runnable() {
            int initAnimCount = 1;
            @Override
            public void run() {
                switch (initAnimCount) {
                    case 1:
                        first = true;
                        sec  = false;
                        third = false;
                        break;
                    case 2:
                        first = false;
                        sec  = true;
                        third = false;
                        break;
                    case 3:
                        first = false;
                        sec  = false;
                        third = true;
                        initAnimCount = 0;
                        break;
                }
                initAnimCount++;
                postInvalidate();
                handler.postDelayed(this, 1000);
            }
        };
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            Log.i("testing_test",w+"   "+h+"   "+oldw+"   "+oldh+"  "+dp2px(w));
            width = w;
            height = h;
        }
        boolean first,sec,third= true;
        @Override
        protected void onDraw(Canvas c) {
            Trace.beginSection("glow red");
            Log.i("testing_runnable",first+"  "+sec+"   "+third);
            float xs, ys;
            xs = (float) getWidth() / 2;
            ys = (float) (getHeight() / 2);
            if(first){
//                paint.setAlpha(100);
                c.drawCircle(xs, ys, (int)(startRadius)-20, paint);
//                paint.setAlpha(20);
                c.drawCircle(xs, ys, (int)((endRadius-startRadius)/2+startRadius)-20, paint);
                c.drawCircle(xs, ys, (int)(endRadius)-20, paint);
            }
            if(sec){
//                paint.setAlpha(100);
                c.drawCircle(xs, ys, (int)((endRadius-startRadius)/2+startRadius)-20, paint);
//                paint.setAlpha(20);
                c.drawCircle(xs, ys, (int)(startRadius)-20, paint);
                c.drawCircle(xs, ys, (int)(endRadius)-20, paint);
            }
            if (third){
//                paint.setAlpha(100);
                c.drawCircle(xs, ys, (int)(endRadius)-20, paint);
//                paint.setAlpha(20);
                c.drawCircle(xs, ys, (int)(startRadius)-20, paint);
                c.drawCircle(xs, ys, (int)((endRadius-startRadius)/2+startRadius)-20, paint);
            }
            Bitmap test1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bot), (int)(endRadius-startRadius+30), (int)(endRadius-startRadius+30), false);
            c.drawBitmap(test1, xs-startRadius-30, ys-startRadius-40, new Paint());

        }
        public int dp2px(int dp) {
            float density = getContext().getResources().getDisplayMetrics().density;
            Log.i("testing_test",density+"");
            return (int) (dp * density + 0.5f);
        }
    }
}