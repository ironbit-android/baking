package pe.ironbit.android.baking.loader.callback;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import java.util.List;

import pe.ironbit.android.baking.listener.base.BaseRecipeListener;
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
    private BaseRecipeListener listener;

    /**
     * Unique constructor.
     *
     * @param context  Activity context.
     * @param factory  Factory for web request.
     * @param listener Delegate listener.
     */
    public WebRequestLoaderCallback(Context context, WebRequestFactory factory, BaseRecipeListener listener) {
        this.context = context;
        this.factory = factory;
        this.listener = listener;
    }

    @Override
    public Loader<List> onCreateLoader(int id, Bundle args) {
        return factory.createLoaderTask();
    }

    @Override
    public void onLoadFinished(Loader<List> loader, List data) {
        listener.onRecipeListListener(data);
    }

    @Override
    public void onLoaderReset(Loader<List> loader) {
        listener.onRecipeListListener(null);
    }
}
