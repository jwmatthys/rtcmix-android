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
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ScrollView;
import android.text.method.ScrollingMovementMethod;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.widget.Toast;
import java.util.Random;

public class DroidMix extends Activity implements OnClickListener
{
    AudioSynthesisTask audio;
    TextView outputText = null;
    ScrollView scroller = null;
    Button startSound, endSound, toggleSound;
    boolean isRunning = false;
    boolean toggleButton = true;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	startSound = (Button) this.findViewById(R.id.StartSound);
	endSound = (Button) this.findViewById(R.id.EndSound);
	toggleSound = (Button) this.findViewById(R.id.ToggleSound);
	startSound.setOnClickListener(this);
	endSound.setOnClickListener(this);
	toggleSound.setOnClickListener(this);
	toggleSound.setEnabled(false);
	endSound.setEnabled(false);

        outputText = (TextView)findViewById(R.id.OutputText);
        outputText.setText("Press Start to initialize sound.\n");
        outputText.setMovementMethod(new ScrollingMovementMethod());	
        scroller = (ScrollView)findViewById(R.id.Scroller);
    }

    @Override
    public void onPause()
    {
	super.onPause();
	isRunning = false;

	endSound.setEnabled(false);
	startSound.setEnabled(true);
    }

    public void onClick (View v)
    {
	if (v == startSound)
	    {
		isRunning = true;
		audio = new AudioSynthesisTask();
		audio.execute();
		endSound.setEnabled(true);
		toggleSound.setEnabled(true);
		startSound.setEnabled(false);
	    }
	else if (v == endSound)
	    {
		isRunning = false;
		endSound.setEnabled(false);
		toggleSound.setEnabled(false);
		startSound.setEnabled(true);
	    }
	else if (v == toggleSound)
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
	    }
    }

    private class AudioSynthesisTask extends AsyncTask <Void, Void, Boolean>
    {
	@Override
	    protected Boolean doInBackground(Void... params)
	{
	    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	    Random freq = new Random();
	    final int SAMPLE_RATE = 22050;
	    int buffsize = AudioTrack.getMinBufferSize(SAMPLE_RATE,
						       AudioFormat.CHANNEL_OUT_STEREO,
						       AudioFormat.ENCODING_PCM_16BIT);
	    final String buffmsg = "buffsize: "+buffsize;
	    runOnUiThread(new Runnable() {
		    public void run() {
			
			Toast.makeText(DroidMix.this, buffmsg, Toast.LENGTH_SHORT).show();
		    }
		});
	    buffsize = buffsize << 1; // might as well double it now
	    final int MAX_AMP = Short.MAX_VALUE;
	    float inbuf[] = new float[buffsize];
	    float outbuf[] = new float[buffsize];
	    short samples[] = new short[buffsize];
	    for (int i=0; i<buffsize; i++)
		{
		    inbuf[i]=0.f;
		    outbuf[i]=0.f;
		    samples[i]=(short)0;
		}
	    String errcode = "";
	    
	    AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
						   SAMPLE_RATE,
						   AudioFormat.CHANNEL_OUT_STEREO,
						   AudioFormat.ENCODING_PCM_16BIT,
						   buffsize,
						   AudioTrack.MODE_STREAM);

	    // Initialize RTcmix
	    if (rtcmix.rtcmixmain() != 0)
		MyLog.d("DroidMix", "rtcmixmain() failed to load");
	    rtcmix.pd_rtsetparams(SAMPLE_RATE,2,buffsize>>1,inbuf,outbuf,errcode); // TODO: figure out stereo
	    String testcode = "env=maketable(\"window\",1000,1); pan=makeLFO(\"sine\",1,0,1); lfo=makeLFO(\"square\",5,-100,100); WAVETABLE(0,60,10000*env,800+lfo,pan); WAVETABLE(1,60,20000, 333, 0.5)";
	    int codelen = testcode.length();
	    MyLog.d("DroidMix", "testcode: "+testcode+"\nlength: "+codelen);
	    if (rtcmix.parse_score(testcode,codelen) != 0)
		MyLog.d("DroidMix", "parse_score() failed");
	    
	    // start audio
	    audioTrack.play();

	    // synthesis loop
	    while(isRunning)
		{
		    if (toggleButton)
			{
			    for (int i=0; i < buffsize; i++)
				samples[i] = (short) (MAX_AMP*2*(freq.nextFloat()-0.5));
			    audioTrack.write(samples, 0, buffsize);
			}
		    else
			{
			    outbuf = rtcmix.pullTraverse();
			    for (int i=0; i < buffsize; i++)
				{
				    samples[i] = (short) (outbuf[i]*MAX_AMP);
				}
			    audioTrack.write(samples, 0,buffsize);
			}
		}
	    audioTrack.stop();
	    audioTrack.release();
	    return null;
	}
    }

    public void onDestroy()
    {
	super.onDestroy();
	isRunning = false;
    }
    
    /** static constructor */
    static
    {
        System.loadLibrary("rtcmix");
    }
}

/*
    public void onRunButtonClick(View view)
    {
	toggleButton = !toggleButton;
	
	// Ensure scroll to end of text
	scroller.post(new Runnable() {
		public void run() {
		    scroller.fullScroll(ScrollView.FOCUS_DOWN);
		}
	    });
    }
        
}
*/
