package pe.ironbit.android.baking.view.recipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.model.recipe.RecipeData;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    private Context context;

    @BindView(R.id.recyclerview_recipes_item)
    TextView recipe_item;

    @BindView(R.id.recyclerview_recipes_name)
    TextView recipe_name;

    @BindView(R.id.recyclerview_recipes_servings)
    TextView recipe_servings;

    @BindView(R.id.recyclerview_recipes_image)
    ImageView recipe_image;

    public RecipeViewHolder(View itemView, Context context) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(RecipeData data) {
        recipe_item.setText(String.valueOf(data.getId()));

        recipe_name.setText(data.getName());

        recipe_servings.setText(String.valueOf(data.getServings()));

        if (!TextUtils.isEmpty(data.getImage())) {
            Picasso.with(context).load(data.getImage()).into(recipe_image);
        }
    }
}
