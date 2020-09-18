package com.ssl.manager.modules.ssl.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ssl.manager.modules.ssl.entity.Domain;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
public interface DomainService extends IService<Domain> {

    void add(Domain user);

    boolean update(Domain user);

}
