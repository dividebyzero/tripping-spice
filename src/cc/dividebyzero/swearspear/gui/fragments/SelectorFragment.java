package cc.dividebyzero.swearspear.gui.fragments;

import android.support.v4.app.Fragment;


public abstract class SelectorFragment<T> extends Fragment {
    
    public interface EventListener<T>{
        public void onItemSelected(final int selectorId,T item);
    }
    
    protected int                 mArrayResourceId;
    
    public abstract void makeRandomSelection();
    
    public abstract T getCurrentSelection();
 
    protected abstract void updateAdapter();
    
    public void setArrayResourceId(int mArrayResourceId) {
        this.mArrayResourceId = mArrayResourceId;
        updateAdapter();
    }
    

    public int getArrayResourceId() {
        return mArrayResourceId;
    }
}
