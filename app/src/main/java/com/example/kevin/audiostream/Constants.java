package com.example.kevin.audiostream;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;

/**
 * Created by kevin on 2/22/16.
 */
public interface Constants {
    public static final String ADDR="255.255.255.255";
    public static final int SAMPLE_RATE = 8000;
    public static final int FRAME_SIZE = 160;
    public static final int FRAME_SIZE_IN_SHORTS = 160;
    public static final int FRAME_SIZE_IN_BYTES = 320;
    public static final int ENCODING_PCM_NUM_BITS = AudioFormat.ENCODING_PCM_16BIT;
//    public static final int RECORD_BUFFER_SIZE = Math.max(
//            SAMPLE_RATE,
//            (int) Math.ceil(((double) (AudioRecord.getMinBufferSize(
//                    SAMPLE_RATE,
//                    AudioFormat.CHANNEL_IN_MONO,
//                    ENCODING_PCM_NUM_BITS)) / FRAME_SIZE )) * FRAME_SIZE);
public static final int RECORD_BUFFER_SIZE=(int) Math.ceil(((double) (AudioRecord.getMinBufferSize(
                    SAMPLE_RATE,
                    AudioFormat.CHANNEL_IN_MONO,
                    ENCODING_PCM_NUM_BITS)) / FRAME_SIZE )) * FRAME_SIZE;
//    public static final int TRACK_BUFFER_SIZE = Math.max(
//            FRAME_SIZE,
//            (int) Math.ceil(((double) (AudioTrack.getMinBufferSize(
//                    SAMPLE_RATE,
//                    AudioFormat.CHANNEL_OUT_MONO,
//                    ENCODING_PCM_NUM_BITS)) / FRAME_SIZE )) * FRAME_SIZE);
public static final int TRACK_BUFFER_SIZE=(int) Math.ceil(((double) (AudioTrack.getMinBufferSize(
                    SAMPLE_RATE,
                    AudioFormat.CHANNEL_OUT_MONO,
                    ENCODING_PCM_NUM_BITS)) / FRAME_SIZE )) * FRAME_SIZE;
    public static final int PORT=2010;
}
