package chap02;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * packageName    : chap02
 * fileName       : SmallCat01
 * author         : sjunpark
 * date           : 24. 10. 16.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 16.        sjunpark       최초 생성
 */
public class SmallCat01 {
    
    private static final String GET_METHOD = "GET";
    private static final String DOCUMENT_ROOT = "\\usr\\share\\nginx\\html\\";
    
    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(8001)) {
            Socket socket = server.accept();
            
            InputStream is = socket.getInputStream();
            
            String line;
            String path = null;
            while ((line = readLine(is)) != null) {
                if (line.isEmpty()) {
                    break;
                }
                
                if (line.startsWith(GET_METHOD)) {
                    path = line.split(" ")[1];
                }
            }
            
            OutputStream os = socket.getOutputStream();
            
            //리스폰스 헤더 반환
            writeLine(os, "HTTP/1.1 200 OK");
            writeLine(os, "Server: SmallCat01");
            writeLine(os, MessageFormat.format("Date: {0}", getDateString()));
            writeLine(os, "Content-Type: text/html");
            writeLine(os, "Connection: close");
            writeLine(os, "");
            
            //리스폰스 바디를 반환
            try(FileInputStream fis = new FileInputStream(DOCUMENT_ROOT + path)) {
                int ch;
                while ((ch = fis.read()) != -1) {
                    os.write(ch);
                }
            }
            
            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 입력 스트림을 읽어서 문자열을 반환하는 함수
    private static String readLine(InputStream in) throws Exception {
        StringBuilder sb = new StringBuilder();
        int ch;
        
        try {
            label:
            while ((ch = in.read()) != -1) {
                switch (ch) {
                    case '\r':
                        // do nothing
                        break;
                    case '\n':
                        break label;
                    default:
                        sb.append((char) ch);
                        break;
                }
                
                if (ch == 1) {
                    return null;
                } else {
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
    
    //1행의 문자열을 바이트열로 OutputStream 으로 쓰는 유틸 함수
    private static void writeLine(OutputStream out, String str) throws Exception {
        for (char ch : str.toCharArray()) {
            out.write((int) ch);
        }
        
        out.write((int) '\r');
        out.write((int) '\n');
    }
    
    //현재시각을 HTTP 표준 포맷에 맞게 날짜 문자열로 반환한다.
    private static String getDateString() {
        return ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
