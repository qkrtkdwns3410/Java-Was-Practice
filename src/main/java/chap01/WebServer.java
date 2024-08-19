package chap01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * packageName    : chap01
 * fileName       : WebServer
 * author         : ipeac
 * date           : 24. 8. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 8. 18.        ipeac       최초 생성
 */
public class WebServer {
    public static void main(String[] args) {
        File receiveFile = new File("src/main/resources/chap01/server_receive.txt");
        try {
            ServerSocket server = new ServerSocket(8001);
            System.out.println("클라이언트로부터 접속을 기다리고 있습니다.");
            
            File sendFile = new File("src/main/resources/chap01/server_send.txt");
            FileOutputStream fos = new FileOutputStream(receiveFile);
            FileInputStream fis = new FileInputStream(sendFile);
            
            Socket socket = server.accept();
            System.out.println("클라이언트 접속 완료");
            
            int ch;
            InputStream in = socket.getInputStream();
            
            // 클라이언트로부터 받은 데이터를 파일로 저장
            while ((ch = in.read()) != 0) {
                fos.write(ch);
            }
            
            // server_send.txt 파일의 내용을 클라이언트로 전송
            OutputStream os = socket.getOutputStream();
            
            while ((ch = fis.read()) != -1) {
                os.write(ch);
            }
            
            socket.close();
            System.out.println("클라이언트와 통신 종료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}