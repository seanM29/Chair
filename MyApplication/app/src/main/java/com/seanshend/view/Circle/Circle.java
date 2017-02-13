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

package com.seanshend.view.Circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Circle extends View {

    private int type = 1;


    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();


    private int[] CircleColor = {0xFFE8E8E8, 0xFF9CACF0, 0xff4ad5e9, 0xfffad362, 0xFFA6D7AF};

    public Circle(Context context) {
        this(context, null);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
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


        RectF rect1 = new RectF(-r, -r, r, r);                       // 饼状图绘制区域

        mPaint.setColor(CircleColor[type]);
        canvas.drawArc(rect1, 0, 360, true, mPaint);


    }
        public void setType(int index){
            type=index;
            invalidate();
        }

}
