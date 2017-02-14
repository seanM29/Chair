package com.seanshend.myapplication;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends BaseFragment {


    TextView t3 = null;
    TextView t5 = null;
    TextView t6 = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initAll(view);
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        t3 = (TextView) view.findViewById(R.id.textView3);
        t5 = (TextView) view.findViewById(R.id.textView5);
        t6 = (TextView) view.findViewById(R.id.seatDegreeTv);
        t3.post(new Runnable() {
            @Override
            public void run() {
                // 将执行代码放到这里
                SetText("用户信息", t3);
            }
        });
        t5.post(new Runnable() {
            @Override
            public void run() {
                // 将执行代码放到这里
                SetText("意见反馈", t5);
            }
        });
        t6.post(new Runnable() {
            @Override
            public void run() {
                // 将执行代码放到这里
                SetText("关于我们", t6);
            }
        });

    }


    private void SetText(String in, TextView t1) {


// 获取TextView中使用的Paint,里面带有字体等信息
        TextPaint paint1 = t1.getPaint();

// TextView中的文字可写的宽度
        int width = t1.getMeasuredWidth() - t1.getPaddingLeft() - t1.getPaddingRight();
// 大字的宽度
        float width1 = paint1.measureText(in) + 1;

// 空格字符的宽度,用paint2来测量精度高点
        float spaceWidth = paint1.measureText(" ");
// 需要填充的空格数量
        int spaceCount = (int) ((width - width1) / spaceWidth);
// 将所有字符串拼接起来
        StringBuilder sb = new StringBuilder();
        sb.append(in);
        Log.i("space:", "" + spaceCount);
        for (int i = 0; i < spaceCount - 2; i++) {
            sb.append(' ');

        }


        sb.append(">");

        Log.i("sb:", sb.toString());

        t1.setText(sb);


    }

}
