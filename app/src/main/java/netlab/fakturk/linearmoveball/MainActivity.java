package netlab.fakturk.linearmoveball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    SmallCircleView smallCircleView;
    float currentY=100, updateY=10;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallCircleView = (SmallCircleView) findViewById(R.id.smallCircleView);
        float ydpi = getResources().getDisplayMetrics().ydpi; //1 inch
        final float yCm = ydpi/2.54f;


        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                // Update logic here
                currentY=smallCircleView.getY();
                if (currentY>yCm*5+100)
                {
                    updateY=-10;
                }
                if (currentY<100)
                {
                    updateY=10;
                }



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Drawing logic here
                        smallCircleView.setYMove(updateY);

                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(myTimerTask, 10, 10);

    }



}
