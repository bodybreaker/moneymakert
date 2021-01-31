package login;


import lombok.ToString;
import org.apache.commons.codec.binary.Hex;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * 프로그램창 띄우기, 로그인창에서 정보 입력후 로그인 수행
 */
public class Login {


    public static Robot robot;
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 창 오픈
     * @param filePath
     */
    public void excprgm(String filePath){

        //https://prolite.tistory.com/1238
        // 관리자권한 요구하기때문에 작업스케쥴러로 설정 필요 >> schtasks /run /tn "efriendexpert"
        String[] cmdarr = {filePath,""};
        try {
            //Process p = Runtime.getRuntime().exec("cmd /c "+filePath);
            Process p = Runtime.getRuntime().exec(cmdarr);

            //p.waitFor();
            //String result = getCommandResult(p.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 로그인정보 복호화하여 로그인 수행
     * @param loginModel
     */
    public void doLogin(LoginModel loginModel){
        //robot.keyPress(KeyEvent.VK_P);
        String loginID = loginModel.getIdEnc();
        String pwd = loginModel.getPwdEnc();
        String certPwd = loginModel.getCertPwdEnc();


        Cryptor cryptor = new Cryptor();

        System.out.println(cryptor.encrypt(loginID));

        inputString(loginID);
        robot.keyPress(KeyEvent.VK_ENTER); //enter

        inputString(pwd);
        robot.keyPress(KeyEvent.VK_ENTER); //enter

        inputString(certPwd);
        robot.keyPress(KeyEvent.VK_ENTER); //enter


    }



    private static String getCommandResult(InputStream stream) throws IOException {

        StringBuilder sb = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(stream);
             BufferedReader in = new BufferedReader(isr)) {

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString().trim();
    }

    public char[] convertStringToHex(String str) {

        // display in uppercase
        char[] chars = Hex.encodeHex(str.getBytes(StandardCharsets.UTF_8), false);

        return chars;
    }

    public void inputString(String str){
        char[] chars= str.toUpperCase(Locale.ROOT).toCharArray();
        for(char c : chars){
            System.out.println((int)c);
//            robot.keyPress(RobotKeyModule.getCode(c));
            pressKey(c);
            robot.delay(200);
        }
    }
    public void pressKey(char c){
        switch (c){
            case '~':
            case '!':
            case '@':
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '(':
            case ')':
            case '_':
            case '+':
            case '{':
            case '}':
            case ':':
            case '"':
            case '<':
            case '>':
            case '?':
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(getInputInt(c));

                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(getInputInt(c));
                break;

            default:
                robot.keyPress(getInputInt(c));
                break;
        }
    }



    public int getInputInt(char c){
        switch (c){
            case '~': return 96;
            case '!': return 49;
            case '@': return 50;
            case '#': return 51;
            case '$': return 52;
            case '%': return 53;
            case '^': return 54;
            case '&': return 55;
            case '*': return 56;
            case '(': return 57;
            case ')': return 48;
            case '_': return 45;
            case '+': return 61;
            case '{': return 91;
            case '}': return 93;
            case ':': return 59;
            case '"': return 39;
            case '<': return 44;
            case '>': return 46;
            case '?': return 47;

            default: return (int)c;
        }
    }

}
