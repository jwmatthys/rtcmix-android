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
    int sr = 22050;
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
		    int buffsize = AudioTrack.getMinBufferSize(sr,
							       AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
		    //int buffsize = 4000;
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
		    //float outbuf[] = new float[buffsize*2]; //this works but it's clicky
		    float outbuf[] = new float[16384]; //this works but it's clicky
		    short samples[] = new short[buffsize*2];
			for (int i=0; i<buffsize*2; i++)
				{
					inbuf[i]=0.f;
					samples[i]=(short)0;
				}
			for (int i=0; i<outbuf.length; i++)
			    outbuf[i]=0.f;

		    String errcode = "";
			MyLog.d("DroidMix", "calling pd_rtsetparams()");
		    rtcmix.pd_rtsetparams(sr,2,4096,inbuf,outbuf,errcode); // TODO: figure out stereo
		    // for some reason, the second wavetable call doesn't work
		    String testcode = "env=maketable(\"window\",1000,1); WAVETABLE(10,30,10000*env,800); WAVETABLE(0,20,10000*env,440); ";
			int codelen = testcode.length();
			MyLog.d("DroidMix", "testcode: "+testcode+"\nlength: "+codelen);
			if (rtcmix.parse_score(testcode,codelen) != 0)
				MyLog.d("DroidMix", "parse_score() failed");
			int amp = Short.MAX_VALUE;

		    // start audio
		    audioTrack.play();

		    int index = 0;

		    // synthesis loop
		    while(isRunning)
			{
			    if (toggleButton)
				{
				    for (int i=0; i < buffsize*2; i++)
					samples[(index+i)%(buffsize*2)] = (short) (amp*2*(freq.nextFloat()-0.5));
				    audioTrack.write(samples, index, buffsize);
				}
			    else
				{
				    outbuf = rtcmix.pullTraverse();
				    int s_counter = 0;
				    for (int j=0; j < outbuf.length; j++)
					{
					    samples[(index+j)%(buffsize*2)] = (short) (outbuf[j]*amp);
					    samples[(index+j)%(buffsize*2)] = (short) (outbuf[j]*amp);
					    s_counter++;
					    if (s_counter==(buffsize*2))
						{
						    audioTrack.write(samples, index, buffsize);
						    s_counter=0;
						}
					}
				    index = (index + s_counter) % (buffsize*2);
				    audioTrack.write(samples, index, s_counter);				    
				}
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
