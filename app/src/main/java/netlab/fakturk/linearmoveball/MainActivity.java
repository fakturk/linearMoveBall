package netlab.fakturk.linearmoveball;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    SmallCircleView smallCircleView;
    float currentY=100, updateY=5;
    float[] acc;
    String accMesaage;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, SensorService.class));

        acc = new float[3];

        smallCircleView = (SmallCircleView) findViewById(R.id.smallCircleView);
        float ydpi = getResources().getDisplayMetrics().ydpi; //1 inch
        final float yCm = ydpi/2.54f;

//        Log.d("test","deneme");
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
               acc= intent.getFloatArrayExtra("ACC_DATA");
                if (acc!=null)
                {
                    accMesaage = intent.getLongExtra("TIME",0)+" "+acc[0]+" "+acc[1]+" "+acc[2];
                    Log.d("acc ", accMesaage);

                }

            }
        }, new IntentFilter(SensorService.ACTION_SENSOR_BROADCAST));



//        TimerTask myTimerTask = new TimerTask() {
//            @Override
//            public void run() {
//                // Update logic here
//                currentY=smallCircleView.getY();
//                if (currentY>yCm*5+100)
//                {
//                    updateY=-5;
//                }
//                if (currentY<100)
//                {
//                    updateY=5;
//                }
//
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Drawing logic here
//                        smallCircleView.setYMove(updateY);
//
//                    }
//                });
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(myTimerTask, 10, 10);

    }



}
