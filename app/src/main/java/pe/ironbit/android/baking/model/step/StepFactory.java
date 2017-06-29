package pe.ironbit.android.baking.model.step;

/**
 * Factory for StepDAta.
 */
public final class StepFactory {
    /**
     * Private unique constructor.
     */
    private StepFactory() {
    }

    /**
     * Create StepData object from the parameters.
     *
     * @param id                {@see StepData#id}
     * @param description       {@see StepData#description}
     * @param shortDescription  {@see StepData#shortDescription}
     * @param videoURL          {@see StepData#videoURL}
     * @param thumbnailURL      {@see StepData#thumbnailURL}
     * @return
     */
    public static StepData createStepData(int id,
                                          String description,
                                          String shortDescription,
                                          String videoURL,
                                          String thumbnailURL) {
        return new StepData(id, description, shortDescription, videoURL, thumbnailURL);
    }
}
