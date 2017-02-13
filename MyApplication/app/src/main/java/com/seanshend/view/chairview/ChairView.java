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
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.seanshend.view.pieview.PieData;

import java.util.ArrayList;

public class ChairView extends View {

    private int angle;
    private int type=0;

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
    private int []BackgroundColor ={0xFFE8E8E8};

    public void Settype(int i,int data){
        type=i;
        angle=data;
        invalidate();
    }

    public void updateData(int data){
        angle=data;
        invalidate();
    }

    public void addAngle(){
        angle++;
        invalidate();
    }
    public void minAngle(){
        angle--;
        invalidate();
    }


    public ChairView(Context context) {
        this(context, null);
    }

    public ChairView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.STROKE);
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


        if(type==0) {           //默认状态
            canvas.translate(-mWidth / 4,0);
            r= (float) (r*1.1);
            RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域

            mPaint.setColor(BackgroundColor[0]);
            canvas.drawArc(rect, 0, 360, true, mPaint);

            canvas.translate(-1*r/4,2*r/5);


            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.BLACK );
            canvas.drawCircle(0,0,r/18,mPaint);

            canvas.drawRoundRect(-  r / 15,-r/15,11*r/10,r/15,r/20,r/20,mPaint);
            canvas.rotate(250);
            canvas.drawRoundRect(-  r / 15,-r/15,11*r/10,r/15,r/20,r/20,mPaint);

        }
        else if(type==1){
            r= (float) (r*1.1);
            RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域

            mPaint.setColor(BackgroundColor[0]);
            canvas.drawArc(rect, 0, 360, true, mPaint);

            canvas.translate(r/2,2*r/5);


            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.BLACK );
            canvas.drawCircle(0,0,r/18,mPaint);

            canvas.drawRoundRect(-12*r/10,-r/15,  r / 15,r/15,r/20,r/20,mPaint);
            canvas.rotate(angle);
            //canvas.rotate(50);
            canvas.drawRoundRect(-12*r/10,-r/15,  r / 15,r/15,r/20,r/20,mPaint);
        }
        else if(type==2){
            r= (float) (r*1.1);
            RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域

            mPaint.setColor(BackgroundColor[0]);
            canvas.drawArc(rect, 0, 360, true, mPaint);

            canvas.translate(-1*r/2,2*r/5);


            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.BLACK );
            canvas.drawCircle(0,0,r/18,mPaint);

            canvas.drawRoundRect(-  r / 15,-r/15,12*r/10,r/15,r/20,r/20,mPaint);

            canvas.rotate(angle);

            canvas.drawRoundRect(-  r / 15,-r/15,12*r/10,r/15,r/20,r/20,mPaint);
        }
        else if(type==3){
            r= (float) (r*1.1);
            RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域
            mPaint.setStrokeWidth(10);
            mPaint.setColor(BackgroundColor[0]);
            canvas.drawArc(rect, 0, 360, true, mPaint);

            canvas.translate(r/2,2*r/5);


            mPaint.setStrokeWidth(5);

            canvas.drawCircle(0,0,r/18,mPaint);

            canvas.drawRoundRect(-12*r/10,-r/15,  r / 15,r/15,r/20,r/20,mPaint);
            mPaint.setColor(0xff4c9fff);
            canvas.rotate(angle);
            canvas.drawRoundRect(-12*r/10,-r/15,  r / 15,r/15,r/20,r/20,mPaint);
        }
        else{
            r= (float) (r*1.1);
            RectF rect = new RectF(-r, -r, r, r);                       // 饼状图绘制区域
            mPaint.setStrokeWidth(10);
            mPaint.setColor(BackgroundColor[0]);
            canvas.drawArc(rect, 0, 360, true, mPaint);

            canvas.translate(-1*r/2,2*r/5);


            mPaint.setStrokeWidth(5);

            canvas.drawCircle(0,0,r/18,mPaint);

            canvas.drawRoundRect(-  r / 15,-r/15,12*r/10,r/15,r/20,r/20,mPaint);
            canvas.rotate(angle);
            mPaint.setColor(Color.BLACK );
            canvas.drawRoundRect(-  r / 15,-r/15,12*r/10,r/15,r/20,r/20,mPaint);
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
        canvas.drawText(str,0,4,-r/4, 4*r/7,textPaint);

        Paint pointPaint = new Paint();
        pointPaint.setColor(Color.WHITE);
        pointPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        pointPaint.setTextSize(2*r/5);

        String poins = String.valueOf(mData.get(index).getValue());
        canvas.drawText(poins,0,2,-r/6, 0,pointPaint);
        //   canvas.translate(r/4+r/6,-4*r/7);
    }


    // 设置起始角度
    public void setStartAngle(int mStartAngle) {

        invalidate();   // 刷新
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

    public int getAngle() {
        return angle;
    }

    // 初始化数据
   /* private void initData(ArrayList<PieData> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);

            sumValue += pie.getValue();       //计算数值和

            int j = i % mColors.length;       //设置颜色
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);

            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;

            Log.i("angle", "" + pie.getAngle());
        }
    }
*/
}
