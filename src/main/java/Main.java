import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        String message = String.valueOf(new Random().nextInt(1000000000));//generate a string containing random number
        sender.send(message.getBytes());  //convert our string to byte array and send it
        System.out.println(" [x] Sent '" + message + "'");
    }
}
