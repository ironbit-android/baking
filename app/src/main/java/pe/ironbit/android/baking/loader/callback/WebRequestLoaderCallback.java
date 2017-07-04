package pe.ironbit.android.baking.loader.callback;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import java.util.List;

import pe.ironbit.android.baking.event.base.BaseObserver;
import pe.ironbit.android.baking.loader.factory.WebRequestFactory;

/**
 * Loader callback for web request.
 */
public class WebRequestLoaderCallback implements LoaderManager.LoaderCallbacks<List> {
    /**
     * Activity context.
     */
    private Context context;

    /**
     * Factory for web request.
     */
    private WebRequestFactory factory;

    /**
     * Delegate object.
     */
    private BaseObserver observer;

    /**
     * Unique constructor.
     *
     * @param context  Activity context.
     * @param factory  Factory for web request.
     * @param observer Observer object.
     */
    public WebRequestLoaderCallback(Context context, WebRequestFactory factory, BaseObserver observer) {
        this.context = context;
        this.factory = factory;
        this.observer = observer;
    }

    @Override
    public Loader<List> onCreateLoader(int id, Bundle args) {
        return factory.createLoaderTask();
    }

    @Override
    public void onLoadFinished(Loader<List> loader, List data) {
        //listener.onRecipeListListener(data);
        observer.notifyListener(data);
    }

    @Override
    public void onLoaderReset(Loader<List> loader) {
        //listener.onRecipeListListener(null);
        observer.notifyListener(null);
    }
}
