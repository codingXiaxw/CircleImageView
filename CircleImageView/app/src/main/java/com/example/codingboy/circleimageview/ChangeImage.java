package com.example.codingboy.circleimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
                                    //待更新

                                    lastDistance = currentDistance;
                                } else if (lastDistance - currentDistance > 5) //缩小图片操作
                                {

                                    //待更新

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



    @Override
    public void onClick(View v)
    {

    }
}
