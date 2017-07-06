package pe.ironbit.android.baking.model.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.ingredient.IngredientParcelable;
import pe.ironbit.android.baking.model.step.StepData;
import pe.ironbit.android.baking.model.step.StepParcelable;

public class RecipeParcelable implements Parcelable {
    private RecipeData data;

    public static final Parcelable.Creator<RecipeParcelable> CREATOR
            = new Parcelable.Creator<RecipeParcelable>() {
        @Override
        public RecipeParcelable createFromParcel(Parcel parcel) {
            return new RecipeParcelable(parcel);
        }

        @Override
        public RecipeParcelable[] newArray(int size) {
            return new RecipeParcelable[size];
        }
    };

    public RecipeParcelable(RecipeData data) {
        this.data = data;
    }

    public RecipeParcelable(Parcel parcel) {
        RecipeBuilder builder = new RecipeBuilder();

        builder.setId(parcel.readInt())
               .setName(parcel.readString())
               .setServings(parcel.readInt())
               .setImage(parcel.readString());

        {
            List<StepParcelable> stepParcelableList = new ArrayList<>();
            parcel.readTypedList(stepParcelableList, StepParcelable.CREATOR);

            List<StepData> stepDataList = new ArrayList<>();
            for (StepParcelable stepParcelable : stepParcelableList) {
                stepDataList.add(stepParcelable.getStepData());
            }
            builder.setSteps(stepDataList);
        }

        {
            List<IngredientParcelable> ingredientParcelableList = new ArrayList<>();
            parcel.readTypedList(ingredientParcelableList, IngredientParcelable.CREATOR);

            List<IngredientData> ingredientDataList = new ArrayList<>();
            for (IngredientParcelable ingredientParcelable : ingredientParcelableList) {
                ingredientDataList.add(ingredientParcelable.getIngredientData());
            }
            builder.setIngredients(ingredientDataList);
        }

        data = builder.build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(data.getId());
        parcel.writeString(data.getName());
        parcel.writeInt(data.getServings());
        parcel.writeString(data.getImage());

        {
            List<StepParcelable> list = new ArrayList<>();
            for (StepData stepData : data.getSteps()) {
                list.add(new StepParcelable((stepData)));
            }

            parcel.writeTypedList(list);
        }

        {
            List<IngredientParcelable> list = new ArrayList<>();
            for (IngredientData ingredientData : data.getIngredients()) {
                list.add(new IngredientParcelable((ingredientData)));
            }

            parcel.writeTypedList(list);
        }
    }

    public RecipeData getRecipeData() {
        return data;
    }
}
