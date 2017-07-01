package pe.ironbit.android.baking.listener.use;

import android.util.Log;

import java.util.Iterator;
import java.util.List;

import pe.ironbit.android.baking.listener.base.BaseRecipeListener;
import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.recipe.RecipeData;

public class UseRecipeListener implements BaseRecipeListener {
    @Override
    public void onRecipeListListener(List list) {
//        Iterator<RecipeData> data = list.iterator();
//        while (data.hasNext()) {
//            RecipeData v = data.next();
//
//            Iterator<IngredientData> id = v.getIngredients().iterator();
//            while (id.hasNext()) {
//                IngredientData da = id.next();
//                Log.v("Listener", "VAL: " + da.getName());
//            }
//        }
    }
}
