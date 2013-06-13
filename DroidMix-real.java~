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

// Let's walk before we run. Start with Android SDK audio to see if it works
// Then later we can switch to OpenSLES
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class DroidMix extends Activity
{
    TextView outputText = null;
    ScrollView scroller = null;
    Thread t;
    boolean isRunning = true;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        outputText = (TextView)findViewById(R.id.OutputText);
        outputText.setText("Press 'Run' to start...\n");
        outputText.setMovementMethod(new ScrollingMovementMethod());

        scroller = (ScrollView)findViewById(R.id.Scroller);
    }

    t = new Thread()
	{
	    public void run()
	    {
		// set process priority
		    setPriority(Thread.MAX_PRIORITY);

		    int err = rtcmix.rtcmixmain();
		    outputText.append("The result of running rtcmixmain(): " + err + "\n");

		    // set the buffer size
		    int buffsize = AudioTrack.getMinBufferSize(sr,
							       AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);

		    int sample_rate = 22050;
		    float[] inbuf = new float[buffsize];
		    float[] outbuf = new float[buffsize];
		    for (int i=0; i<buffsize; i++)
			{
			    inbuf[i] = (float)0.0;
			    outbuf[i] = (float)0.0;
			}
		    String rtcmix_error = "";
		    rtcmix.pd_rtsetparams(sample_rate,1,buffsize,inbuf,outbuf,rtcmix_error);

		    // create an audiotrack object
		    AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
							   sr, AudioFormat.CHANNEL_OUT_MONO,
							   AudioFormat.ENCODING_PCM_16BIT, buffsize,
							   AudioTrack.MODE_STREAM);
		    
		    short samples[] = new short[buffsize];
		    int amp = 10000;
		    double twopi = 8.*Math.atan(1.);
		    double fr = 440.f;
		    double ph = 0.0;
		    
		    // start audio
		    audioTrack.play();
		    
		    // synthesis loop
		    while(isRunning)
			{
			    fr =  440 + 440*sliderval;
			    for(int i=0; i < buffsize; i++)
				{
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

    public void onRunButtonClick(View view)
    {
      outputText.append("Started...\n");
      initAudio();
      isRunning = true;
      outputText.append("Finished!\n");
      
      // Ensure scroll to end of text
      scroller.post(new Runnable() {
        public void run() {
          scroller.fullScroll(ScrollView.FOCUS_DOWN);
        }
      });
    }

    /** Calls into C/C++ code */
/** Calls into C/C++ code */
    public void initAudio()
    {
	//outputText.append("Errors from running pd_rtsetparams(): " + rtcmix_error + "\n");
    }

    /** static constructor */
    static {
        System.loadLibrary("rtcmix");
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
}
