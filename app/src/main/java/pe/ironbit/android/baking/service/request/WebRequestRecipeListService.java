package pe.ironbit.android.baking.service.request;

import java.util.List;

import pe.ironbit.android.baking.data.base.DataParser;
import pe.ironbit.android.baking.service.base.WebRequestHelper;
import pe.ironbit.android.baking.service.base.WebRequestService;

public class WebRequestRecipeListService implements WebRequestService {
    /**
     * Parser that populate model object with information retrieved from internet.
     */
    private DataParser parser;

    /**
     * Unique constructor.
     * Parser (dependency injection).
     * @param parser {@see #parser}
     */
    public WebRequestRecipeListService(DataParser parser) {
        this.parser = parser;
    }

    @Override
    public List makeWebRequest(String url) {
        return WebRequestHelper.makeWebRequest(url, parser);
    }
}
