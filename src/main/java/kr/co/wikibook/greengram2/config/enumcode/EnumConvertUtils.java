package kr.co.wikibook.greengram2.config.enumcode;

import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

// 공통코드 테이블 안만들려고 이 작업을 하는 중

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumConvertUtils {

    // String(Code값) to Enum
    // 리턴 타입이 Enum 이어야 하고 EnumMapperType을 상속받은 Enum 이어야 한다.
    // enumClass는 Enum 타입이어야한다.
    public static <E extends Enum<E> & EnumMapperType> E ofCode(Class<E> enumClass, String code){
        if(StringUtils.isBlank(code)){return null;} // code 매개변수가 null 이거나 빈칸인 경우 return null 처리

        // Enum에 있는 값 중 매개변수 code와 같은 값을 차자 리턴하기 위함. 근데 같은게 없으면 return null
        return EnumSet.allOf(enumClass).stream() // enum을 Stream 화 하기 위함
                .filter(item -> item.getCode().equals(code))
                .findFirst().orElse(null);
    }

    // Enum to String(Code값)
    public static <E extends Enum<E> & EnumMapperType> String toCode(E enumItem) {
        if (enumItem == null) {
            return null;
        }
        return enumItem.getCode();
    }
}
