package com.zxl.photowallfallsdemo.test1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zxl.photowallfallsdemo.R;

public class Test1Act extends Activity {
    private Button btn;
    private MyScrollView my_scroll_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test1);

        btn = (Button) findViewById(R.id.btn);
        my_scroll_view = (MyScrollView) findViewById(R.id.my_scroll_view);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                my_scroll_view.refalsh();
            }
        });
    }
}
