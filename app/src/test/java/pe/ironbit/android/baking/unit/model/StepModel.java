package pe.ironbit.android.baking.unit.model;

import org.junit.Test;

import pe.ironbit.android.baking.model.step.StepBuilder;
import pe.ironbit.android.baking.model.step.StepContract;
import pe.ironbit.android.baking.model.step.StepData;
import pe.ironbit.android.baking.model.step.StepFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepModel {
    private static final int ID = 1234;

    private static final String DESCRIPTION = "complete description";

    private static final String SHORT_DESCRIPTION = "short description";

    private static final String VIDEO_URL = "some video URL";

    private static final String THUMBNAIL_URL = "some thumbnail URL";

    @Test
    public void stepFactory_Functionality() {
        StepData data = StepFactory.createStepData(ID, DESCRIPTION, SHORT_DESCRIPTION, VIDEO_URL, THUMBNAIL_URL);

        assertThat(data.getId(), is(ID));
        assertThat(data.getDescription(), is(DESCRIPTION));
        assertThat(data.getShortDescription(), is(SHORT_DESCRIPTION));
        assertThat(data.getVideoURL(), is(VIDEO_URL));
        assertThat(data.getThumbnailURL(), is(THUMBNAIL_URL));
    }

    @Test
    public void stepBuilder_InitialValues() {
        StepData data = StepBuilder.builder().build();

        assertThat(data.getId(), is(StepContract.initialId));
        assertThat(data.getDescription(), is(""));
        assertThat(data.getShortDescription(), is(""));
        assertThat(data.getVideoURL(), is(""));
        assertThat(data.getThumbnailURL(), is(""));
    }

    @Test
    public void stepBuilder_UsingConstructor() {
        StepBuilder builder = new StepBuilder();

        builder.setId(ID)
               .setDescription(DESCRIPTION)
               .setShortDescription(SHORT_DESCRIPTION)
               .setVideoURL(VIDEO_URL)
               .setThumbnailURL(THUMBNAIL_URL);

        StepData data = builder.build();

        assertThat(data.getId(), is(ID));
        assertThat(data.getDescription(), is(DESCRIPTION));
        assertThat(data.getShortDescription(), is(SHORT_DESCRIPTION));
        assertThat(data.getVideoURL(), is(VIDEO_URL));
        assertThat(data.getThumbnailURL(), is(THUMBNAIL_URL));
    }

    @Test
    public void stepBuilder_UsingStaticFunction() {
        StepData data = StepBuilder.builder()
                .setId(ID)
                .setDescription(DESCRIPTION)
                .setShortDescription(SHORT_DESCRIPTION)
                .setVideoURL(VIDEO_URL)
                .setThumbnailURL(THUMBNAIL_URL)
                .build();

        assertThat(data.getId(), is(ID));
        assertThat(data.getDescription(), is(DESCRIPTION));
        assertThat(data.getShortDescription(), is(SHORT_DESCRIPTION));
        assertThat(data.getVideoURL(), is(VIDEO_URL));
        assertThat(data.getThumbnailURL(), is(THUMBNAIL_URL));
    }
}
