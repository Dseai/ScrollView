package com.example.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    private TextView tv;
    private ScrollView scroll;
    private Button up_btn;
    private Button down_btn;

    public MainActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.tv = (TextView)this.findViewById(R.id.content);
        this.up_btn = (Button)this.findViewById(R.id.up);
        this.down_btn = (Button)this.findViewById(R.id.down);
        this.up_btn.setOnClickListener(this);
        this.down_btn.setOnClickListener(this);
        this.tv.setText(this.getResources().getString(R.string.content));
        this.scroll = (ScrollView)this.findViewById(R.id.scroll);
        this.scroll.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    //(1)getScrollY()滚动条纵向滑动的距离
                    case MotionEvent.ACTION_MOVE:
                        //没用滑动 处于顶部位置
                        if(MainActivity.this.scroll.getScrollY() <= 0) {
                            Log.i("Main", "滑动到顶部");
                        }
                    //textView的总高度<=一屏幕的高度+滚动条滚动的距离
                        //滑动到底部
                        if(MainActivity.this.scroll.getChildAt(0).getMeasuredHeight() <= MainActivity.this.scroll.getHeight() + MainActivity.this.scroll.getScrollY()) {
                            Log.i("Main", "滑动到底部");
                            Log.i("Main", "scroll.getChildAt(0).getMeasuredHeight()=" + MainActivity.this.scroll.getChildAt(0).getMeasuredHeight() + "scroll,getHeight()=" + MainActivity.this.scroll.getHeight() + "scroll.getScrollY()=" + MainActivity.this.scroll.getScrollY());
                            MainActivity.this.tv.append(MainActivity.this.getResources().getString(R.string.content));
                        }
                    default:
                        return false;
                }
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()) {
            /**
             * scrollTo:以滚动视图起始位置开始计算
             * scrollBy:相对于前一个位置的滚动的距离
             */
            case R.id.up:
                this.scroll.scrollBy(0, -30);
                break;
            case R.id.down:
                this.scroll.scrollBy(0, 30);
        }

    }
}

