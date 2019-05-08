package org.java.hrm.util.common;

public class HrmConstants {
    //数据库表常量
    public static final String USERTABLE = "user_inf";
    public static final String DEPTTABLE = "dept_inf";
    public static final String JOBTABLE = "job_inf";
    public static final String EMPLOYEETABLE = "employee_inf";
    public static final String NOTICETABLE = "notice_inf";
    public static final String DOCUMENTTABLE = "document_inf";
    //关于用户权限控制的表
    public static final String ROLETABLE = "rbac_role";
    public static final String PERMISSIONTABLE = "rbac_permission";
    public static final String ELEMENTTABLE = "rbac_element";
    public static final String FILETABLE = "rbac_file";
    public static final String MENUTABLE = "rbac_menu";
    public static final String OPERATIONTABLE = "rbac_operation";
    //关于用户权限控制的中间表
    public static final String USERROLETABLE = "rbac_user_role";
    public static final String PERMISSIONELEMENTTABLE = "rbac_permission_element";
    public static final String PERMISSIONFILETABLE = "rbac_permission_file";
    public static final String PERMISSIONMENUTABLE = "rbac_permission_menu";
    public static final String PERMISSIONOPERATIONTABLE = "rbac_permission_operation";
    public static final String ROLEPERMISSIONTABLE = "rbac_role_permission";

    //登录
    public static final String LOGIN = "loginForm";
    //用户的session对象
    public static final String USER_SESSION = "user_session";
    //用户权限范围内的操作的session对象
    public static final String USER_OPERATION_SESSION = "user_operation";
    //默认的分页大小
    public static int  PAGE_DEFAULT_SIZE = 15;
}
