package com.hero.littlenum.highlighttextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by littlenum on 2017/8/8.
 */

public class HighLightTextView extends TextView {
    //页面刷新16ms
    private static final int DURATION = 16;
    //高亮移动方向
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    //循环模式，从左到右，再从左到右
    public static final int START_END_START_END = 0;
    //从左到右，再从右到左
    public static final int START_END_END_START = 1;

    private static final int[] DEFAULT_COLORS = new int[]{0x55ffffff, 0xffffffff, 0x55ffffff};
    private static final float[] DEFAULT_POSITIONS = new float[]{0, 0.5f, 1f};
    //一次移动的周期
    private int period = 2000;
    //高亮部分宽度比例
    private float ratio = 0.25f;
    private LinearGradient gradient;
    private int[] colors = DEFAULT_COLORS;
    private float[] positions = DEFAULT_POSITIONS;
    //高亮的角度，默认为0，水平
    private float degress = 0;
    private int direction = LEFT;
    private int mode = START_END_START_END;

    private Matrix matrix;
    private float translate = 0;
    private int interval;
    private float lightWidth;

    public HighLightTextView(Context context) {
        super(context);
    }

    public HighLightTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HighLightTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public LinearGradient getGradient() {
        return gradient;
    }

    public void setGradient(LinearGradient gradient) {
        this.gradient = gradient;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public float[] getPositions() {
        return positions;
    }

    public void setPositions(float[] positions) {
        this.positions = positions;
    }

    public float getDegress() {
        return degress;
    }

    public void setDegress(float degress) {
        this.degress = degress;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            if (gradient == null) {
                gradient = new LinearGradient(0, 0, w * ratio, 0, colors, positions, Shader.TileMode.CLAMP);
            }
            lightWidth = w * ratio;
            getPaint().setShader(gradient);
            interval = w / (period / DURATION);
            if (direction == RIGHT) {
                translate = w;
            } else {
                translate = -lightWidth;
            }
            matrix = new Matrix();
            matrix.setTranslate(translate, 0);
            matrix.setRotate(degress);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置高亮移动的位置和方向
        if (direction == LEFT) {
            translate += interval;
            if (translate > getWidth()) {
                if (mode == START_END_START_END) {
                    translate = -lightWidth;
                } else {
                    direction = RIGHT;
                    translate = getWidth();
                }
            }
        } else {
            translate -= interval;
            if (translate < -lightWidth) {
                if (mode == START_END_START_END) {
                    translate = getWidth();
                } else {
                    direction = LEFT;
                    translate = -lightWidth;
                }
            }
        }
        matrix.setTranslate(translate, 0);
        gradient.setLocalMatrix(matrix);
        postInvalidate();
    }
}
