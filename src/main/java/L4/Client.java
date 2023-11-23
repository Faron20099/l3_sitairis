package L4;

import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        boolean exit = false;
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        while(!exit){
            byte[] sendData;
            byte[] receiveData = new byte[1024];
            Scanner scanner = new Scanner(System.in);
            System.out.print("Комент по приколу: ");
            System.out.print("Введите значения дя x y z через запятую: ");
            String values = scanner.nextLine();
            sendData = values.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8001);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String response = new String(receivePacket.getData());
            System.out.println(response.substring(0,response.indexOf('}')+1));
            System.out.print("Введите 1 для выхода или любой символ для продолжения: ");
            String a = scanner.nextLine();
            if (a.equals("1")){
                exit = true;
                clientSocket.close();
            }
        }
    }
}