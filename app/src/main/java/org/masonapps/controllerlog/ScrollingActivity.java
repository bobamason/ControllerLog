package org.masonapps.controllerlog;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    private TextView mLogTextView;
    private NestedScrollView mScrollView;
    private boolean mShouldScroll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        mScrollView = (NestedScrollView) findViewById(R.id.logScrollView);
        mScrollView.setSmoothScrollingEnabled(true);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mShouldScroll = false;
            }
        });
        
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShouldScroll = true;
                mScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
        
        mLogTextView = (TextView) findViewById(R.id.logTextView);
        mLogTextView.setText("");
     }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        print("onKeyDown() ");
        println(event.toString());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        print("onKeyUp() ");
        println(event.toString());
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        print("onGenericMotionEvent() ");
        println(event.toString());
        return super.onGenericMotionEvent(event);
    }

    private void println(String s) {
        print(s);
        println();
    }

    private void print(String s) {
        mLogTextView.append(s);
    }

    private void println() {
        mLogTextView.append("\n");
        if(mShouldScroll){
            mScrollView.fullScroll(View.FOCUS_DOWN);
        }
    }
}
