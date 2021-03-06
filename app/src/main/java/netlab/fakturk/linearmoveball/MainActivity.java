package netlab.fakturk.linearmoveball;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    SmallCircleView smallCircleView;
    float currentY=100,currentX=100, updateY=5,updateX = 5;
    float[] acc;
    String accMesaage;
    boolean isVertical = false;
    boolean isMoving = true;
    int length=10;
    int offset = 400;
    int acceleration = 1;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//       isMoving = false;
//        System.out.println("isMoving : "+ isMoving);
//        return true;
//    }

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startService(new Intent(MainActivity.this, SensorService.class));

        acc = new float[3];

        smallCircleView = (SmallCircleView) findViewById(R.id.smallCircleView);
        smallCircleView.setVertical(isVertical);
        smallCircleView.setMoving(isMoving);
        smallCircleView.setLength(10);

        smallCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMoving = !isMoving;
                System.out.println("isMoving : "+ isMoving);
            }
        });

        float ydpi = getResources().getDisplayMetrics().ydpi; //1 inch
        final float yCm = ydpi/2.54f;
        float xdpi = getResources().getDisplayMetrics().ydpi; //1 inch
        final float xCm = xdpi/2.54f;

//        Log.d("test","deneme");
//        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver()
//        {
//            @Override
//            public void onReceive(Context context, Intent intent)
//            {
//               acc= intent.getFloatArrayExtra("ACC_DATA");
//                if (acc!=null)
//                {
//                    accMesaage = intent.getLongExtra("TIME",0)+" "+acc[0]+" "+acc[1]+" "+acc[2];
//                    Log.d("acc ", accMesaage);
//
//                }
//
//            }
//        }, new IntentFilter(SensorService.ACTION_SENSOR_BROADCAST));



        System.out.println("isMoving (before timertask): "+ isMoving);


        if (isMoving)
        {
            TimerTask myTimerTask = new TimerTask() {
                @Override
                public void run() {
                    // Update logic here

                    if (isVertical)
                    {
                        currentY=smallCircleView.getY();
                        if (currentY>yCm*length+offset)
                        {
                            updateY=-5;
                        }
                        if (currentY<offset)
                        {
                            updateY=5;
                        }
                    }
                    else
                    {
                        currentX=smallCircleView.getX();
                        if (currentX>xCm*length+offset)
                        {
                            updateX=-5;
                        }
                        if (currentX<offset)
                        {
                            updateX=5;
                        }
                    }




                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Drawing logic here
                            if (isVertical)
                            {
                                smallCircleView.setYMove(updateY);
                            }
                            else
                            {
                                smallCircleView.setXMove(updateX);
                            }



                        }
                    });
                }
            };
            Timer timer = new Timer();
            timer.schedule(myTimerTask, 10, 10);
        }



    }
    @Override
    protected void onDestroy()
    {

        super.onDestroy();
        stopService(new Intent(this,SensorService.class));
    }
    @Override
    protected void onPause() {

        super.onPause();
        stopService(new Intent(this,SensorService.class));


    }

    @Override
    protected void onResume() {

        super.onResume();
        startService(new Intent(this, SensorService.class));
    }



}
