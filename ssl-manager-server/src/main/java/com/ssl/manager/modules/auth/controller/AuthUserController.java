package com.ssl.manager.modules.auth.controller;


import com.ssl.manager.modules.usersphere.entity.User;
import com.ssl.manager.modules.usersphere.service.UserService;
import com.ssl.manager.security.vo.LoginUser;
import com.ssl.manager.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto generator
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/auth/user")
@AllArgsConstructor
public class AuthUserController {

    private final UserService userService;

    /**
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<?> getInfo(Authentication authentication) {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = userService.getById(loginUser.getId());
        return Result.success("",user);
    }

}
