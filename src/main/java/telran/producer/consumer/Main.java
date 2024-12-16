package telran.producer.consumer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int N_MESSAGES = 20;
    private static final int N_RECEIVERS = 10;
    private static List<Receiver> duties = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBoxOdd = new SimpleMessageBox();
        MessageBox messageBoxEven = new SimpleMessageBox();

        Sender sender = new Sender(N_MESSAGES, messageBoxOdd, messageBoxEven);
        for (int i = 0; i < N_RECEIVERS; i++) {
            Receiver receiver = new Receiver(messageBoxOdd, messageBoxEven);
            duties.add(receiver);
            receiver.start();
        }
        sender.start();
        sender.join();

        // Thread.sleep(100);

        for (Receiver receiver : duties) {
            receiver.interrupt();
            receiver.join();
        }
        
    }
}