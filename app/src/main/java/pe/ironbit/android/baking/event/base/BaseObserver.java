package pe.ironbit.android.baking.event.base;

/**
 * Used to decouple adapters with loaders.
 * Update registered listener when loader data is retrieved.
 */
public interface BaseObserver {
    void attach(BaseListener object);

    void detach(BaseListener object);

    void notifyListener(Object object);
}
