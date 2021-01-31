import login.Login;
import login.LoginModel;
import org.yaml.snakeyaml.Yaml;
import util.YamlUtil;

import java.io.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        InputStream inputStream = Main.class.getResourceAsStream("props.yaml");
        YamlUtil.getInstance().init(inputStream);

        String exeDir = YamlUtil.getInstance().getProps("exeDir").toString();
        String loginInfoDir = YamlUtil.getInstance().getProps("loginInfoDir").toString();
        String pbUserKey = YamlUtil.getInstance().getProps("pbUserKey").toString();
        String defaultIV = YamlUtil.getInstance().getProps("defaultIV").toString();


        System.out.println("＃실행파일 위치 : "+exeDir);
        System.out.println("＃로그인정보 위치 : "+loginInfoDir);


        // 02.로그인 정보 가져오기
        LoginModel loginModel = new LoginModel();
        File file = new File(loginInfoDir);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            int cnt=0;

            while((line = bufferedReader.readLine())!=null){

                switch (cnt){
                    case 0: loginModel.setIdEnc(line);break;
                    case 1: loginModel.setPwdEnc(line);break;
                    case 2: loginModel.setCertPwdEnc(line);break;
                }
                cnt++;

            }
            if(cnt!=3){
                System.err.println("로그인정보파일 올바르지 않음");
                System.exit(-1);
            }

            System.out.println(loginModel.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 03. 로그인 창 열기
        Login login = new Login();
        login.excprgm(exeDir);

        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        // 04. 로그인정보 입력
        login.doLogin(loginModel);

    }
}
