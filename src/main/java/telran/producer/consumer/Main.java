package telran.producer.consumer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int N_MESSAGES = 20;
    static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, messageBox);
        List<Receiver> duties = new ArrayList<>();
        for (int i = 0; i < N_RECEIVERS; i++) {
            Receiver receiver = new Receiver(messageBox);
            duties.add(receiver);
            receiver.start();
        }
        sender.start();
        sender.join();
        
        for(Receiver receiver:duties) {
            receiver.interrupt();
            receiver.join();
        }
    }
}