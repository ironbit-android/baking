package pe.ironbit.android.baking.player;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoPlayer {
    private Context context;

    private Uri uri;

    private View view;

    private SimpleExoPlayer exoPlayer;

    private SimpleExoPlayerView exoPlayerView;

    public VideoPlayer(Context context, Uri uri, View view) {
        this.context = context;
        this.uri = uri;
        this.view = view;

        setPlayerView(view);
        initialize();
    }

    public void release() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    public void initialize() {
        if (exoPlayer == null) {
            createVideoPlayer();
            prepareMediaSource(uri);
        }
    }

    public void setVideoPlayerEventListener(ExoPlayer.EventListener listener) {
        if (exoPlayer != null) {
            exoPlayer.addListener(listener);
        }
    }

    private void createVideoPlayer() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        exoPlayerView.setPlayer(exoPlayer);
    }

    private void setPlayerView(View view) {
        exoPlayerView = (SimpleExoPlayerView) view;
    }

    private void prepareMediaSource(Uri uri) {
        String userAgent = Util.getUserAgent(context, VideoPlayer.class.getSimpleName());

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, userAgent);

        MediaSource mediaSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
        exoPlayer.prepare(mediaSource);
    }
}
