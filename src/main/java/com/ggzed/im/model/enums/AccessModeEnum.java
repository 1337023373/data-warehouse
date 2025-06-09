package com.ggzed.im.model.enums;

public enum AccessModeEnum {
        NATIVE("Native"),
        JNDI("JNDI"),
        ODBC("ODBC");

        private final String value;

        AccessModeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }