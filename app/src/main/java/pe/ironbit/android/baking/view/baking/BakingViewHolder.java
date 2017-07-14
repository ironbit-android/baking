package pe.ironbit.android.baking.view.baking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.activity.RecipeActivity;
import pe.ironbit.android.baking.contract.BakingContract;
import pe.ironbit.android.baking.model.recipe.RecipeData;
import pe.ironbit.android.baking.model.recipe.RecipeParcelable;
import pe.ironbit.android.baking.widget.BakingWidgetIntentService;

public class BakingViewHolder extends RecyclerView.ViewHolder {
    private Context context;

    private RecipeData recipeData;

    @BindView(R.id.recyclerview_baking_item)
    TextView bakingItem;

    @BindView(R.id.recyclerview_baking_name)
    TextView bakingName;

    @BindView(R.id.recyclerview_baking_servings)
    TextView bakingServings;

    @BindView(R.id.recyclerview_baking_image)
    ImageView bakingImage;

    public BakingViewHolder(View itemView, final Context context) {
        super(itemView);

        this.context = context;
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeActivity.class);
                RecipeParcelable recipeParcelable = new RecipeParcelable(recipeData);
                intent.putExtra(BakingContract.BUNDLE_RECIPE_DATA_KEY, recipeParcelable);
                context.startActivity(intent);

                BakingWidgetIntentService.updateDataWidget(context, recipeData.getIngredients());
            }
        });
    }

    public void bind(RecipeData data) {
        recipeData = data;

        bakingItem.setText(String.valueOf(data.getId()));

        bakingName.setText(data.getName());

        bakingServings.setText(String.valueOf(data.getServings()));

        if (!TextUtils.isEmpty(data.getImage())) {
            Picasso.with(context).load(data.getImage()).into(bakingImage);
        }
    }
}
