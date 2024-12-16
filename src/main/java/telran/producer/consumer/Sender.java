package telran.producer.consumer;

import java.nio.channels.Pipe.SourceChannel;

public class Sender extends Thread{
    private int nMessages;
    private MessageBox messageBoxOdd;
    private MessageBox messageBoxEven;

    public Sender(int nMessages, MessageBox messageBoxOdd, MessageBox messageBoxEven) {
        this.nMessages = nMessages;
        this.messageBoxOdd = messageBoxOdd;
        this.messageBoxEven = messageBoxEven;
    }
    @Override
    public void run(){
        for(int i = 0; i < nMessages; i++) {
            try {
                if((i + 1) % 2 == 0) {
                    messageBoxEven.put("Message" + (i + 1));
                } else {
                    messageBoxOdd.put("Message" + (i + 1));
                }
                
            } catch (InterruptedException e) {
                
            }
            
    }
    System.out.println();

}

}