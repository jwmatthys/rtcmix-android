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

public class DroidMix extends Activity
{
    TextView outputText = null;
    ScrollView scroller = null;

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

    public void onRunButtonClick(View view)
    {
      outputText.append("Started...\n");
      initAudio();
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
	int err = rtcmix.rtcmixmain();
	outputText.append("The result of running rtcmixmain(): " + err + "\n");
	float[] inbuf = new float[1024];
	float[] outbuf = new float[1024];
	for (int i=0; i<1024; i++)
	    {
		inbuf[i] = (float)0.0;
		outbuf[i] = (float)0.0;
	    }
	String rtcmix_error = "";
	rtcmix.pd_rtsetparams(22050,2,1024,inbuf,outbuf,rtcmix_error);
	outputText.append("Errors from running pd_rtsetparams(): " + rtcmix_error + "\n");
    }

    /** static constructor */
    static {
        System.loadLibrary("rtcmix");
    }
}
