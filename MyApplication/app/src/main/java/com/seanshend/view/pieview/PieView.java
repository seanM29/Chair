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

package com.seanshend.view.pieview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class PieView extends View {


    private int type=0;

    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();

    // 文字色块部分
    private PointF mStartPoint = new PointF(20, 20);



    public void Settype(int i){
        type=i;
        invalidate();
    }


    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
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
        if (null == mData)
            return;

        canvas.translate(mWidth / 2, mHeight / 2);                  // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);    // 饼状图半径


      if(type==0) {           //默认状态
          canvas.translate(0, -mHeight /8 );
          r= (float) (r*0.9);
          RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域

          mPaint.setColor( mData.get(0).getColor());
          canvas.drawArc(rect, 0, 360, true, mPaint);

          String str = mData.get(0).getName();
          Paint textPaint = new Paint();
          textPaint.setColor(Color.WHITE);
          textPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
          textPaint.setTextSize(r/4);


          // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
          canvas.drawText(str,0,str.length(),-r/4, 4*r/7,textPaint);

          Paint pointPaint = new Paint();
          pointPaint.setColor(Color.WHITE);
          pointPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
          pointPaint.setTextSize(2*r/5);

          String poins = String.valueOf(mData.get(0).getValue());
          canvas.drawText(poins,0,2,-r/6, 0,pointPaint);


      }
        else{
          r= (float) (r*0.5);
          canvas.translate(-mWidth / 4, -mHeight /4 );
          Matrix m;
          m = canvas.getMatrix();
          Draw(canvas,r,1);
          canvas.translate(mWidth / 2, 0 );
          Draw(canvas,r,2);
          canvas.translate(-mWidth / 2, mHeight /2 );
          Draw(canvas,r,3);
          canvas.translate(mWidth / 2, 0 );
          Draw(canvas,r,4);
       }


    }

    void Draw(Canvas canvas,float r,int index){

        RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域

        mPaint.setColor( mData.get(index).getColor());
        canvas.drawArc(rect, 0, 360, true, mPaint);

        String str = mData.get(index).getName();
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        textPaint.setTextSize(r/4);


        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        canvas.drawText(str,0,str.length(),-r/4, 4*r/7,textPaint);

        Paint pointPaint = new Paint();
        pointPaint.setColor(Color.WHITE);
        pointPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        pointPaint.setTextSize(2*r/5);

        String poins = String.valueOf(mData.get(index).getValue());
        canvas.drawText(poins,0,2,-r/6, 0,pointPaint);
     //   canvas.translate(r/4+r/6,-4*r/7);
    }



    // 设置数据
    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        //initData(mData);
        invalidate();   // 刷新
    }

    public int getType() {
        return type;
    }

}
