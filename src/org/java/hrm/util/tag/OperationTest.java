package org.java.hrm.util.tag;

import org.java.hrm.domain.Operation;

import java.util.List;

public class OperationTest {
    /**
     * 测试operation是否禁止访问
     * @param operation           待测试的operation
     * @param prohibitOperation  禁止访问的operation的列表
     * @return
     */
    public static Boolean test(String operation, List<Operation> prohibitOperation) {
        if (prohibitOperation != null) {
            for (Operation opera : prohibitOperation) {
                if (operation.indexOf(opera.getUrl()) >= 0){
                    return false;
                }
            }
        }
        return true;
    }
}
