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
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);



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
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
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
        int y = (int) this.y;
        canvas.drawCircle(x,y,50,paint);
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
