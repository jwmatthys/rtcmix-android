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
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import android.app.Dialog;
import android.os.AsyncTask;
//import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.text.method.ScrollingMovementMethod;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.widget.Toast;
//import java.util.Random;
import ar.com.daidalos.afiledialog.*;

public class DroidMix extends Activity implements OnClickListener, OnSeekBarChangeListener, OnItemSelectedListener
{
    AudioSynthesisTask audio;
    //private Handler mHandler = new Handler();
    TextView outputText = null;
    ScrollView scroller = null;
    private Button startSound, sampleSound, loadSound, flushSound;
    private SeekBar pfSeekBar;
    Spinner sampleRateSpinner;
    boolean isRunning = false;
    boolean flushFlag = false;
    boolean scorefileLoaded = false;
    int chosen_samplerate = 8000;
    final String testcode = "env=maketable(\"window\",1000,1); for (i=0; i<120; i+=1) { WAVETABLE(i*0.5,2,15000*env,110*irand(2,7),random())}";
    final int codelen = testcode.length();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	startSound = (Button) this.findViewById(R.id.StartSound);
	sampleRateSpinner = (Spinner) findViewById(R.id.PickSR);
	sampleSound = (Button) this.findViewById(R.id.SampleSound);
	loadSound = (Button) this.findViewById(R.id.LoadSound);
	flushSound = (Button) this.findViewById(R.id.FlushSound);
	pfSeekBar = (SeekBar) findViewById(R.id.PField);
	pfSeekBar.setOnSeekBarChangeListener(this);
	startSound.setOnClickListener(this);
	sampleSound.setOnClickListener(this);
	loadSound.setOnClickListener(this);
	flushSound.setOnClickListener(this);
	sampleRateSpinner.setSelection(1);
	sampleRateSpinner.setOnItemSelectedListener(this);
	sampleSound.setEnabled(false);
	loadSound.setEnabled(false);
	flushSound.setEnabled(false);

