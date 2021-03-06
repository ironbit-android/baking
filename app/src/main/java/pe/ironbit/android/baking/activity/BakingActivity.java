package pe.ironbit.android.baking.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.data.base.DataParser;
import pe.ironbit.android.baking.data.v1.DataParserV1;
import pe.ironbit.android.baking.event.concrete.ConcreteObserver;
import pe.ironbit.android.baking.loader.callback.WebRequestLoaderCallback;
import pe.ironbit.android.baking.loader.contract.WebRequestLoaderContract;
import pe.ironbit.android.baking.loader.factory.WebRequestFactory;
import pe.ironbit.android.baking.loader.factory.WebRequestFactoryRecipeList;
import pe.ironbit.android.baking.service.base.WebRequestService;
import pe.ironbit.android.baking.service.request.WebRequestRecipeListService;
import pe.ironbit.android.baking.util.ConfigUtil;
import pe.ironbit.android.baking.view.baking.BakingAdapter;

public class BakingActivity extends AppCompatActivity {
    /**
     * Set value of 1 columns when orientation is portrait on phones.
     */
    private static final int PHONE_PORTRAIT_MATRIX_SIZE = 1;

    /**
     * Set value of 2 columns when orientation is landscape on phones.
     */
    private static final int PHONE_LANDSCAPE_MATRIX_SIZE = 2;

    /**
     * Set value of 2 columns when orientation is portrait on tablets.
     */
    private static final int TABLET_PORTRAIT_MATRIX_SIZE = 2;

    /**
     * Set value of 3 columns when orientation is landscape on tablets.
     */
    private static final int TABLET_LANDSCAPE_MATRIX_SIZE = 3;

    /**
     * The size of the grid in the RecyclerView.
     */
    private static int MATRIX_SIZE = PHONE_LANDSCAPE_MATRIX_SIZE;

    /**
     * Used to update all the adapters with the same data.
     */
    private ConcreteObserver observer;

    /**
     * Adapter for Recipe Activity.
     */
    private BakingAdapter bakingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking);

        // verify network connection
        if (!verifyInternetConnection()) {
            View textView = ButterKnife.findById(this, R.id.activity_baking_message);
            textView.setVisibility(View.VISIBLE);

            View recyclerView = ButterKnife.findById(this, R.id.activity_baking_recyclerview);
            recyclerView.setVisibility(View.GONE);
            return;
        }

        // init objects interactions.
        initEventObjects();

        // load RecyclerView
        initRecyclerView();

        // load information from internet
        initWebRequestLoader();
    }

    /**
     * Initialize the events objects.
     */
    private void initEventObjects() {
        observer = new ConcreteObserver();

        bakingAdapter = new BakingAdapter();

        observer.attach(bakingAdapter);
    }

    /**
     * Initialize the recipe recycler view.
     */
    private void initRecyclerView() {
        // get recyclerview object
        RecyclerView recyclerView = ButterKnife.findById(this, R.id.activity_baking_recyclerview);

        // set number of columns for the recyclerview
        if (ConfigUtil.isDeviceTablet(this)) {
            if (ConfigUtil.isOrientationPortrait(this)) {
                MATRIX_SIZE = TABLET_PORTRAIT_MATRIX_SIZE;
            } else {
                MATRIX_SIZE = TABLET_LANDSCAPE_MATRIX_SIZE;
            }
        } else {
            if (ConfigUtil.isOrientationPortrait(this)) {
                MATRIX_SIZE = PHONE_PORTRAIT_MATRIX_SIZE;
            } else {
                MATRIX_SIZE = PHONE_LANDSCAPE_MATRIX_SIZE;
            }
        }

        // create layout and register it
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, MATRIX_SIZE);
        recyclerView.setLayoutManager(layoutManager);

        // add adapter
        recyclerView.setAdapter(bakingAdapter);
    }

    /**
     * Retrieve information from internet.
     * It requires string value (web_resource_recipe_list) as url resource.
     */
    private void initWebRequestLoader() {
        String url = getString(R.string.web_resource_recipe_list);

        DataParser parser = new DataParserV1();
        WebRequestService service = new WebRequestRecipeListService(parser);
        WebRequestFactory factory = new WebRequestFactoryRecipeList(this, url, service);
        LoaderManager.LoaderCallbacks<List> loader = new WebRequestLoaderCallback(this, factory, observer);

        getLoaderManager().initLoader(WebRequestLoaderContract.WEB_REQUEST_RECIPE_LOADER, null, loader).forceLoad();
    }

    /**
     * Verify network connection.
     * @return true is there is a network connection.
     */
    private boolean verifyInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null) && (networkInfo.isConnectedOrConnecting());
    }
}
