package pe.ironbit.android.baking.widget;

import java.util.ArrayList;
import java.util.List;

final class BakingWidgetContent {
    private static List<String> list = new ArrayList<>();

    static List<String> getIngredients() {
        return list;
    }

    static void setIngredients(List<String> list) {
        if (list == null) {
            BakingWidgetContent.list = new ArrayList<String>();
        } else {
            BakingWidgetContent.list = list;
        }
    }
}
