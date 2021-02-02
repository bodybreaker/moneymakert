package util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlUtil {

    private static final YamlUtil instance = new YamlUtil();

    private Map<String,Object> yamlMap;

    private YamlUtil(){

    };


    public void init(InputStream inputStream){
        // 01.yaml 파일 읽어오기
        //https://www.baeldung.com/java-snake-yaml
        Yaml yaml = new Yaml();
        yamlMap = yaml.load(inputStream);
    }


    public static YamlUtil getInstance() {
        return instance;
    }

    public Object getProps(String key){
        return yamlMap.get(key);
    }
}
