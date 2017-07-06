package pe.ironbit.android.baking.model.ingredient;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientParcelable implements Parcelable {
    private IngredientData data;

    public static final Parcelable.Creator<IngredientParcelable> CREATOR
            = new Parcelable.Creator<IngredientParcelable>() {
        @Override
        public IngredientParcelable createFromParcel(Parcel parcel) {
            return new IngredientParcelable(parcel);
        }

        @Override
        public IngredientParcelable[] newArray(int size) {
            return new IngredientParcelable[size];
        }
    };

    public IngredientParcelable(IngredientData data) {
        this.data = data;
    }

    public IngredientParcelable(Parcel parcel) {
        data = IngredientBuilder.builder()
                                .setQuantity(parcel.readInt())
                                .setMeasure(parcel.readString())
                                .setName(parcel.readString())
                                .build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(data.getQuantity());
        parcel.writeString(data.getMeasure());
        parcel.writeString(data.getName());
    }

    public IngredientData getIngredientData() {
        return data;
    }
}
