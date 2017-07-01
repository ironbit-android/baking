package pe.ironbit.android.baking.loader.task;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import pe.ironbit.android.baking.service.base.WebRequestService;

/**
 * It extends from AsyncLoaderTask.
 * It is used to retrieve information from internet.
 */
public class WebRequestLoaderTask extends AsyncTaskLoader<List> {
    /**
     * Url where the information will be retrieved.
     */
    private String url;

    /**
     * Service that execute the task.
     * Used constructor dependency injection.
     */
    private WebRequestService service;

    /**
     * Unique constructor.
     *
     * @param context activity context.
     * @param url     {@see #url}
     * @param service {@see #service}
     */
    public WebRequestLoaderTask(Context context, String url, WebRequestService service) {
        super(context);

        this.url = url;
        this.service = service;
    }

    @Override
    public List loadInBackground() {
        return service.makeWebRequest(url);
    }
}
