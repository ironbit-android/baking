package pe.ironbit.android.baking.loader.factory;

import android.content.Loader;

import java.util.List;

/**
 * Interface used to create a loader task.
 */
public interface WebRequestFactory {
    Loader<List> createLoaderTask();
}
