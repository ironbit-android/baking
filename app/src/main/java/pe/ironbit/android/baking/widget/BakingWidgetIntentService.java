package pe.ironbit.android.baking.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.model.ingredient.IngredientData;

public class BakingWidgetIntentService extends IntentService {

    public static void initWidget(Context context) {
        Intent intent = new Intent(context, BakingWidgetIntentService.class);
        intent.setAction(BakingWidgetContract.ACTION_INIT_WIDGET_KEY);
        context.startService(intent);
    }

    public static void updateWidget(Context context) {
        Intent intent = new Intent(context, BakingWidgetIntentService.class);
        intent.setAction(BakingWidgetContract.ACTION_UPDATE_WIDGET_KEY);
        context.startService(intent);
    }

    public static void updateDataWidget(Context context, List<IngredientData> ingredients) {
        List<String> data = new ArrayList<>();
        for (IngredientData ingredient : ingredients) {
            data.add(ingredient.getName());
        }

        Intent intent = new Intent(context, BakingWidgetIntentService.class);
        intent.setAction(BakingWidgetContract.ACTION_SET_DATA_WIDGET_KEY);
        intent.putStringArrayListExtra(BakingWidgetContract.DATA_WIDGET_KEY, (ArrayList) data);
        context.startService(intent);
    }

    public BakingWidgetIntentService() {
        super(BakingWidgetIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        String action = intent.getAction();
        if (BakingWidgetContract.ACTION_INIT_WIDGET_KEY.equals(action)) {
            handleActionInitWidget(intent);
            return;
        }
        if (BakingWidgetContract.ACTION_UPDATE_WIDGET_KEY.equals(action)) {
            handleActionUpdateWidget(intent);
            return;
        }
        if (BakingWidgetContract.ACTION_SET_DATA_WIDGET_KEY.equals(action)) {
            handleActionSetDataWidget(intent);
            return;
        }
    }

    private void handleActionInitWidget(Intent intent) {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = manager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));

        BakingWidgetContent.setIngredients(null);
        BakingWidgetProvider.updateBakingWidget(getApplicationContext(), manager, appWidgetIds);
    }

    private void handleActionUpdateWidget(Intent intent) {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = manager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));

        manager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.baking_widget_list_view);
        BakingWidgetProvider.updateBakingWidget(getApplicationContext(), manager, appWidgetIds);
    }

    private void handleActionSetDataWidget(Intent intent) {
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = manager.getAppWidgetIds(new ComponentName(this, BakingWidgetProvider.class));

        List list = intent.getStringArrayListExtra(BakingWidgetContract.DATA_WIDGET_KEY);
        BakingWidgetContent.setIngredients(list);

        manager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.baking_widget_list_view);
        BakingWidgetProvider.updateBakingWidget(getApplicationContext(), manager, appWidgetIds);
    }
}
