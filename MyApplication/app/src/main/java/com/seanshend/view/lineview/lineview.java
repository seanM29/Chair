/*
 * Copyright 2016 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2016-10-02 00:35:57
 *
 */

package com.seanshend.view.lineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.seanshend.view.pieview.PieData;

import java.util.ArrayList;

public class lineview extends View {

    private int angle = 50;
    private int type = 0;

    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();

    // 文字色块部分
    private PointF mStartPoint = new PointF(20, 20);
    private PointF mCurrentPoint = new PointF(mStartPoint.x, mStartPoint.y);
    private float mColorRectSideLength = 20;
    private float mTextInterval = 10;
    private float mRowMaxLength;
    private int[] BackgroundColor = {0xFFE8E8E8};

    public lineview(Context context) {
        this(context, null);
    }

    public lineview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }

    public void setType(int i) {
        type = i;
        invalidate();
    }

    public void setType(int i, int data) {
        type = i;
        angle = data;
        invalidate();
    }

    public void addAngle() {
        angle++;
        invalidate();
    }

    public void minAngle() {
        angle--;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(0, mHeight / 2);                  // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);    // 饼状图半径
        float center = (float) (1.0 * angle * mWidth / 90.0);


        if (type == 0) {           //默认状态
            mPaint.setStrokeWidth(5);
            mPaint.setColor(0xff4c9fff);

            canvas.drawLine(0, 0, center, 0, mPaint);
            mPaint.setColor(BackgroundColor[0]);
            canvas.drawLine(center, 0, mWidth, 0, mPaint);
            canvas.translate(center, 0);
            mPaint.setColor(0xffffffff);
            mPaint.setStyle(Paint.Style.FILL);
            RectF rect1 = new RectF(-r - 1, -r - 1, r + 1, r + 1);
            canvas.drawArc(rect1, 0, 360, true, mPaint);
            mPaint.setColor(0x19000000);
            mPaint.setStyle(Paint.Style.STROKE);
            RectF rect2 = new RectF(-r - 1, -r - 1, r + 1, r + 1);
            canvas.drawArc(rect2, 0, 360, true, mPaint);
        } else {
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.BLACK);
            canvas.drawLine(0, 0, center, 0, mPaint);
            mPaint.setColor(BackgroundColor[0]);
            canvas.drawLine(center, 0, mWidth, 0, mPaint);
            canvas.translate(center, 0);

            RectF rect = new RectF(-r, -r, r, r);
            canvas.drawArc(rect, 0, 360, true, mPaint);
        }


    }


}
