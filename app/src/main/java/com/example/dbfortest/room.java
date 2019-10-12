package com.example.dbfortest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class room extends AppCompatActivity {
    private ImageView im_picture;
    private EditText room_name,city_name,budget;
    private Button bt2;
    private String st_roomname,st_budget,st_travel,st_personnum,st_uri;
    private static final int REQUEST_CODE = 1;
    private Uri get_uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        InitView();

        im_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){   //檢查當前API是否大於23(M=23)
                    if (ContextCompat.checkSelfPermission(room.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){ //檢查是否已擁有權限,已擁有權限－PERMISSION_GRANTED,無權限－PERMISSION_DENIED
                        ActivityCompat.requestPermissions(room.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);  //第一個參數傳入Context物件，第二個字串陣列則是欲要求的權限，第三個int是本次請求的辨識編號
                    }else {
                        BringImagePicker();
                    }
                }else{
                    BringImagePicker();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                getstring();
                st_uri = get_uri.getPath();
                bundle.putString("room", st_roomname);
                bundle.putString("travel", st_travel);
                bundle.putString("budget",st_budget);
                bundle.putString("uri",st_uri);
                intent.putExtras(bundle);
                setResult(REQUEST_CODE, intent);
                finish();
            }
        });
    }
    private void InitView()
    {
        im_picture = findViewById(R.id.im_picture);
        room_name = findViewById(R.id.room_name);
        city_name = findViewById(R.id.city_name);
        budget = findViewById(R.id.budget);
        bt2 = findViewById(R.id.button);
    }


    private void BringImagePicker(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .start(room.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri imageUri = result.getUri();
                get_uri = imageUri;
                im_picture.setImageURI(imageUri);
            }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error =result.getError();
            }
        }
    }

    private void getstring() {
        st_roomname = String.valueOf(room_name.getText());
        st_budget = String.valueOf(budget.getText());
        st_travel = String.valueOf(city_name.getText());
    }

}
