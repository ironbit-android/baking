package pe.ironbit.android.baking.loader.factory;

import android.content.Context;
import android.content.Loader;

import java.util.List;

import pe.ironbit.android.baking.loader.task.WebRequestLoaderTask;
import pe.ironbit.android.baking.service.base.WebRequestService;

public class WebRequestFactoryRecipeList implements WebRequestFactory {
    private Context context;

    private String url;

    private WebRequestService service;

    public WebRequestFactoryRecipeList(Context context, String url, WebRequestService service) {
        this.context = context;
        this.url = url;
        this.service = service;
    }

    @Override
    public Loader<List> createLoaderTask() {
        return new WebRequestLoaderTask(context, url, service);
    }
}
