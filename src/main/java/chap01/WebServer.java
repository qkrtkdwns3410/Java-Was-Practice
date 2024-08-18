package chap01;

import java.io.File;
import java.net.ServerSocket;

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
        try {
            ServerSocket server = new ServerSocket(8001);
            File recvFile = new File("classpath:chap01/server_recv.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}