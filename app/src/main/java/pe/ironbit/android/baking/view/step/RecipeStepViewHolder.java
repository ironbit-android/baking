package pe.ironbit.android.baking.view.step;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;

public class RecipeStepViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recyclerview_step_item)
    TextView recipeStepItem;

    @BindView(R.id.recyclerview_step_description)
    TextView recipeStepDescription;

    public RecipeStepViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }

    public void bind(String position, String description) {
        recipeStepItem.setText(position);

        recipeStepDescription.setText(description);
    }
}
