package com.example.dbfortest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registered extends AppCompatActivity {
    public static final String GOOGLE_ACCOUNT ="";
    private EditText Gphone,Gmail,Gbirth,Gnickname,Gname;
    private CheckBox boy,girl;
    private Button registered,cancel,goTest;
    private Spinner city_spinner;
    private String get_sex,get_phone,get_birth,get_nickname,get_city,citySp,sex,get_Email,get_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        GlobalVariable gv = (GlobalVariable)getApplicationContext();
        if(gv.getC()>0){
            System.out.println("==================IN=============");
            Intent intent = new Intent();
            intent.setClass(Registered.this,mainView.class);
            startActivity(intent);
        }

        initView();
        useCity_spinneret();
        CleanButton();
        setListeners();
    }
    private void initView(){
        Gphone=findViewById(R.id.phoneNum);
        Gmail=findViewById(R.id.Email);
        Gnickname=findViewById(R.id.nickname);
        Gname =findViewById(R.id.name);
        Gbirth=findViewById(R.id.birth);
        boy=findViewById(R.id.checkBox);
        girl=findViewById(R.id.checkBox2);
        registered=findViewById(R.id.registered);
        cancel=findViewById(R.id.Cancel);
        city_spinner=findViewById(R.id.spinner);
    }
    private void setListeners(){
        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatUser();
                GlobalVariable gv = (GlobalVariable)getApplicationContext();
                gv.countC();
                System.out.println("======================"+gv.countC());
                new AlertDialog.Builder(Registered.this)
                        .setTitle("會員資料建立成功").setNeutralButton("開始旅遊!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setClass(Registered.this,mainView.class);
                        startActivity(intent);
                    }
                }).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(Registered.this,login.class);
                startActivity(intent);
            }
        });


        boy.setOnCheckedChangeListener(mOnCheckedChangeListener);
        girl.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener =new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton cbuttonView, boolean isChecked) {
            int a =cbuttonView.getId();
            if(a==R.id.checkBox){
                if(isChecked){
                    clearCheckbox();
                    boy.setChecked(true);
                    sex="男性";
                }
            }else if(a==R.id.checkBox2){
                if(isChecked){
                    clearCheckbox();
                    girl.setChecked(true);
                    sex="女性";
                }
            }
        }
    };
    private void clearCheckbox(){
        boy.setChecked(false);
        girl.setChecked(false);
    }
    private void CleanButton(){

        Gphone.setOnTouchListener(new View.OnTouchListener() {
            int count=0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count=count+1;
                if(count==1){
                    Gphone.setText("");
                }

                return false;
            }
        });

        Gmail.setOnTouchListener(new View.OnTouchListener() {
            int count=0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                if(count==1){
                    Gmail.setText("");}
                return false;
            }
        });

        Gname.setOnTouchListener(new View.OnTouchListener() {
            int count=0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                if(count==1){
                    Gname.setText("");}
                return false;
            }
        });

        Gnickname.setOnTouchListener(new View.OnTouchListener() {
            int count=0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                if(count==1){
                    Gnickname.setText("");}
                return false;
            }
        });

        Gbirth.setOnTouchListener(new View.OnTouchListener() {
            int count=0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                if(count==1){
                    Gbirth.setText("");}
                return false;
            }
        });
    }
    private void getInfo(){
        get_Email=Gmail.getText().toString();
        get_phone=Gphone.getText().toString();
        get_nickname=Gnickname.getText().toString();
        get_name=Gname.getText().toString();
        get_birth=Gbirth.getText().toString();
        get_city=citySp;
        get_sex=sex;
    }

    private void useCity_spinneret(){
        ArrayAdapter<CharSequence> setCity =ArrayAdapter.createFromResource(this,R.array.live_city
                ,android.R.layout.simple_spinner_item);

        setCity.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        city_spinner.setAdapter(setCity);

        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                citySp = String.valueOf(adapterView.getSelectedItemId());
            }
        });
    }
    private void creatUser() {
        getInfo();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(get_Email);
        myRef.child("Phone").setValue(get_phone);
        myRef.child("Birth").setValue(get_birth);
        myRef.child("LiveCity").setValue(get_city);
        myRef.child("Nickname").setValue(get_nickname);
        myRef.child("Name").setValue(get_name);
        myRef.child("Sexual").setValue(get_sex);
    }

}
