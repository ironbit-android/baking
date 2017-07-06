package pe.ironbit.android.baking.view.ingredient;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.model.ingredient.IngredientData;

public class RecipeIngredientViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recyclerview_ingredient_item)
    TextView ingredientItem;

    @BindView(R.id.recyclerview_ingredient_quantity)
    TextView ingredientQuantity;

    @BindView(R.id.recyclerview_ingredient_measure)
    TextView ingredientMeasure;

    @BindView(R.id.recyclerview_ingredient_name)
    TextView ingredientName;

    public RecipeIngredientViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public void bind(IngredientData data, String position) {
        ingredientItem.setText(position);

        ingredientQuantity.setText(String.valueOf(data.getQuantity()));

        ingredientMeasure.setText(data.getMeasure());

        ingredientName.setText(data.getName());
    }
}
