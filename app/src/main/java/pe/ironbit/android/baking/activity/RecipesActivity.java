package pe.ironbit.android.baking.activity;

import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.data.base.DataParser;
import pe.ironbit.android.baking.data.v1.DataParserV1;
import pe.ironbit.android.baking.listener.base.BaseRecipeListener;
import pe.ironbit.android.baking.listener.use.UseRecipeListener;
import pe.ironbit.android.baking.loader.callback.WebRequestLoaderCallback;
import pe.ironbit.android.baking.loader.contract.WebRequestLoaderContract;
import pe.ironbit.android.baking.loader.factory.WebRequestFactory;
import pe.ironbit.android.baking.loader.factory.WebRequestFactoryRecipeList;
import pe.ironbit.android.baking.service.base.WebRequestService;
import pe.ironbit.android.baking.service.request.WebRequestRecipeListService;

public class RecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // load information from internet
        initWebRequestLoader();
    }

    /**
     * Retrieve information from internet.
     * It requires string value (web_resource_recipe_list) as url resource.
     */
    private void initWebRequestLoader() {
        String url = getString(R.string.web_resource_recipe_list);

        DataParser parser = new DataParserV1();
        BaseRecipeListener listener = new UseRecipeListener();
        WebRequestService service = new WebRequestRecipeListService(parser);
        WebRequestFactory factory = new WebRequestFactoryRecipeList(this, url, service);
        LoaderManager.LoaderCallbacks<List> loader = new WebRequestLoaderCallback(this, factory, listener);

        getLoaderManager().initLoader(WebRequestLoaderContract.WEB_REQUEST_RECIPE_LOADER, null, loader).forceLoad();
    }
}
