package telran.producer.consumer;


public class Receiver extends Thread {
    private MessageBox messageBoxOdd;
    private MessageBox messageBoxEven;

    public Receiver(MessageBox messageBoxOdd, MessageBox messageBoxEven) {
        this.messageBoxOdd = messageBoxOdd;
        this.messageBoxEven = messageBoxEven;

        setDaemon(true);
    }

    

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                if (!isThreadEven(getName()) ) {
                    message = messageBoxEven.take();
                } else {
                    message = messageBoxOdd.take();
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private boolean isThreadEven(String threadName) {
        return Integer.parseInt(threadName.replaceAll("\\D+", "")) % 2 == 0;
    }

}