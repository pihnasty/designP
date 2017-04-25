package shild.chapter22.ServerClient_test._06_UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


 class ClientUDP
{
    public static void main(String args[])
    {
        DatagramSocket sock = null;

        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            // sock = new DatagramSocket();
            sock = new DatagramSocket(55555);

            while(true)
            {
                //Ожидаем ввод сообщения серверу
                System.out.println("Введите сообщение серверу: ");
                String s = (String)cin.readLine();
                byte[] b = s.getBytes();

                //Отправляем сообщение
                DatagramPacket  dp = new DatagramPacket(b , b.length , InetAddress.getByName("192.168.13.69") , 7000);
                sock.send(dp);

                //буфер для получения входящих данных
                byte[] buffer = new byte[65536];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                //Получаем данные
                sock.receive(reply);
                byte[] data = reply.getData();
                s = new String(data, 0, reply.getLength());

                System.out.println("Сервер: " + reply.getAddress().getHostAddress() + ", порт: " + reply.getPort() + ", получил: " + s);
            }
        }catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }

}
