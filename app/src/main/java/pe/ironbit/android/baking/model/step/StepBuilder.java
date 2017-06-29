package pe.ironbit.android.baking.model.step;

/**
 * Builder class for StepData.
 */
public final class StepBuilder {
    /**
     * {@see StepData#id}
     */
    private int id;

    /**
     * {@see StepData#description}
     */
    private String description;

    /**
     * {@see StepData#shortDescription}
     */
    private String shortDescription;

    /**
     * {@see StepData#videoURL}
     */
    private String videoURL;

    /**
     * {@see StepData#thumbnailURL}
     */
    private String thumbnailURL;

    /**
     * Static factory for one line builder statement.
     *
     * @return StepBuilder
     */
    public static StepBuilder builder() {
        return new StepBuilder();
    }

    /**
     * Unique constructor.
     */
    public StepBuilder() {
        clear();
    }

    /**
     * Set all fields to initial values.
     *
     * @return StepBuilder
     */
    public StepBuilder clear() {
        id = StepContract.initialId;
        description = "";
        shortDescription = "";
        videoURL = "";
        thumbnailURL = "";

        return this;
    }

    /**
     * Creates a StepData object using the factory.
     *
     * @return StepData
     */
    public StepData build() {
        return StepFactory.createStepData(id, description, shortDescription, videoURL, thumbnailURL);
    }

    /**
     * Set the step id.
     *
     * @param id {@see StepData#id}
     * @return StepBuilder
     */
    public StepBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Set complete description.
     *
     * @param description {@see StepData#description}
     * @return StepBuilder
     */
    public StepBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Set short description.
     *
     * @param shortDescription {@see StepData#shortDescription}
     * @return StepBuilder
     */
    public StepBuilder setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    /**
     * Set video URL.
     *
     * @param videoURL {@see StepData#videoURL}
     * @return StepBuilder
     */
    public StepBuilder setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    /**
     * Set image URL.
     *
     * @param thumbnailURL {@see StepData#thumbnailURL}
     * @return StepBuilder
     */
    public StepBuilder setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
        return this;
    }
}
