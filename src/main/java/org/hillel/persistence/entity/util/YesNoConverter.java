package org.hillel.persistence.entity.util;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

public class YesNoConverter implements AttributeConverter<Boolean, String> {

//    @Converter(autoApply = true)  // global set for all hibernate
    private enum YesNoType {
        YES("yes", true), NO("no", false);
        private final String dbValue;
        private final boolean entityValue;


        YesNoType(String dbValue, boolean entityValue) {
            this.dbValue = dbValue;
            this.entityValue = entityValue;
        }

        private static final YesNoType getByDBValue(String value) {
            if (StringUtils.isEmpty(value)) return NO;
            for (YesNoType type : values()) {
                if (Objects.equals(type.dbValue, value)) return type;
            }
            return NO;
        }

        private static final YesNoType getByDBValue(boolean entityValue) {
            if (Objects.isNull(entityValue)) return NO;
            for (YesNoType type : values()) {
                if (Objects.equals(type.entityValue, entityValue)) return type;
            }
            return NO;
        }
    }

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return YesNoType.getByDBValue(aBoolean).dbValue;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        return YesNoType.getByDBValue(dbValue).entityValue;
    }
}
