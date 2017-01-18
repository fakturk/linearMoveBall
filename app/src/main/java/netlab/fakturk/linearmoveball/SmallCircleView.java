package netlab.fakturk.linearmoveball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fakturk on 16. 12. 18.
 */

public class SmallCircleView extends View {
    Paint paint = new Paint();
    Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);



    float theta = 0;



    float y=100;

    public SmallCircleView(Context context) {
        super(context);
    }

    public SmallCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmallCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SmallCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paintText.setTextSize(40);

        int bigCircleRadius=0;
        if (this.getHeight()>this.getWidth())
        {
            bigCircleRadius = this.getWidth()/2-50;
        }
        else
        {
            bigCircleRadius = this.getHeight()/2-50;
        }
        int x = (int) this.getWidth()/2 ;
        int y = (int) this.getHeight()/2;
        canvas.drawCircle(x,y,50,paint);
        canvas.drawLine(x-50,y,x+50,y,paint);
        canvas.drawLine(x,y-50,x,y+50,paint);

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi; //1 inch
        float yCm = ydpi/2.54f;
//        canvas.drawLine(x, 100,x,yCm*5+100,paintText);

//        canvas.drawLine(x-10,100,x+10,100,paintText);
//        canvas.drawText("0",x+20,100,paintText);
//
//        canvas.drawLine(x-10,100+yCm*1,x+10,100+yCm*1,paintText);
//        canvas.drawText("1",x+20,100+yCm*1,paintText);
//
//        canvas.drawLine(x-10,100+yCm*2,x+10,100+yCm*2,paintText);
//        canvas.drawText("2",x+20,100+yCm*2,paintText);
//
//        canvas.drawLine(x-10,100+yCm*3,x+10,100+yCm*3,paintText);
//        canvas.drawText("3",x+20,100+yCm*3,paintText);
//
//        canvas.drawLine(x-10,100+yCm*4,x+10,100+yCm*4,paintText);
//        canvas.drawText("4",x+20,100+yCm*4,paintText);
//
//        canvas.drawLine(x-10,100+yCm*5,x+10,100+yCm*5,paintText);
//        canvas.drawText("5",x+20,100+yCm*5,paintText);
    }

    void setTheta(float theta)
    {
        this.theta = theta;
//        this.invalidate();
    }

    public float getTheta() {
        return theta;
    }
    @Override
    public float getY()
    {

        return y;
    }

    void setYMove(float yMove)
    {
        this.y+=yMove;
        this.invalidate();
    }

}
