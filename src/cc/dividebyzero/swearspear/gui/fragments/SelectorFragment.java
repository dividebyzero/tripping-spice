package cc.dividebyzero.swearspear.gui.fragments;

import android.support.v4.app.Fragment;


public abstract class SelectorFragment<T> extends Fragment {
    
    public interface EventListener<T>{
        public void onItemSelected(T item);
    }
    
    public abstract void makeRandomSelection();
    
    public abstract T getCurrentSelection();
    
}
