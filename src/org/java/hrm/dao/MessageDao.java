package org.java.hrm.dao;

import org.apache.ibatis.annotations.*;
import org.java.hrm.dao.provider.MessageDynaSqlProvider;
import org.java.hrm.domain.Message;
import org.java.hrm.domain.User;
import org.java.hrm.params.UserMessagesParam;
import static org.java.hrm.util.common.HrmConstants.MESSAGETABLE;
import static org.java.hrm.util.common.HrmConstants.USERMESSAGETABLE;

import java.util.List;

public interface MessageDao {

    /**
     * 新增message
     * @param message
     */
    @InsertProvider(type = MessageDynaSqlProvider.class, method = "insertMessage")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void save(Message message);

    /**
     * 查询指定用户的未读消息
     * @param user
     * @return
     */
    @SelectProvider(type = MessageDynaSqlProvider.class, method = "selectUnreadMessages")
    @Results(id="messageResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "source", column = "source"),
            @Result(property = "target", column = "target"),
            @Result(property = "releaseTime", column = "releaseTime")
    })
    List<Message> selectUnreadMessages(User user);

    /**
     * 根据id查询消息的详细内容
     * @param id
     * @return
     */
    @Select("SELECT id, content, source, target, releaseTime FROM " + MESSAGETABLE + " WHERE id=#{id}")
    Message getMessageById(@Param("id") Integer id);

    /**
     * 将消息设置为读取状态
     * @param params
     */
    @Insert("INSERT INTO " + USERMESSAGETABLE + "(username, messageId) VALUES(#{username},#{messageId})")
    void readMessage(UserMessagesParam params);
}
