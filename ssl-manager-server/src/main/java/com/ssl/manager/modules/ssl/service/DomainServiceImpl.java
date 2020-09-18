package com.ssl.manager.modules.ssl.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssl.manager.modules.ssl.entity.Domain;
import com.ssl.manager.modules.ssl.mapper.DomainMapper;
import lombok.AllArgsConstructor;
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
public class DomainServiceImpl extends ServiceImpl<DomainMapper, Domain> implements DomainService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Domain domain) {
        this.save(domain);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Domain domain) {
        this.updateById(domain);
        return false;
    }

}
