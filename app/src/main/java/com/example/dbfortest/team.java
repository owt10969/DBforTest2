package com.example.dbfortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class team extends AppCompatActivity {

    private Button bt1;
    private LinearLayout ll,ll2;
    private ImageView im1 = null;
    private static final int REQUEST_CODE = 1;
    private TextView tx1,tx2,tx3,tx4;
    private Uri get_uri;
    private String st_room,st_budget,st_travel,st_personnum,st_uri;
    private ImageTextButton1 imtb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        InitView();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(team.this, room.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        add_view();
    }
    private void InitView(){
        ll = findViewById(R.id.ll);
        ll2 = findViewById(R.id.ll2);
        bt1 = findViewById(R.id.button2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            st_room = data.getExtras().getString("room");
            tx1.setText("房名: " + st_room);
            st_travel = data.getExtras().getString("travel");
            tx2.setText("旅遊行程: " + st_travel);
            st_budget = data.getExtras().getString("budget");
            tx3.setText("旅遊預算: " + st_budget);
            st_uri = data.getExtras().getString("uri");
            get_uri = Uri.parse(st_uri);
            im1 = new ImageView(getApplicationContext());
            im1.setImageURI(get_uri);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
            params.topMargin = 5;
            params.leftMargin = 28;
            ll.addView(im1);


        }
    }

    private void add_view(){
        tx1 = new TextView(this);
        tx2 = new TextView(this);
        tx3 = new TextView(this);
        tx4 = new TextView(this);
        ll.addView(tx1);
        ll.addView(tx2);
        ll.addView(tx3);
        ll.addView(tx4);
    }

    public class ImageTextButton1 extends LinearLayout {

        private ImageView im2;
        private TextView tx2;

        public ImageTextButton1 (Context context){
            super(context ,null);
        }

        public ImageTextButton1 (Context context, AttributeSet attributeSet){
            super(context,attributeSet);

            LayoutInflater.from(context).inflate(R.layout.activity_main, this,true);

            this.im2 = findViewById(R.id.im2);
            this.tx2 = findViewById(R.id.tx2);

            this.setClickable(true);
            this.setFocusable(true);

        }

        public void setImageUri (Uri uri){
            this.im2.setImageURI(uri);
        }

        public void setText (String text){
            this.tx2.setText(text);
        }

        public void setTextColor(int color){
            this.tx2.setTextColor(color);
        }

        public void setTextSize(float size){
            this.tx2.setTextSize(size);
        }

    }
}
