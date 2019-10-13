package com.example.dbfortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class team extends AppCompatActivity {

    private Button bt1;
    private ImageView im1 = null;
    private static final int REQUEST_CODE = 1;
    private TextView tx1, tx2, tx3, tx4;
    private Uri get_uri;
    private String st_room, st_budget, st_travel, st_personnum, st_uri;
    private FlexboxLayout flexboxLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        InitView();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(team.this, room.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    private void InitView(){
        flexboxLayout = findViewById(R.id.fl_test);
        bt1 = findViewById(R.id.button2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            st_room = data.getExtras().getString("room");
            st_travel = data.getExtras().getString("travel");
            st_budget = data.getExtras().getString("budget");
            st_uri = data.getExtras().getString("uri");
            get_uri = Uri.parse(st_uri);
            flexboxLayout.addView(add_View());

        }
    }


    private View add_View() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(this);
        lp.rightMargin = 28;
        lp.topMargin = 10;
        view.setLayoutParams(lp);
        view.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vlp.height = 400;
        vlp.width = 400;
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp3 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp4 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp5 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tx1 = new TextView(this);
        tx2 = new TextView(this);
        tx3 = new TextView(this);
        tx4 = new TextView(this);
        im1 = new ImageView(getApplicationContext());
        im1.setImageURI(get_uri);
        im1.setLayoutParams(vlp);
        tx1.setLayoutParams(vlp2);
        tx2.setLayoutParams(vlp3);
        tx3.setLayoutParams(vlp4);
        tx4.setLayoutParams(vlp5);
        tx1.setText("房名: " + st_room);
        tx2.setText("旅遊行程: " + st_travel);
        tx3.setText("旅遊預算: " + st_budget);
        view.addView(im1);
        view.addView(tx1);
        view.addView(tx2);
        view.addView(tx3);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(team.this, room.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
