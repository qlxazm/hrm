package org.java.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.java.hrm.domain.Message;
import org.java.hrm.domain.User;

import static org.java.hrm.util.common.HrmConstants.MESSAGETABLE;

public class MessageDynaSqlProvider {

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
       /* SELECT id, content, source, target, releaseTime FROM `messages` WHERE (target = 'all' OR target = '超级管理员' ) AND id NOT IN (
                SELECT messageId FROM user_message WHERE username = '超级管理员'
)*/

        return null;
    }
}
