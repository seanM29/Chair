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
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class dayview extends View {

    private int angle;
    private int type = 0;

    // 数据
    private ArrayList<Integer> Data;
    private int[] Angle=new int[5];
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();



    private int[] CircleColor = {0xFFE8E8E8, 0xFF9CACF0, 0xff4ad5e9, 0xfffad362, 0xFFA6D7AF};

    public dayview(Context context) {
        this(context, null);
    }

    public dayview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }

    public void setData( ArrayList<Integer>  data) {
        Data = data;
        updateAngle();
        invalidate();
    }

    public void updateData( ArrayList<Integer>  data) {
        Data = data;
        updateAngle();
        invalidate();
    }

    private void updateAngle() {
        int tmp = 0;

        for(int i=1;i<=4;i++){
            Angle[i]= (int) (1.0*Data.get(i)*360/100);
        }
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

        canvas.translate(mWidth / 2, mHeight / 2);                  // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);    // 饼状图半径
        float r2 = (float) (1.0 * 9 * r / 10);
        float r3 = (float) (1.0 * 8 * r / 10);
        float r4 = (float) (1.0 * 7 * r / 10);

        RectF rect1 = new RectF(-r, -r, r, r);                       // 饼状图绘制区域
        RectF rect2 = new RectF(-r2, -r2, r2, r2);
        RectF rect3 = new RectF(-r3, -r3, r3, r3);
        RectF rect4 = new RectF(-r4, -r4, r4, r4);

        mPaint.setColor(CircleColor[0]);
        canvas.drawArc(rect1, 0, 360, true, mPaint);
        canvas.drawArc(rect2, 0, 360, true, mPaint);
        canvas.drawArc(rect3, 0, 360, true, mPaint);
        canvas.drawArc(rect4, 0, 360, true, mPaint);

        mPaint.setColor(CircleColor[1]);
        canvas.drawArc(rect1, 0, Angle[1], false, mPaint);
        mPaint.setColor(CircleColor[2]);
        canvas.drawArc(rect2, 0, Angle[2], false, mPaint);
        mPaint.setColor(CircleColor[3]);
        canvas.drawArc(rect3, 0, Angle[3], false, mPaint);
        mPaint.setColor(CircleColor[4]);
        canvas.drawArc(rect4, 0, Angle[4], false, mPaint);


        mPaint.setStrokeWidth(5);
        String score =""+Data.get(0);
        canvas.drawText(score, 0, score.length(), 0, 0, pointPaint);
        pointPaint.setTextSize(mHeight / 20);
        mPaint.setStrokeWidth(3);
        canvas.drawText("Total Score", 0, 11, -mHeight/8, mHeight / 8, pointPaint);



    }


    // 设置起始角度
    public void setStartAngle(int mStartAngle) {

        invalidate();   // 刷新
    }




}
