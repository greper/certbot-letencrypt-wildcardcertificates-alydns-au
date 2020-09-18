package com.ssl.manager.modules.usersphere.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ssl.manager.modules.usersphere.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
public interface UserService extends IService<User> {

    void add(User user);

    boolean update(User user);

    User getByUsername(String username);

    boolean verifyPassword(User user, String password);
}
