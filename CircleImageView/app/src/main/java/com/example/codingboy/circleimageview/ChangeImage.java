package com.example.codingboy.circleimageview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;

/**
 * Created by codingBoy on 16/4/20.
 */
public class ChangeImage extends Activity implements View.OnClickListener
{

    private ImageView backgroundImage;
    private Button savebtn,backbtn,changebtn;
    private FrameLayout frameLayout;
    int [] imageId=new int[]{R.drawable.b,R.drawable.c,R.drawable.d};
    int i=0;
    Bitmap bitmap;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changeimage);


        backgroundImage= (ImageView) findViewById(R.id.backgroundImage);
        savebtn= (Button) findViewById(R.id.save);
        changebtn= (Button) findViewById(R.id.change);
        backbtn= (Button) findViewById(R.id.back);
        frameLayout= (FrameLayout) findViewById(R.id.frameLayout);

        savebtn.setOnClickListener(this);
        backbtn.setOnClickListener(this);
        changebtn.setOnClickListener(this);

        backgroundImage.setImageResource(imageId[i]);

        setRoundCircle();

        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            float currentDistance;
            float lastDistance = -1;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        //当触摸手指数等于1个时，代表对图片做出移动操作。
                        if (event.getPointerCount() == 1) {
                            FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) backgroundImage.getLayoutParams();
                            lp.leftMargin = (int) event.getX();
                            lp.bottomMargin= (int) event.getY();
                            backgroundImage.setLayoutParams(lp);

                        }

                        //当触摸手指数大于2个时，代表对图像做出放大活着缩小操作
                        if (event.getPointerCount() >= 2) {

                            float offsetX = event.getX(0) - event.getX(1);
                            float offsetY = event.getY(0) - event.getY(1);
                            currentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);

                            if (lastDistance < 0) {
                                lastDistance = currentDistance;
                            } else {
                                if (currentDistance - lastDistance > 5)    //放大图片操作
                                {
                                    FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) backgroundImage.getLayoutParams();
                                    lp.width= (int) (backgroundImage.getWidth()*1.1);
                                    lp.height= (int) (backgroundImage.getHeight()*1.1);
                                    backgroundImage.setLayoutParams(lp);

                                    lastDistance = currentDistance;
                                } else if (lastDistance - currentDistance > 5) //缩小图片操作
                                {
                                    FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) backgroundImage.getLayoutParams();
                                    lp.width= (int) (backgroundImage.getWidth()*0.9);
                                    lp.height= (int) (backgroundImage.getHeight()*0.9);
                                    backgroundImage.setLayoutParams(lp);


                                    lastDistance = currentDistance;
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void setRoundCircle() {
        Paint paint=new Paint();
        Rect rect=new Rect()
        Canvas canvas=new Canvas()
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back:
                finish();
                break;
            case R.id.change:
                  i++;
                if(i>=0&&i<3) {

                    backgroundImage.setImageResource(imageId[i]);
                }else if (i==3)
                {
                    i-=3;
                    backgroundImage.setImageResource(imageId[i]);
                }
                break;
            case R.id.save:
                Intent intent=new Intent();
                bitmap.createBitmap(((BitmapDrawable)backgroundImage.getDrawable()).getBitmap(),100,100,100,100);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                intent.putExtra("bitmap", bytes);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
