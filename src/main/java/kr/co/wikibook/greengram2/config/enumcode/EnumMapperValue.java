package kr.co.wikibook.greengram2.config.enumcode;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class EnumMapperValue {
    private String code;
    private String value;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.code = enumMapperType.getCode();
        this.value = enumMapperType.getValue();
    }
}
