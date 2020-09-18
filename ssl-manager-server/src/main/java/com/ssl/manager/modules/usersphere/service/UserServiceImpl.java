package com.ssl.manager.modules.usersphere.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssl.manager.modules.usersphere.entity.User;
import com.ssl.manager.modules.usersphere.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(User user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(RandomUtil.randomString(6));
        }
        generatePassword(user);
        this.save(user);
    }

    private void generatePassword(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(User user) {
        if(StringUtils.isNotBlank(user.getPassword())){
            generatePassword(user);
        }else{
            user.setPassword(null);
        }
        user.setUsername(null);//禁止修改用户名
        this.updateById(user);
        return false;
    }

    @Override
    public User getByUsername(String username) {
        if(StringUtils.isBlank(username)){
            return null;
        }
        User query = new User();
        query.setUsername(username);
        return baseMapper.selectOne(Wrappers.lambdaQuery(query));
    }

    @Override
    public boolean verifyPassword(User user, String rawPassword) {
        String encodePassword = passwordEncoder.encode(rawPassword);
        if(StrUtil.equals(encodePassword,user.getPassword())){
            return true;
        }
        return false;
    }


}
