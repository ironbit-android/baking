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

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepViewHolder> {
    List<StepData> list;

    BaseListener listener;

    public RecipeStepAdapter(BaseListener listener) {
        this.listener = listener;
        list = new ArrayList<>();
    }

    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_step, parent, false);
        RecipeStepViewHolder viewHolder = new RecipeStepViewHolder(view, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, int position) {
        StepData data = list.get(position);

        holder.bind(position, data.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<StepData> list) {
        if (list == null) {
            return;
        }

        this.list = list;
        notifyDataSetChanged();
    }
}
