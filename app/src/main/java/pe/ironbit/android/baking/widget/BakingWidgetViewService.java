package pe.ironbit.android.baking.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class BakingWidgetViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetViewFactory(getApplicationContext());
    }
}
