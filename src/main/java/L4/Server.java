package L4;

import java.net.*;
import java.io.*;
import org.json.JSONObject;

import static java.lang.Math.*;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(8001);
        System.out.println("Server.UDPServer: Started on " + serverSocket.getLocalAddress() + ":"
                + serverSocket.getLocalPort());
        while (true) {
            byte[] data = new byte[1024];
            double x,y,z,result;
            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData()).trim();
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String[] params = sentence.split(",");
            x = Double.parseDouble(params[0]);
            y = Double.parseDouble(params[1]);
            z = Double.parseDouble(params[2]);
            for(int i =0; i< params.length;i++){
                System.out.println(params[i]);
            }
            result = 6 +(Math.exp(x-sin(y))/(y+(tan(x*x)/(y+pow(x,7)/z))))*pow((1 + pow(1/tan(z/100),7)),sqrt(abs(y)+3));
            JSONObject answer = new JSONObject();
            answer.put("result", result);
            answer.put("x", x);
            answer.put("y", y);
            answer.put("z", z);
            data = answer.toString().getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, port);
            serverSocket.send(sendPacket);

            BufferedWriter writer = new BufferedWriter(new FileWriter("Answers.txt", true));
            writer.write(answer.toString() + "\n");
            writer.close();
            System.out.println("Client connected from " + IPAddress + ":" + port);
        }
    }
}