package com.example.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static Result succ(Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }

    public static Result succ(String mess, Object data) {
         Result m = new Result();
         m.setCode("0");
         m.setData(data);
         m.setMsg(mess);
         return m;
    }
    public static Result fail(String mess) {
         Result m = new Result();
         m.setCode("-1");
         m.setData(null);
         m.setMsg(mess);
         return m;
    }
    public static Result fail(String mess, Object data) {
         Result m = new Result();
         m.setCode("-1");
         m.setData(data);
         m.setMsg(mess);
        return m;
    }

    public static Result fail(String code,String mess, Object data) {
        Result m = new Result();
        m.setCode(code);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }




//    private String code;
//    private String msg;
//    private Object data;
//
//    public void setResult(ResultCodeEnum code)
//    {
//        this.setCode(code.getCode());
//        this.setMsg(code.getMsg());
//    }
//
//    public void setData(Object data)
//    {
//        this.setData(data);
//    }
//
//    public static Result GetResultSuccessfully(Object data)
//    {
//        return GetResult("200","操作成功",data);
//    }
//
//    public static Result GetResult(String code,String msg,Object data)
//    {
//        Result result = new Result();
//        result.setCode(code);
//        result.setMsg(msg);
//        result.setData(data);
//
//        return result;
//    }

}
