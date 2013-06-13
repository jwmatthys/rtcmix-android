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
    boolean toggleButton = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	t = new Thread()
	    {
		public void run()
		{
		    setPriority(Thread.MAX_PRIORITY);
		    int buffsize = AudioTrack.getMinBufferSize(sr,
							       AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
		    // create an audiotrack object
		    AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
							   sr, AudioFormat.CHANNEL_OUT_MONO,
							   AudioFormat.ENCODING_PCM_16BIT, buffsize,
							   AudioTrack.MODE_STREAM);
		    
		    short samples[] = new short[buffsize];
		    int amp = Short.MAX_VALUE;
		    double twopi = 8.*Math.atan(1.);
		    double fr = 440.f;
		    double ph = 0.0;

		    // start audio
		    audioTrack.play();

		    // synthesis loop
		    while(isRunning)
			{
			    fr =  440;
			    for(int i=0; i < buffsize; i++){
				if (toggleButton)
				    samples[i] = (short) (amp*2*(freq.nextFloat()-0.5));
				else
				    samples[i] = (short) (amp*Math.sin(ph));
				ph += twopi*fr/sr;
			    }
			    audioTrack.write(samples, 0, buffsize);
			}
		    audioTrack.stop();
		    audioTrack.release();
		}
	    };
	t.start();
	
        outputText = (TextView)findViewById(R.id.OutputText);
        outputText.setText("Press 'Run' to start...\n");
        outputText.setMovementMethod(new ScrollingMovementMethod());
	
        scroller = (ScrollView)findViewById(R.id.Scroller);
    }
    
    public void onRunButtonClick(View view)
    {
	toggleButton = !toggleButton;
	if (toggleButton)
	    {
		//outputText.append("Noise\n");
		Toast.makeText(getApplicationContext(),
			       "Noise", Toast.LENGTH_SHORT).show();
	    }
	else
	    {
		//outputText.append("Tone\n");
		Toast.makeText(getApplicationContext(),
			       "Tone", Toast.LENGTH_SHORT).show();
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
