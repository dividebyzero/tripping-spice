package cc.dividebyzero.swearspear.gui.fragments;


public abstract class StringSelectorFragment extends SelectorFragment<String> {
    
    public interface StringEventListener extends EventListener<String>{
        public void onItemSelected(final int selectorId,String item);
    }
    
    protected StringEventListener mEventListener; 
    
    @Override
    public abstract void makeRandomSelection();
    
    @Override
    public abstract String getCurrentSelection();

    public void setEventListener(StringEventListener mEventListener) {
        this.mEventListener = mEventListener;
    }

    public StringEventListener getEventListener() {
        return mEventListener;
    }
    
}
