package kr.co.wikibook.greengram2.config.enumcode.model;


import jakarta.persistence.Converter;
import kr.co.wikibook.greengram2.config.enumcode.AbstractEnumCodeConverter;
import kr.co.wikibook.greengram2.config.enumcode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumUserRole implements EnumMapperType {
    USER_1("01", "유저1")
    ,USER_2("02", "유저2")
    ,ADMIN("03", "관리자")
    ,MENTO("04", "멘토")
    ,MANAGER("05", "매니저")
    ;

    private final String code;
    private final String value;

    @Converter(autoApply = true) // 연결하는 애들이 여러군데다그럼 이거 쓰면 됨
    public static class CodeConverter extends AbstractEnumCodeConverter<EnumUserRole>{
        public CodeConverter() {super(EnumUserRole.class,false);}
        // 만약 어떤데는 null 허용이고 어떤데는 아니다 라고 하면 그냥 거기에서 연결

    }
}
