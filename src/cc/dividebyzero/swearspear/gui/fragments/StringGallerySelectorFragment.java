package cc.dividebyzero.swearspear.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import cc.dividebyzero.swearspear.R;

public class StringGallerySelectorFragment extends StringSelectorFragment {
    
    private static final String LOG_TAG = StringGallerySelectorFragment.class.getSimpleName();
    private View                content;
    private Gallery             mGallery;
    
    private int                 mArrayResourceId;
    private String[]            mDataSource;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        if (container == null) { return null; }
        
        content = inflater.inflate(R.layout.control_panel_fmt, null);
        mGallery = (Gallery) content.findViewById(R.id.gallery1);
        
        return content;
    }
    
    private void updateAdapter() {
        if (mGallery != null)
        {
            mDataSource = getResources().getStringArray(mArrayResourceId);
            ArrayAdapter<String> aas = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.id.text1, mDataSource);
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
    
    public void setArrayResourceId(int mArrayResourceId) {
        this.mArrayResourceId = mArrayResourceId;
        updateAdapter();
    }
    
    public int getArrayResourceId() {
        return mArrayResourceId;
    }
    
}
