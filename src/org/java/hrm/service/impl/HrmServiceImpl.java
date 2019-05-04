package org.java.hrm.service.impl;

import org.java.hrm.dao.UserDao;
import org.java.hrm.domain.User;
import org.java.hrm.service.HrmService;
import org.java.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*使用事务数据库的默认的事务隔离级别*/
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {

    /*     ==============================  用户部分       */
    @Autowired
    private UserDao userDao;  //自动注入UserDao

    /**
     * 添加新用户
     * @param user
     * @return
     */
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    /**
     * 分页查询用户
     * @param user
     * @param pageModel
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        int recordCount = userDao.count(params);
        System.out.println("查询符合条件的用户总数 -- >> " + recordCount);
        pageModel.setRecordCount(recordCount);

        if (recordCount > 0){
            params.put("pageModel", pageModel);
        }
        return userDao.selectByPage(params);
    }

    /**
     * 登录
     * @param loginname
     * @param password
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
        System.out.println("HrmServiceImpl -- >> login");
        return userDao.selectByLoginnameAndPassword(loginname, password);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        System.out.println("HrmServiceImpl -- >> findUserById");
        return userDao.selectById(id);
    }
}
