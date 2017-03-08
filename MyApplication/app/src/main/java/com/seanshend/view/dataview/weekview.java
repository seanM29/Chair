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

package com.seanshend.view.dataview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class weekview extends View {


    private int type = 1;

    // 数据
    private ArrayList<Integer> mData;

    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();

    // 文字色块部分
    private PointF mStartPoint = new PointF(20, 20);

    private int[] CircleColor = {0xFFE8E8E8, 0xFF9CACF0, 0xff4ad5e9, 0xfffad362, 0xFFA6D7AF};

    public weekview(Context context) {
        this(context, null);
    }

    public weekview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }


    // 设置数据
    public void setmData(ArrayList<Integer> mData) {
        this.mData = mData;
        invalidate();   // 刷新
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

        Paint pointPaint = new Paint();
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        pointPaint.setTextSize(mHeight / 10);

        canvas.translate(mWidth/7, 3 * mHeight / 4);                  // 将画布坐标原点移动到中心位置
        float max = 3 * mHeight / 4;
        float s1 = (float) (1.0 * mData.get(1) / 100 * max);
        float s2 = (float) (1.0 * mData.get(2) / 100 * max);
        float s3 = (float) (1.0 * mData.get(3) / 100 * max);
        float s4 = (float) (1.0 * mData.get(4) / 100 * max);
        float s5 = (float) (1.0 * mData.get(5) / 100 * max);
        float s6 = (float) (1.0 * mData.get(6) / 100 * max);
        float s7 = (float) (1.0 * mData.get(7) / 100 * max);


        mPaint.setColor(CircleColor[mData.get(0)]);
        canvas.drawLine(0, 0, 0, -s1, mPaint);
        canvas.drawText("M", 0, 1, -mHeight / 20, mHeight / 8, pointPaint);

        canvas.translate(mWidth / 8, 0);
        canvas.drawLine(0, 0, 0, -s2, mPaint);
        canvas.drawText("T", 0, 1, -mHeight / 20, mHeight / 8, pointPaint);

        canvas.translate(mWidth / 8, 0);
        canvas.drawLine(0, 0, 0, -s3, mPaint);
        canvas.drawText("W", 0, 1, -mHeight / 20, mHeight / 8, pointPaint);

        canvas.translate(mWidth / 8, 0);
        canvas.drawLine(0, 0, 0, -s4, mPaint);
        canvas.drawText("T", 0, 1, -mHeight / 20, mHeight / 8, pointPaint);

        canvas.translate(mWidth / 8, 0);
        canvas.drawLine(0, 0, 0, -s5, mPaint);
        canvas.drawText("F", 0, 1, -mHeight / 20, mHeight / 8, pointPaint);

        canvas.translate(mWidth / 8, 0);
        canvas.drawLine(0, 0, 0, -s6, mPaint);
        canvas.drawText("S", 0, 1, -mHeight / 20, mHeight / 8, pointPaint);

        canvas.translate(mWidth / 8, 0);
        canvas.drawLine(0, 0, 0, -s7, mPaint);
        canvas.drawText("S", 0, 1, -mHeight / 20, mHeight / 8, pointPaint);


    }






}
