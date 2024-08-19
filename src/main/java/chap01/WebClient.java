package chap01;

import java.io.*;
import java.net.Socket;

/**
 * packageName    : chap01
 * fileName       : WebClient
 * author         : ipeac
 * date           : 24. 8. 19.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 8. 19.        ipeac       최초 생성
 */
public class WebClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8001);
            File receiveFile = new File("src/main/resources/chap01/client_receive.txt");
            File sendFile = new File("src/main/resources/chap01/client_send.txt");
            
            FileInputStream fis = new FileInputStream(sendFile);
            FileOutputStream fos = new FileOutputStream(receiveFile);
            
            int ch;
            
            // client_send.txt 파일의 내용을 서버로 전송
            OutputStream out = socket.getOutputStream();
            while ((ch = fis.read()) != -1) {
                out.write(ch);
            }
            
            //종료 표시를 위해 0 송신
            out.write(0);
            
            //서버 리턴을 client_receive.txt 파일로 저장
            InputStream in = socket.getInputStream();
            while ((ch = in.read()) != -1) {
                fos.write(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}