package pe.ironbit.android.baking.view.step;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.event.base.BaseListener;

public class RecipeStepViewHolder extends RecyclerView.ViewHolder {
    private int position;

    @BindView(R.id.recyclerview_step_item)
    TextView recipeStepItem;

    @BindView(R.id.recyclerview_step_description)
    TextView recipeStepDescription;

    public RecipeStepViewHolder(View view, final BaseListener listener) {
        super(view);

        ButterKnife.bind(this, view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.update(position);
            }
        });
    }

    public void bind(int position, String description) {
        this.position = position;

        recipeStepItem.setText(String.valueOf(position));

        recipeStepDescription.setText(description);
    }
}
