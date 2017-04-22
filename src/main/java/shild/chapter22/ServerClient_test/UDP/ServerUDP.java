package shild.chapter22.ServerClient_test.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


class ServerUDP
{
    public static void main(String args[])
    {
        try
        {
            //Создаем сокет
            DatagramSocket sock = new DatagramSocket(7000);

            //буфер для получения входящих данных
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            System.out.println("Ожидаем данные...");

            while(true)
            {
                System.out.println("while(true) ...");
                //Получаем данные
                sock.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());

                System.out.println("Сервер получил: " + s);

                //Отправляем данные клиенту
                DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
                sock.send(dp);
            }
        }

        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }


}
