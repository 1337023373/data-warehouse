package com.ggzed.im.model.enums;

// 枚举类定义（可根据需要放在单独文件中）
    public enum RepoTypeEnum {
        MYSQL("MySQL"),
        ORACLE("Oracle"),
        POSTGRESQL("PostgreSQL"),
        SQL_SERVER("SQL Server");

        private final String value;

        RepoTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }