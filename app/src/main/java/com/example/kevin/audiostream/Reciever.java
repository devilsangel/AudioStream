package com.example.kevin.audiostream;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by kevin on 2/22/16.
 */
public class Reciever extends Thread implements Constants{
    DatagramSocket socket;
    DatagramPacket packet;
    byte [] frame;
    int count =0;
    AudioTrack player;
    Handler handler;
    public Reciever(Handler handler){
        this.handler=handler;
    }
    public void run(){
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
        init();
        while(!MainActivity.sender){
            try {
                socket.receive(packet);
                count++;
                if(new String(frame).substring(0,3).equals("END")){
                    handler.sendEmptyMessage(2);
                    Log.d("boo", "boo");
                    continue;
                }
                else{
                    if(count==2)
                    handler.sendEmptyMessage(1);
                }
                player.write(frame,0,FRAME_SIZE_IN_BYTES);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.stop();
        player.release();
        socket.close();
    }
    void init(){
        try {
            socket=new DatagramSocket(PORT);
            socket.setBroadcast(true);
            player= new AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    SAMPLE_RATE,
                    AudioFormat.CHANNEL_OUT_MONO,
                    ENCODING_PCM_NUM_BITS,
                    TRACK_BUFFER_SIZE,
                    AudioTrack.MODE_STREAM
            );
            frame=new byte[FRAME_SIZE_IN_BYTES];
            packet=new DatagramPacket(frame,frame.length);
            player.play();

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
