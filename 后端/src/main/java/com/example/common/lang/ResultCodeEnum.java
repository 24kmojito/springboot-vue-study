package com.example.common.lang;

public enum ResultCodeEnum {

    OPERATOR_SUCCESS("200","操作成功"),
    PARA_BLANK_FAILURE("1000","部分输入数据为空"),
    DB_FIND_SUCCESS("1001","数据库查找成功"),
    DB_FIND_FAILURE("1002","数据库查找失败，没有该条记录"),
    DB_UPDATE_SUCCESS("1003","数据更新成功"),
    DB_UPDATE_FAILURE("1004","数据更新失败"),
    DB_DELETE_SUCCESS("1005","数据删除成功"),
    DB_DELETE_FAILURE("1006","数据删除失败"),
    DB_ADD_SUCCESS("1007","数据增加成功"),
    DB_ADD_FAILURE("1008","数据增加失败");

    private String code;
    private String msg;
    ResultCodeEnum(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    public String getCode()
    {
        return code;
    }
    public String getMsg()
    {
        return msg;
    }
}
