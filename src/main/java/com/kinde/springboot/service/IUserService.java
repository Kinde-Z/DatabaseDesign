package com.kinde.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kinde.springboot.controller.dto.UserDTO;
import com.kinde.springboot.entity.User;

public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
