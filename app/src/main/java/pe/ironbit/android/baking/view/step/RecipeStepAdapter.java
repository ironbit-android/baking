package pe.ironbit.android.baking.view.step;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.event.base.BaseListener;
import pe.ironbit.android.baking.model.step.StepData;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepViewHolder>
                               implements BaseListener {
    Selector selector;

    List<StepData> list;

    public enum Selector {
        LONG_DESCRIPTION,
        SHORT_DESCRIPTION
    }

    public RecipeStepAdapter(Selector selector) {
        this.selector = selector;

        list = new ArrayList<>();
    }

    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_step, parent, false);
        RecipeStepViewHolder viewHolder = new RecipeStepViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, int position) {
        StepData data = list.get(position);

        if (selector == Selector.LONG_DESCRIPTION) {
            holder.bind(String.valueOf(position), data.getDescription());
        } else {
            holder.bind(String.valueOf(position), data.getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void update(Object object) {
        list = (List<StepData>) object;
        notifyDataSetChanged();
    }
}
