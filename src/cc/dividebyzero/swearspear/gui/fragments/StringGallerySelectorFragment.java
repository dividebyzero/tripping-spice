package cc.dividebyzero.swearspear.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import cc.dividebyzero.swearspear.R;

public class StringGallerySelectorFragment extends StringSelectorFragment implements OnItemSelectedListener {
    
    private static final String LOG_TAG = StringGallerySelectorFragment.class.getSimpleName();
    private View                content;
    private Gallery             mGallery;
    
    
    private String[]            mDataSource;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        if (container == null) { return null; }
        
        content = inflater.inflate(R.layout.gallery_selector_fmt, null);
        mGallery = (Gallery) content.findViewById(R.id.gallery1);
        mGallery.setOnItemSelectedListener(this);
        
        updateAdapter();
        
        return content;
    }
    
    protected void updateAdapter() {
        if (mGallery != null)
        {
            mDataSource = getResources().getStringArray(mArrayResourceId);
            ArrayAdapter<String> aas = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.single_item_insult_view,R.id.textView1, mDataSource);
            mGallery.setAdapter(aas);
        } else
        {
            android.util.Log.e(LOG_TAG, "can't set adapter or data on null gallery");
        }
    }
    
    @Override
    public void makeRandomSelection() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public String getCurrentSelection() {
        // TODO Auto-generated method stub
        return null;
    }
    


    public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
        
        if(mEventListener!=null && mDataSource!=null){
            final String item= mDataSource[pos];
            mEventListener.onItemSelected(mArrayResourceId, item);
        }else{
            android.util.Log.e(LOG_TAG,"error, no data or no listener");
        }
        
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        
    }
    
}
