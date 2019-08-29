package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.java.hrm.domain.Message;
import org.java.hrm.domain.User;

import static org.java.hrm.util.common.HrmConstants.MESSAGETABLE;
import static org.java.hrm.util.common.HrmConstants.USERMESSAGETABLE;

public class MessageDynaSqlProvider {

    private static final String MESSAGE_TYPE_OF_ALL = "all";

    /**
     * 新增消息
     * @param message
     * @return
     */
    public String insertMessage(Message message) {
        String sql = new SQL(){
            {
                INSERT_INTO(MESSAGETABLE);
                if (message.getContent() != null && !"".equals(message.getContent())) {
                    VALUES("content", "#{content}");
                }
                if (message.getSource() != null && !"".equals(message.getSource())) {
                    VALUES("source", "#{source}");
                }
                if (message.getTarget() != null && !"".equals(message.getTarget())) {
                    VALUES("target", "#{target}");
                }
                VALUES("releaseTime", "current_timestamp");
            }
        }.toString();
        return sql;
    }

    public String selectUnreadMessages(User user) {
        String sql = new SQL(){
            {
                SELECT("id, content, source, target, releaseTime");
                FROM(MESSAGETABLE);
                WHERE("target = '" + MESSAGE_TYPE_OF_ALL + "' OR target = #{username}");
                AND();
                WHERE("id NOT IN (SELECT messageId FROM " + USERMESSAGETABLE + " WHERE username = #{username})");

            }
        }.toString();
        return sql;
    }
}