        outputText = (TextView)findViewById(R.id.OutputText);
        //outputText.setText("Press Start to initialize sound.\n");
        outputText.setMovementMethod(new ScrollingMovementMethod());	
        scroller = (ScrollView)findViewById(R.id.Scroller);
    }

    @Override
    public void onBackPressed() {
	android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onPause()
    {
	super.onPause();
	isRunning = false;
    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
	rtcmix.pfield_set(1,progress*5+200);
    }
    
    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        //Toast.makeText(DroidMix.this, "Started Tracking Seekbar", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        //pfSeekBar.setSecondaryProgress(seekBar.getProgress());
        //Toast.makeText(DroidMix.this, "Stopped Tracking Seekbar", Toast.LENGTH_SHORT).show();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
	{
	    try {
		chosen_samplerate = Integer.parseInt(parent.getItemAtPosition(pos).toString());
	    } catch (NumberFormatException nfe)
		{}
	}		
    }

    public void onNothingSelected(AdapterView<?> parent)
    {
	// A thing is here that does a thing, or no thing.
    }

    public void onClick (View v)
    {
	if (v == startSound)
	    {
		audio = new AudioSynthesisTask();
		audio.execute();
		startSound.setEnabled(false);
		sampleRateSpinner.setEnabled(false);
		sampleSound.setEnabled(true);
		loadSound.setEnabled(true);
		flushSound.setEnabled(true);
	    }	

       	if (v == sampleSound)
	    {
		if (rtcmix.parse_score(testcode,codelen) == 0)
		    {
			scorefileLoaded = true;
			Toast.makeText(getApplicationContext(),
				       "RTcmix sample score loaded", Toast.LENGTH_SHORT).show();
		    }
	    }
	else if (v == loadSound)
	    {
    		FileChooserDialog dialog = new FileChooserDialog(DroidMix.this);
		
    		// Assign listener for the select event.
    		dialog.addListener(DroidMix.this.onFileSelectedListener);
		
    		// Define the filter for select images.
    		dialog.setFilter(".*sco|.*SCO");
    		dialog.setShowOnlySelectable(false);
    		
    		// Show the dialog.
		dialog.show();
	
	    }
	else if (v == flushSound)
	    {
		flushFlag = true;
		Toast.makeText(getApplicationContext(),
			       "Flushing all audio buffers", Toast.LENGTH_SHORT).show();
	    }
    }
    
    private void openRTcmixFile(File score)
    {
	String rtcmixScore = "";
	try {
	    FileInputStream fis = new FileInputStream(score);
	    StringBuilder inputStringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
	    String line = bufferedReader.readLine();
	    while(line != null){
		inputStringBuilder.append(line);inputStringBuilder.append('\n');
		line = bufferedReader.readLine();
	    }
	    rtcmixScore = inputStringBuilder.toString();
	}
	catch (FileNotFoundException fnfe)
	    {
		MyLog.d("DroidMix", fnfe.getMessage());
	    }
	catch (IOException ioe)
	    {
		MyLog.d("DroidMix", ioe.getMessage());
	    }
	
	if (rtcmix.parse_score(rtcmixScore,rtcmixScore.length()) == 0)
	    {
		scorefileLoaded = true;
		outputText.setText(rtcmixScore);
	    }
        scroller = (ScrollView)findViewById(R.id.Scroller);
    }

    public static String convertStreamToString(InputStream is) throws Exception {
	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	StringBuilder sb = new StringBuilder();
	String line = null;
	while ((line = reader.readLine()) != null) {
	    sb.append(line).append("\n");
	}
	return sb.toString();
    }
    
    private FileChooserDialog.OnFileSelectedListener onFileSelectedListener = new FileChooserDialog.OnFileSelectedListener() 
	{
	    public void onFileSelected(Dialog source, File file) {
		source.hide();
		Toast toast = Toast.makeText(DroidMix.this, "File selected: " + file.getName(), Toast.LENGTH_LONG);
		toast.show();
		openRTcmixFile(file);
	    }
	    public void onFileSelected(Dialog source, File folder, String name) {
		source.hide();
		Toast toast = Toast.makeText(DroidMix.this, "File created: " + folder.getName() + "/" + name, Toast.LENGTH_LONG);
		toast.show();
	    }
	};
    
    private class AudioSynthesisTask extends AsyncTask <Void, Void, Boolean>
    {
	@Override
	    protected Boolean doInBackground(Void... params)
	{
	    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	    final int SAMPLE_RATE = chosen_samplerate;
	    final int RTCMIX_BUFSIZE = 1024;
	    int temp_buffsize = AudioTrack.getMinBufferSize(SAMPLE_RATE,
						       AudioFormat.CHANNEL_OUT_STEREO,
						       AudioFormat.ENCODING_PCM_16BIT);
	    int buffsize = 1;
	    while (buffsize < temp_buffsize) buffsize = buffsize << 1;
	    final String buffmsg = "RTcmix started. Sample rate: "+SAMPLE_RATE+", Buffer size: "+buffsize;
	    runOnUiThread(new Runnable() {
		    public void run() {
			
			Toast.makeText(DroidMix.this, buffmsg, Toast.LENGTH_LONG).show();
		    }
		});
	    buffsize = buffsize << 1; // might as well double it now
	    final int MAX_AMP = Short.MAX_VALUE;
	    float inbuf[] = new float[RTCMIX_BUFSIZE<<1];
	    float outbuf[] = new float[RTCMIX_BUFSIZE<<1];
	    float foobuf[] = new float[RTCMIX_BUFSIZE<<1];
	    float barbuf[] = new float[RTCMIX_BUFSIZE<<1];
	    short samples[] = new short[buffsize];
	    short silence[] = new short[buffsize];
	    for (int i=0; i<outbuf.length; i++)
		{
		    inbuf[i]=0.f;
		    outbuf[i]=0.f;
		    foobuf[i]=0.f;
		    barbuf[i]=0.f;
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
	    // No pass-by-pointer in Java (JAVA!!) so no need to specify buffers here
	    // (Changed in JNI source in mm_rtsetparams.cpp)
	    rtcmix.droid_rtsetparams(SAMPLE_RATE,2,RTCMIX_BUFSIZE);
	    MyLog.d("DroidMix", "testcode: "+testcode+"\nlength: "+codelen);
	    
	    // start audio
	    audioTrack.play();

	    final int OUTBUFSIZE = outbuf.length;
	    
	    isRunning = true;

	    // synthesis loop
	    while(isRunning)
		{
		    if (scorefileLoaded)
			{
			    for (int i=0; i < buffsize; i++)
				{
				    if (i % OUTBUFSIZE == 0)
					{
					    outbuf = rtcmix.pullTraverse(inbuf);
					    if (flushFlag)
						{
						    rtcmix.flush_sched();
						    flushFlag = false;
						    scorefileLoaded = false;
						}
					}
				    samples[i] = (short) (outbuf[i % OUTBUFSIZE]*MAX_AMP);
				}
			    audioTrack.write(samples, 0, buffsize);
			}
		    else
			audioTrack.write(silence, 0, buffsize);
		}
	    inbuf = null;
	    outbuf = null;
	    silence = null;
	    samples = null;
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
