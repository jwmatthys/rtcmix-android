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
    Button startSound, endSound, sampleSound;
    boolean isRunning = false;
    boolean scorefileLoaded = false;
    final String testcode = "env=maketable(\"window\",1000,1); for (i=0; i<120; i+=1) { WAVETABLE(i*0.5,2,15000*env,110*irand(2,7),random())}";
    final int codelen = testcode.length();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	startSound = (Button) this.findViewById(R.id.StartSound);
	endSound = (Button) this.findViewById(R.id.EndSound);
	sampleSound = (Button) this.findViewById(R.id.SampleSound);
	startSound.setOnClickListener(this);
	endSound.setOnClickListener(this);
	sampleSound.setOnClickListener(this);
	sampleSound.setEnabled(false);
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
		sampleSound.setEnabled(true);
		startSound.setEnabled(false);
	    }
	else if (v == endSound)
	    {
		isRunning = false;
		endSound.setEnabled(false);
		sampleSound.setEnabled(false);
		startSound.setEnabled(true);
	    }
	else if (v == sampleSound)
	    {
		if (rtcmix.parse_score(testcode,codelen) == 0)
		    {
			scorefileLoaded = true;
			Toast.makeText(getApplicationContext(),
				       "RTcmix sample score loaded", Toast.LENGTH_SHORT).show();
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
	    final int RTCMIX_BUFSIZE = 1024;
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
	    float inbuf[] = new float[RTCMIX_BUFSIZE<<1];
	    float outbuf[] = new float[RTCMIX_BUFSIZE<<1];
	    short samples[] = new short[buffsize];
	    short silence[] = new short[buffsize];
	    for (int i=0; i<outbuf.length; i++)
		{
		    inbuf[i]=0.f;
		    outbuf[i]=0.f;
		    //samples[i]=(short)0;
		}
	    // Hey, why keep rewriting zeros to make silence?
	    for (int i=0; i<buffsize; i++)
		silence[i]=(short)0;

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
	    rtcmix.pd_rtsetparams(SAMPLE_RATE,2,RTCMIX_BUFSIZE,inbuf,outbuf,errcode);
	    MyLog.d("DroidMix", "testcode: "+testcode+"\nlength: "+codelen);
	    
	    // start audio
	    audioTrack.play();

	    final int OUTBUFSIZE = outbuf.length;

	    // synthesis loop
	    while(isRunning)
		{
		    if (scorefileLoaded)
			{
			    for (int i=0; i < buffsize; i++)
				{
				    if (i % OUTBUFSIZE == 0)
					outbuf = rtcmix.pullTraverse();
				    samples[i] = (short) (outbuf[i % OUTBUFSIZE]*MAX_AMP);
				}
			    audioTrack.write(samples, 0, buffsize);
			}
		    else
			audioTrack.write(silence, 0, buffsize);
		}
	    return null;
	}
    }

    public void onDestroy()
    {
	if (audio != null && audio.getStatus() != AsyncTask.Status.FINISHED)
            audio.cancel(true);
	isRunning = false;
        super.onDestroy();
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
