package com.zxl.photowallfallsdemo;

import android.util.Log;

/**
 * @Description:
 * @Author: zxl
 * @Date: 2016/12/8 15:40
 */

public class LLL {

    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 2000;
    public static final String TAG = "zxl-----";

    public static void eee(String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                if (i == 0) {
                    Log.e(TAG, msg.substring(start, end));
                } else {
                    Log.e("", msg.substring(start, end));
                }
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.e(TAG, msg.substring(start, strLength));
                break;
            }
        }
    }

    public static void main(String[] args) {
      
    }
}
