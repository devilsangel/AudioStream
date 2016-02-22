package com.example.kevin.audiostream;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.provider.SyncStateContract;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by kevin on 2/22/16.
 */
public class Sender extends Thread implements Constants{
    DatagramSocket socket;
    byte [] frame;
    DatagramPacket packet;
    AudioRecord recorder;
    public void run(){
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
        init();
        while(MainActivity.sender){
            recorder.startRecording();
            recorder.read(frame, 0, FRAME_SIZE_IN_BYTES);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        recorder.stop();
        recorder.release();
        socket.close();
    }
    void init(){
        try {
            socket=new DatagramSocket();
            socket.setBroadcast(true);
            InetAddress addr=InetAddress.getByName(ADDR);
            frame=new byte[FRAME_SIZE_IN_BYTES];


            packet=new DatagramPacket(
                    frame,
                    frame.length,
                    addr,
                    PORT);


            recorder=new AudioRecord(
                    MediaRecorder.AudioSource.MIC,
                    SAMPLE_RATE,
                    AudioFormat.CHANNEL_IN_MONO,
                    ENCODING_PCM_NUM_BITS,
                    RECORD_BUFFER_SIZE);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
