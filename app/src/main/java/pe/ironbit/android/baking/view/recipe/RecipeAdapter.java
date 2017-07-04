package pe.ironbit.android.baking.view.recipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.event.base.BaseListener;
import pe.ironbit.android.baking.model.recipe.RecipeData;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder>
                           implements BaseListener {

    private List<RecipeData> list;

    public RecipeAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_recipes, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        RecipeData data = list.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void update(Object object) {
        this.list = (List<RecipeData>)object;
        notifyDataSetChanged();
    }
}