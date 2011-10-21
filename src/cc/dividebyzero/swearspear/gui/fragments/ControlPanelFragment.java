package cc.dividebyzero.swearspear.gui.fragments;

import cc.dividebyzero.swearspear.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ControlPanelFragment extends Fragment {
    
    public interface CPFCallback {
        
        void onRandomizePressed();
        
        void onSpeakPressed();
        
    }
    
    private View        content;
    
    private EditText    mText;
    
    private CPFCallback mCallback;
    
    public void setCallback(CPFCallback callback) {
        this.mCallback = callback;
    }
    
    private View.OnClickListener buttonListener = new View.OnClickListener() {
                                                    
                                                    public void onClick(View v) {
                                                        final int id = v.getId();
                                                        
                                                        if (id == R.id.b_randomize)
                                                        {
                                                            sendRandomize();
                                                        } else if (id == R.id.b_speak)
                                                        {
                                                            sendSpeak();
                                                        }
                                                        
                                                    }
                                                };
    
    public String getCurrentText() {
        CharSequence etext=mText.getText();

        if(etext!=null){
            return etext.toString();
        }
        return null;
    }
    
    public void setCurrentText(final String text) {
        mText.setText(text);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        if (container == null) { return null; }
        
        content = inflater.inflate(R.layout.control_panel_fmt, null);
        
        mText=(EditText)content.findViewById(R.id.t_combined);
        
        content.findViewById(R.id.b_randomize).setOnClickListener(buttonListener);
        content.findViewById(R.id.b_speak).setOnClickListener(buttonListener);
        
        return content;
    }
    
    private void sendRandomize() {
        if (mCallback != null)
        {
            mCallback.onRandomizePressed();
        }
    }
    
    private void sendSpeak() {
        if (mCallback != null)
        {
            mCallback.onSpeakPressed();
        }
    }

    public void disableSpeak() {
        if(content!=null)
        {
            content.findViewById(R.id.b_speak).setEnabled(false);
        }
        
    }
    
}
