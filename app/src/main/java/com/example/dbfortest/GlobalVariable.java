package com.example.dbfortest;

import android.app.Application;

public class GlobalVariable extends Application {
    public boolean judge;
    public static int c=0;
    public int countC(){
        c=c+1;
        return c;
    }
    public int getC(){
        return c;
    }
}
