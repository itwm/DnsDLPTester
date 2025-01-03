

import java.io.*;
import java.net.Socket;
import java.io.File;
import java.util.Scanner;


public class SockClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj adres IP serwera : ");
        String serverAddress = scanner.nextLine();
        System.out.print("Podaj port na ktorym nasłuchuje serwer : ");
        int serverPort = Integer.parseInt(scanner.nextLine());
        try {
            // Utworzenie połączenia z serwerem
            socket = new Socket(serverAddress, serverPort);
            System.out.print("Podaj ścieżkę do pliku z nazwa: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);
                if (file.exists()) {
                bis = new BufferedInputStream(new FileInputStream(file));
                bos = new BufferedOutputStream(socket.getOutputStream());
                byte[] buffer = new byte[1024];
                int bytesRead;
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }   
                System.out.println("Plik został pomyślnie wysłany.");
                } else {
                    System.out.println("Plik nie istnieje.");
                } 
            } finally {
            // Zamknięcie strumieni i połączenia
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
            if (socket != null)
                socket.close();
        }
       
    }
}
