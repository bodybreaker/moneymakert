package login;

import kisa.KISA_SEED_CBC;
import lombok.extern.slf4j.Slf4j;
import util.YamlUtil;

import java.io.UnsupportedEncodingException;

public class Cryptor {

    private static String CHARSET = "utf-8";

    public byte[] encrypt(String str){

        byte[] enc = null;

        // 암호화 함수 호출 - CBC
        try {
            String pbUserKey = YamlUtil.getInstance().getProps("pbUserKey").toString();
            String defaultIV = YamlUtil.getInstance().getProps("defaultIV").toString();

            System.out.println("pbUserKey: "+pbUserKey);
            System.out.println("defaultIV: "+defaultIV);
            enc = KISA_SEED_CBC.SEED_CBC_Encrypt(pbUserKey.getBytes(),defaultIV.getBytes(),str.getBytes(CHARSET),0,str.getBytes(CHARSET).length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return enc;

    }

}
