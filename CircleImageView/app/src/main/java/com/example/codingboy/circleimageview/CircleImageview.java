package com.example.codingboy.circleimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by codingBoy on 16/4/24.
 */
public class CircleImageview extends ImageView {
    public CircleImageview(Context context) {
        super(context);
        setWillNotDraw(false);
        setBackgroundColor(Color.BLACK);
        setAlpha(0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {

        Paint paint_for_transparent = new Paint();
        paint_for_transparent.setAntiAlias(false);
        paint_for_transparent.setAntiAlias(true);
        paint_for_transparent.setStyle(Paint.Style.FILL_AND_STROKE);
        paint_for_transparent.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth()/5, paint_for_transparent);

    }
}
