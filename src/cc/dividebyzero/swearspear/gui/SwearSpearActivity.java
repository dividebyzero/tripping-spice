package cc.dividebyzero.swearspear.gui;

import java.util.Locale;

import cc.dividebyzero.swearspear.R;
import cc.dividebyzero.swearspear.R.layout;
import cc.dividebyzero.swearspear.gui.fragments.ControlPanelFragment;
import cc.dividebyzero.swearspear.gui.fragments.ControlPanelFragment.CPFCallback;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class SwearSpearActivity extends FragmentActivity implements CPFCallback, OnInitListener {
    
    private static final String  LOG_TAG          = SwearSpearActivity.class.getSimpleName();
    
    private static final int     MSG_DO_RANDOMIZE = 10;
    private static final int     MSG_DO_SPEAK     = 20;
    
    private Handler              mHandler         = new Handler() {
                                                      
                                                      @Override
                                                      public void handleMessage(Message msg) {
                                                          super.handleMessage(msg);
                                                          final int what = msg.what;
                                                          
                                                          if (what == MSG_DO_RANDOMIZE)
                                                          {
                                                              
                                                          } else if (what == MSG_DO_SPEAK)
                                                  {
                                                      speakInsult();
                                                  }
                                              }
                                                      
                                                  };
    
    private ControlPanelFragment mControlPanel    = null;
    private TextToSpeech         mTts;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swearspear_acv);
        
        mTts = new TextToSpeech(
                        getApplicationContext(),
                        this // TextToSpeech.OnInitListener
        );
        
        mControlPanel = new ControlPanelFragment();
        mControlPanel.setCallback(this);
        
        // Execute a transaction, replacing any existing fragment
        // with this one inside the frame.
        android.support.v4.app.FragmentTransaction ft;
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.secondary_container, mControlPanel, "cfp1");
        ft.commit();
    }
    
    public void onRandomizePressed() {
        mHandler.sendMessage(mHandler.obtainMessage(MSG_DO_RANDOMIZE));
        
    }
    
    public void onSpeakPressed() {
        mHandler.sendMessage(mHandler.obtainMessage(MSG_DO_SPEAK));
        
    }
    
    private void speakInsult() {
        if (mControlPanel != null)
        {
            final String text = mControlPanel.getCurrentText();
            
            speechOutput(text);
        }
        
    }
    
    private void speechOutput(final String text) {
        if(mTts==null)
            return;
        mTts.speak(text,
                        TextToSpeech.QUEUE_FLUSH, // Drop all pending entries in the playback queue.
                        null
                        );
    }
    
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS)
        {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            int result = mTts.setLanguage(Locale.US);
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                // Lanuage data is missing or the language is not supported.
                Log.e(LOG_TAG, "Language is not available.");
            } else
            {
                // Check the documentation for other possible result codes.
                // For example, the language may be available for the locale,
                // but not for the specified country and variant.
                
            }
        } else
        {
            // Initialization failed.
            Log.e(LOG_TAG, "Could not initialize TextToSpeech.");
            Toast.makeText(getApplicationContext(), R.string.tts_error,Toast.LENGTH_LONG).show();
            if(mControlPanel!=null){
                mControlPanel.disableSpeak();
            }
            
        }
        
    }
    
}
