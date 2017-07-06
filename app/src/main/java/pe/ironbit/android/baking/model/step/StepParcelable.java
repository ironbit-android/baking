package pe.ironbit.android.baking.model.step;

import android.os.Parcel;
import android.os.Parcelable;

public class StepParcelable implements Parcelable {
    private StepData data;

    public static final Parcelable.Creator<StepParcelable> CREATOR
            = new Parcelable.Creator<StepParcelable>() {
        @Override
        public StepParcelable createFromParcel(Parcel parcel) {
            return new StepParcelable(parcel);
        }

        @Override
        public StepParcelable[] newArray(int size) {
            return new StepParcelable[size];
        }
    };

    public StepParcelable(StepData data) {
        this.data = data;
    }

    public StepParcelable(Parcel parcel) {
        data = StepBuilder.builder()
                .setId(parcel.readInt())
                .setDescription(parcel.readString())
                .setShortDescription(parcel.readString())
                .setVideoURL(parcel.readString())
                .setThumbnailURL(parcel.readString())
                .build();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(data.getId());
        parcel.writeString(data.getDescription());
        parcel.writeString(data.getShortDescription());
        parcel.writeString(data.getVideoURL());
        parcel.writeString(data.getThumbnailURL());
    }

    public StepData getStepData() {
        return data;
    }
}
