package org.java.hrm.myException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 添加用户异常，映射为404
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddUserException extends RuntimeException {
}
