package pe.ironbit.android.baking.model.step;

/**
 * POJO class that contains information about the recipe steps.
 */
public class StepData {
    /**
     * The order of the step.
     */
    private int id;

    /**
     * A complete description about the step.
     */
    private String description;

    /**
     * A short description about the step.
     */
    private String shortDescription;

    /**
     * An optional url video information about the current step.
     */
    private String videoURL;

    /**
     * An optional url image information about the current step.
     */
    private String thumbnailURL;

    /**
     * Unique constructor and setter of all parameters.
     *
     * @param id                {@link #id}
     * @param description       {@link #description}
     * @param shortDescription  {@link #shortDescription}
     * @param videoURL          {@link #videoURL}
     * @param thumbnailURL      {@link #thumbnailURL}
     */
    public StepData(int id, String description, String shortDescription, String videoURL, String thumbnailURL) {
        this.id = id;
        this.description = description;
        this.shortDescription = shortDescription;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    /**
     * Get step id.
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Get complete description.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get short description.
     *
     * @return String
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Get video URL.
     *
     * @return String
     */
    public String getVideoURL() {
        return videoURL;
    }

    /**
     * Get image URL.
     *
     * @return String
     */
    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
