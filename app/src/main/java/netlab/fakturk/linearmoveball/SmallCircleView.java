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

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    boolean isVertical = true;
    int offset = 400;


    public boolean isMoving()
    {

        return isMoving;
    }

    public void setMoving(boolean moving)
    {

        isMoving = moving;
    }

    boolean isMoving = true;



    float theta = 0;



    float y=this.getHeight()/2;
    float x = this.getWidth()/2;

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }



    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    int length = 5;

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
        int x  ;
        int y ;
        if (isVertical)
        {
            x = this.getWidth()/2;
            y = (int) this.y;

        }
        else
        {
            x = (int) this.x;
            y = this.getHeight()/2;
        }
        if (!isMoving)
        {
            x = this.getWidth()/2;
            y = this.getHeight()/2;
        }

        //I added this code for not moving case, dont forget to remove this part if you want to move the ball again


        canvas.drawCircle(x,y,50,paint);
        canvas.drawLine(x-50,y,x+50,y,paint);
        canvas.drawLine(x,y-50,x,y+50,paint);

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi; //1 inch
        float yCm = ydpi/2.54f;
        float xCm = xdpi/2.54f;

        if (isVertical)
        {
            //for vertical line
            canvas.drawLine(x, offset,x,yCm*length+offset,paintText);

            for (int i = 0; i <= length; i++)
            {
                canvas.drawLine(x-10,offset+yCm*i,x+10,offset+yCm*i,paintText);
                canvas.drawText(String.valueOf(i),x+20,offset+yCm*i,paintText);
            }


        }
        else
        {
            //for horizaontal line
            canvas.drawLine(offset, y,xCm*length+offset,y,paintText);
            for (int i = 0; i <= length; i++)
            {
                canvas.drawLine(offset+xCm*i,y-10,offset+xCm*i,y+10,paintText);
                canvas.drawText(String.valueOf(i),offset +xCm*i-10,y-30,paintText);
            }

        }




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
    void setXMove(float yMove)
    {
        this.x+=yMove;
        this.invalidate();
    }

}
