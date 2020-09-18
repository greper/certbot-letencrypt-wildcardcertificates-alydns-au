package com.ssl.manager.modules.ssl.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssl.manager.modules.base.controller.AbstractCrudController;
import com.ssl.manager.modules.ssl.entity.Domain;
import com.ssl.manager.modules.ssl.service.DomainService;
import com.ssl.manager.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/manager/ssl/domain")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DomainController extends AbstractCrudController {

    private final DomainService domainService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @GetMapping("/page")
    public Result getUserPage(Page<Domain> page, Domain query
            , @RequestParam(value = "dateRange", required = false) String dateRange
    ) {

        setDefaultSort(page, "id", true);
        LambdaQueryWrapper<Domain> wrapper = Wrappers.lambdaQuery(query);
        Page<Domain> ret = domainService.page(page, wrapper);
        return Result.success("", ret);
    }

    /**
     * 通过id查询
     *
     * @param id id
     * @return Result
     */
    @GetMapping("/info")
    public Result<Domain> getById(@RequestParam("id") Integer id) {
        Domain domain = domainService.getById(id);
        return Result.success("", domain);
    }

    /**
     * 新增
     *
     * @return Result
     */
    @PostMapping("/add")
    public Result<Integer> save(@RequestBody Domain user) {
        domainService.add(user);
        return Result.success("", user.getId());
    }

    /**
     * 修改
     *
     * @return Result
     */
    @PostMapping("/update")
    public Result<?> updateById(@RequestBody Domain user) {

        boolean success = domainService.update(user);
        return Result.success("", success);
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return Result
     */
    @PostMapping("/delete")
    public Result<?> removeById(@RequestParam Long id) {
        return Result.success("", domainService.removeById(id));
    }




}
