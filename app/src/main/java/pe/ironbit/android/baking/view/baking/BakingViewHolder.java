package pe.ironbit.android.baking.view.baking;

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

public class BakingViewHolder extends RecyclerView.ViewHolder {
    private Context context;

    @BindView(R.id.recyclerview_baking_item)
    TextView baking_item;

    @BindView(R.id.recyclerview_baking_name)
    TextView baking_name;

    @BindView(R.id.recyclerview_baking_servings)
    TextView baking_servings;

    @BindView(R.id.recyclerview_baking_image)
    ImageView baking_image;

    public BakingViewHolder(View itemView, Context context) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(RecipeData data) {
        baking_item.setText(String.valueOf(data.getId()));

        baking_name.setText(data.getName());

        baking_servings.setText(String.valueOf(data.getServings()));

        if (!TextUtils.isEmpty(data.getImage())) {
            Picasso.with(context).load(data.getImage()).into(baking_image);
        }
    }
}
