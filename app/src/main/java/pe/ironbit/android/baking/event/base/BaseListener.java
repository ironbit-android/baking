package pe.ironbit.android.baking.event.base;

/**
 * Used to populate data to adaptors.
 * Observers update data to listener.
 */
public interface BaseListener {
    void update(Object object);
}
