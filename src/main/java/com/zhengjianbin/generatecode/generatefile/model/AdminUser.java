package  com.zhengjianbin.generatecode.generatefile.model;

import lombok.Data;
import java.util.Date;

@Data
public class AdminUser {

    private Integer id;
    private String account;
    private String pwd;
    private String uName;
    private String phone;
    private String email;
    private Integer status;
    private Date createTime;
    private String token;
    private Date expiredTime;
    private Integer delStatus;
    private Date loginTime;

}