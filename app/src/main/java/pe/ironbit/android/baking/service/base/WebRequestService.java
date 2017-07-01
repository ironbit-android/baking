package pe.ironbit.android.baking.service.base;

import java.util.List;

/**
 * Interface that perform the web request service.
 */
public interface WebRequestService {
    /**
     * Perform the web request service.
     *
     * @param url web resource.
     * @return collection of data.
     */
    List makeWebRequest(String url);
}
