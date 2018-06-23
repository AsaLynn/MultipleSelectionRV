package com.example.my01.activity;

import com.example.demonstrate.DialogPage;
import com.example.demonstrate.FirstActivity;
import com.example.my01.listener.PageItemLis1;

public class EnterActivity extends FirstActivity {


    @Override
    protected void click0() {
        DialogPage
                .getInstance()
                .setOnDialogItemListener(new PageItemLis1(this));
    }
}
