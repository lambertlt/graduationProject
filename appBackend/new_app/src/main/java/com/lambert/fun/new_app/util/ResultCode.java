package com.lambert.fun.new_app.util;

import java.util.HashMap;
import java.util.Map;

public enum ResultCode {
    /* 常用的返回状态 */
    OK(200, "服务器成功返回用户请求的数据"),
    CREATED(201, "用户新建或修改数据成功"),
    ACCEPTED(202, "请求已经进入后台排队"),
    NO_CONTENT(204, "用户删除数据成功"),
    INVALID_REQUEST(400, "用户发出的请求有错误，服务器没有进行新建或修改数据的操作"),
    UNAUTHORIZED(401, "用户没有权限"),
    FORBIDDEN(403, "用户得到授权，但是访问是被禁止的"),
    NOT_FOUND(404, "用户发出的请求针对的是不存在的记录，服务器没有进行操作"),
    NOT_ACCEPTABLE(406, "用户请求的格式不可得"),
    GONE(410, "用户请求的资源被永久删除，且不会再得到的"),
    UNPROCESSABLE_ENTITY(422, "创建一个对象时，发生一个验证错误"),
    INTERNAL_SERVER_ERROR(500, "服务器发生错误"),

//    /* 参数错误：10001-19999 */
//    PARAM_IS_INVALID(10001, "参数无效"),
//    PARAM_IS_BLANK(10002, "参数为空"),
//    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
//    PARAM_NOT_COMPLETE(10004, "参数缺失"),
//
//    /* 用户错误：20001-29999*/
//    USER_NOT_LOGGED_IN(20001, "用户未登录"),
//    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
//    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
//    USER_NOT_EXIST(20004, "用户不存在"),
//    USER_HAS_EXISTED(20005, "用户已存在"),
//
//    /* 业务错误：30001-39999 */
//    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),
//
//    /* 系统错误：40001-49999 */
//    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
//
//    /* 数据错误：50001-599999 */
//    RESULE_DATA_NONE(50001, "数据未找到"),
//    DATA_IS_WRONG(50002, "数据有误"),
//    DATA_ALREADY_EXISTED(50003, "数据已存在"),
//
//    /* 接口错误：60001-69999 */
//    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
//    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
//    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
//    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
//    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
//    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),
//
//    /* 权限错误：70001-79999 */
//    PERMISSION_NO_ACCESS(70001, "无访问权限"),


    ;
//    200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
//    201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
//    202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
//    204 NO CONTENT - [DELETE]：用户删除数据成功。
//    400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
//    401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
//    403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
//    404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
//    406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
//    410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
//    422 Unprocessable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
//    500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
//    502 网关错误
//    503 Service Unavailable
//    504 网关超时

    private Integer code; // 状态码
    private String msg; // 返回消息

    // 构造器
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // 获取消息
    public static String getMsg(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.msg.equals(name)) {
                return item.msg;
            }
        }
        return name;
    }

    // 获取状态码
    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.msg.equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    /* get/set 方法 */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", this.msg);
        map.put("code", this.code);
        return map;
    }

    @Override
    public String toString() {
        return this.code + this.msg;
    }
}
