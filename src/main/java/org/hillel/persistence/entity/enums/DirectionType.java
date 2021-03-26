package org.hillel.persistence.entity.enums;

public enum DirectionType {
    FROM(1), TO(2), UNKNOW(3);

    private final int code;

    DirectionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
