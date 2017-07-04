package pe.ironbit.android.baking.event.concrete;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.event.base.BaseListener;
import pe.ironbit.android.baking.event.base.BaseObserver;

/**
 * Implementation of BaseObserver.
 */
public class ConcreteObserver implements BaseObserver {
    List<BaseListener> list;

    public ConcreteObserver() {
        list = new ArrayList<BaseListener>();
    }

    @Override
    public void attach(BaseListener object) {
        list.add(object);
    }

    @Override
    public void detach(BaseListener object) {
        list.remove(object);
    }

    @Override
    public void notifyListener(Object object) {
        for (BaseListener listener : list) {
            listener.update(object);
        }
    }
}
