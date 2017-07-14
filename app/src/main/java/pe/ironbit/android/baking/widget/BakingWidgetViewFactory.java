package pe.ironbit.android.baking.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.R;

public class BakingWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;

    private List<String> list;

    public BakingWidgetViewFactory(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        list = BakingWidgetContent.getIngredients();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_list_view);
        views.setTextViewText(R.id.baking_widget_list_item, String.valueOf(position + 1));
        views.setTextViewText(R.id.baking_widget_list_name, list.get(position));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
