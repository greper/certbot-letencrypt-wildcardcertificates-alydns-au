package com.ssl.manager.modules.usersphere.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssl.manager.modules.base.controller.AbstractCrudController;
import com.ssl.manager.modules.usersphere.entity.User;
import com.ssl.manager.modules.usersphere.service.UserService;
import com.ssl.manager.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/manager/usersphere/user")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserController extends AbstractCrudController {

    private final UserService userService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @GetMapping("/page")
    public Result getUserPage(Page<User> page, User query
            , @RequestParam(value = "dateRange", required = false) String dateRange
    ) {

        setDefaultSort(page, "id", true);
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(query);
        Page<User> ret = userService.page(page, wrapper);
        return Result.success("", ret);
    }

    /**
     * 通过id查询
     *
     * @param id id
     * @return Result
     */
    @GetMapping("/info")
    public Result getById(@RequestParam("id") Integer id) {
        User user = userService.getById(id);
        user.setPassword(null);
        return Result.success("", user);
    }

    /**
     * 新增
     *
     * @param user
     * @return Result
     */
    @PostMapping("/add")
    public Result save(@RequestBody User user) {
        userService.add(user);
        return Result.success("", user.getId());
    }

    /**
     * 修改
     *
     * @param user
     * @return Result
     */
    @PostMapping("/update")
    public Result updateById(@RequestBody User user) {

        boolean success = userService.update(user);
        return Result.success("", success);
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return Result
     */
    @PostMapping("/delete")
    public Result removeById(@RequestParam Long id) {
        return Result.success("", userService.removeById(id));
    }




}
