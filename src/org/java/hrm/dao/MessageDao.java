package org.java.hrm.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.java.hrm.dao.provider.MessageDynaSqlProvider;
import org.java.hrm.domain.Message;
import org.java.hrm.domain.User;

import java.util.List;

public interface MessageDao {

    /**
     * 新增message
     * @param message
     */
    @InsertProvider(type = MessageDynaSqlProvider.class, method = "insertMessage")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void save(Message message);


    List<Message> selectUnreadMessages(User user);
}
