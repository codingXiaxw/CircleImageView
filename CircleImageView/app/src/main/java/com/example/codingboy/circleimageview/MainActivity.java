package com.example.codingboy.circleimageview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView circleImage;
    private Button changeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleImage= (ImageView) findViewById(R.id.circleImage);
        changeImage= (Button) findViewById(R.id.changeImage);

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ChangeImage.class);
                startActivityForResult(intent,1);
            }
        });

    }
//    将image转化成圆形头像
    public void toRoundBitmap(Bitmap bitmap)
    {

    }


}
