/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jwmatthys.rtcmix;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ScrollView;
import android.text.method.ScrollingMovementMethod;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.widget.Toast;
import java.util.Random;

public class DroidMix extends Activity
{
    Random freq = new Random();
    TextView outputText = null;
    ScrollView scroller = null;
    Thread t;
    int sr = 44100;
    boolean isRunning = true;
    boolean toggleButton = true;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        outputText = (TextView)findViewById(R.id.OutputText);
        outputText.setText("Press 'Run' to toggle noise or tone...\n");
        outputText.setMovementMethod(new ScrollingMovementMethod());	
        scroller = (ScrollView)findViewById(R.id.Scroller);

	t = new Thread()
	    {
		public void run()
		{
		    setPriority(Thread.MAX_PRIORITY);
		    //int buffsize = AudioTrack.getMinBufferSize(sr,
			//				       AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
			int buffsize = 4000;
		    // create an audiotrack object
		    AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
							   sr, AudioFormat.CHANNEL_OUT_STEREO,
							   AudioFormat.ENCODING_PCM_16BIT, buffsize,
							   AudioTrack.MODE_STREAM);
		    
		    // instantiate rtcmixmain()
			MyLog.d("DroidMix", "starting rtcmixmain()");
		    if (rtcmix.rtcmixmain() != 0)
				MyLog.d("DroidMix", "rtcmixmain() failed to load");
		    else
				MyLog.d("DroidMix", "rtcmixmain() loaded");
		    float inbuf[] = new float[buffsize*2]; // must be 2 because it's stereo
		    float outbuf[] = new float[buffsize*2];
		    short samples[] = new short[buffsize*2];
			for (int i=0; i<buffsize*2; i++)
				{
					inbuf[i]=0.f;
					outbuf[i]=0.023f;
					samples[i]=(short)0;
				}
		    String errcode = "";
			MyLog.d("DroidMix", "calling pd_rtsetparams()");
		    rtcmix.pd_rtsetparams(sr,1,buffsize,inbuf,outbuf,errcode); // TODO: figure out stereo
		    String testcode = "load(\"WAVETABLE\"); lfo=makeLFO(\"sine\",.5,-110,110); WAVETABLE(0,60,20000,440+lfo, 0.5)";
			int codelen = testcode.length();
			MyLog.d("DroidMix", "testcode: "+testcode+"\nlength: "+codelen);
			if (rtcmix.parse_score(testcode,codelen) != 0)
				MyLog.d("DroidMix", "parse_score() failed");
			int amp = Short.MAX_VALUE;

		    // start audio
		    audioTrack.play();

		    // synthesis loop
		    while(isRunning)
			{
				if (!toggleButton)
					{
						outbuf = rtcmix.pullTraverse();
						//MyLog.d("DroidMix", "low traverse values: "+outbuf[0]+", "+outbuf[1]+", "+outbuf[2]+", "+outbuf[3]+", "+outbuf[4]+", "+outbuf[5]+", "+outbuf[6]);
						//MyLog.d("DroidMix", "high traverse values: "+outbuf[buffsize]+", "+outbuf[buffsize+1]+", "+outbuf[buffsize+2]+", "+outbuf[buffsize+3]+", "+outbuf[buffsize+4]+", "+outbuf[buffsize+5]+", "+outbuf[buffsize+6]);
					}
				if (toggleButton)
					{
						for (int i=0; i < buffsize*2; i++)
							samples[i] = (short) (amp*2*(freq.nextFloat()-0.5));
					}
				else
					{
						for(int i=0; i < buffsize*2; i+=1)
							{
								samples[i] = (short) (outbuf[i]*amp);
							}
					}
			    audioTrack.write(samples, 0, buffsize);
			}
		    audioTrack.stop();
		    audioTrack.release();
		}
	    };
	t.start();
    }
    
    public void onRunButtonClick(View view)
    {
	toggleButton = !toggleButton;
	if (toggleButton)
	    {
		Toast.makeText(getApplicationContext(),
			       "Noise", Toast.LENGTH_SHORT).show();
	    }
	else
	    {
		Toast.makeText(getApplicationContext(),
			       "RTcmix score", Toast.LENGTH_SHORT).show();
	    }
	
	// Ensure scroll to end of text
	scroller.post(new Runnable() {
		public void run() {
		    scroller.fullScroll(ScrollView.FOCUS_DOWN);
		}
	    });
    }
        
    public void onDestroy()
    {
	super.onDestroy();
	isRunning = false;
	try {
            t.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	t = null;
    }
    
    /** static constructor */
    static {
        System.loadLibrary("rtcmix");
    }
}
