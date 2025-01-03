

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SockServer{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        int port = 80; //Dodane
        try {
            // Utworzenie serwerowego gniazda (ServerSocket) na porcie 12345
            serverSocket = new ServerSocket(port); //Zmiana na 80 

            System.out.println("Serwer nasłuchuje na porcie "+port+" ...");//dodane

            // Akceptowanie przychodzącego połączenia od klienta
            clientSocket = serverSocket.accept();

            System.out.println("Połączono z klientem: " + clientSocket);

            // Utworzenie strumieni do odczytu i zapisu danych
            bis = new BufferedInputStream(clientSocket.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream("plik.txt"));

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            System.out.println("Plik został pomyślnie odebrany.");
        } finally {
            // Zamknięcie strumieni i połączenia
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
            if (clientSocket != null)
                clientSocket.close();
            if (serverSocket != null)
                serverSocket.close();
        }
    }
}
