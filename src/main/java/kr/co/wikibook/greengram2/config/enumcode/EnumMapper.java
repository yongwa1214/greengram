package kr.co.wikibook.greengram2.config.enumcode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//내부적으로 관리하는 공통코드를 요청을 통해 확인하고 싶을 때 사용
public class EnumMapper {
    private Map<String, List<EnumMapperValue>> factory = new LinkedHashMap<>();
    //(linked)hashMap: 데이터를 저장하는 방법, array나 list 와 저장 방식이 다름
    // array나 list는 방번호가 존재
    // 근데 이건 key와 value로 저장함 여기서 key 타입은 string 이고 value는 list<EnumMapperValue>

    //e는 EnumMapperValue를 상속받은 어떤 타입이든 전달 될 수 있다.
    public void put (String key, Class<? extends EnumMapperValue> e){
        factory.put(key, toEnumValues(e));

    }

    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperValue> e){
        return null;
    }

}
