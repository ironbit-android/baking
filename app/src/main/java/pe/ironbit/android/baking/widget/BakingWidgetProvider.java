package pe.ironbit.android.baking.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import pe.ironbit.android.baking.R;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {
    static void updateBakingWidget(Context context, AppWidgetManager manager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateBakingWidget(context, manager, appWidgetId);
        }
    }

    static void updateBakingWidget(Context context, AppWidgetManager manager, int appWidgetId) {
        if (BakingWidgetContent.getIngredients().size() == 0) {
            updateWidget(context, manager, appWidgetId);
        } else {
            updateWidgetWithData(context, manager, appWidgetId);
        }
    }

    private static void updateWidget(Context context, AppWidgetManager manager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);

        views.setViewVisibility(R.id.baking_widget_list_view, View.GONE);
        views.setViewVisibility(R.id.baking_widget_text_view, View.VISIBLE);

        manager.updateAppWidget(appWidgetId, views);
    }

    private static void updateWidgetWithData(Context context, AppWidgetManager manager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.setViewVisibility(R.id.baking_widget_text_view, View.GONE);
        views.setViewVisibility(R.id.baking_widget_list_view, View.VISIBLE);

        Intent intent = new Intent(context, BakingWidgetViewService.class);
        views.setRemoteAdapter(R.id.baking_widget_list_view, intent);

        manager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] appWidgetIds) {
        BakingWidgetIntentService.updateWidget(context);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}
