package pe.ironbit.android.baking.view.ingredient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.model.ingredient.IngredientData;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientViewHolder> {
    private List<IngredientData> list;

    @Override
    public RecipeIngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_ingredient, parent, false);
        RecipeIngredientViewHolder viewHolder = new RecipeIngredientViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeIngredientViewHolder holder, int position) {
        IngredientData data = list.get(position);
        holder.bind(data, String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<IngredientData> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
