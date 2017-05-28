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

package com.seanshend.view.chairview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.seanshend.DataModel;
import com.seanshend.myapplication.MainActivity;
import com.seanshend.view.pieview.PieData;

import java.util.ArrayList;

public class ChairView extends View {

    private DataModel data;

    private int backAngle;
    private int seatAngle;
    private int type = 0;

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

    public void Settype(int i, DataModel data) {
        type = i;
        this.data = data;
        update();
    }

    public void Settype(int i, int _backAngle, int _seatAngle) {
        type = i;
        backAngle = _backAngle;
        seatAngle = _seatAngle;
        invalidate();
    }

    public void update() {
        backAngle = data.getBackAngle();
        seatAngle = data.getSeatAngle();
        Log.d(MainActivity.LOG_TAG, "" + seatAngle + " " + type);
        invalidate();
    }

    public ChairView(Context context) {
        this(context, null);
    }

    public ChairView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
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

        canvas.translate(mWidth / 2, mHeight / 2);                  // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);    // 饼状图半径

        r = (float) (r * 1.1);
//        RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域
        mPaint.setStrokeWidth(5);
//            canvas.drawArc(rect, 0, 360, true, mPaint);

        canvas.translate(-1 * r / 2, 2 * r / 5);


        mPaint.setStrokeWidth(5);

//            canvas.drawCircle(0, 0, r / 18, mPaint);
        if (type == 3) {
            mPaint.setColor(BackgroundColor[0]);
            canvas.rotate(-seatAngle);
            canvas.drawRoundRect(-r / 15, -r / 15, 12 * r / 10, r / 15, r / 20, r / 20, mPaint);
            canvas.rotate(seatAngle);
            mPaint.setColor(0xff4c9fff);
            canvas.rotate(180 + backAngle);
            canvas.drawRoundRect(-r / 15, -r / 15, 12 * r / 10, r / 15, r / 20, r / 20, mPaint);
        } else if (type == 4) {
            mPaint.setColor(BackgroundColor[0]);
            canvas.rotate(180 + backAngle);
            canvas.drawRoundRect(-r / 15, -r / 15, 12 * r / 10, r / 15, r / 20, r / 20, mPaint);
            canvas.rotate(-(180 + backAngle));
            mPaint.setColor(Color.BLACK);
            canvas.rotate(-seatAngle);
            canvas.drawRoundRect(-r / 15, -r / 15, 12 * r / 10, r / 15, r / 20, r / 20, mPaint);
        } else {
            mPaint.setColor(0xff4c9fff);
            canvas.rotate(180 + backAngle);
            canvas.drawRoundRect(-r / 15, -r / 15, 12 * r / 10, r / 15, r / 20, r / 20, mPaint);
            canvas.rotate(-(180 + backAngle));
            mPaint.setColor(Color.BLACK);
            canvas.rotate(-seatAngle);
            canvas.drawRoundRect(-r / 15, -r / 15, 12 * r / 10, r / 15, r / 20, r / 20, mPaint);
        }
    }


    public int getType() {
        return type;
    }


}
