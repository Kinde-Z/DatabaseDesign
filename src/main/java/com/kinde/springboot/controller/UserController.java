package com.kinde.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinde.springboot.common.Constants;
import com.kinde.springboot.common.Result;
import com.kinde.springboot.controller.dto.UserDTO;
import com.kinde.springboot.entity.User;
import com.kinde.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    private Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        //新增或修改
        return Result.success(userService.register(userDTO));
    }

    //新增和修改
    @PostMapping()
    private Result save(@RequestBody User user){
        //新增或修改
        return Result.success(userService.saveOrUpdate(user));
    }
    //查询所有数据
    @GetMapping
    public List<User> findAll(){
        return userService.list();
    }

    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
    }
    //批量删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(userService.removeByIds(ids));
    }

//    @GetMapping("/page")
//    private Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                         @RequestParam Integer pageSize,
//                                         @RequestParam String username){
//        pageNum = (pageNum - 1)*pageSize;
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
//        Integer total = userMapper.selectTotal(username);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }
    //分页查询 -- mybatis-plus
    @GetMapping("/page")
    private Result findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String username,
                                 @RequestParam(defaultValue = "") String email,
                                 @RequestParam(defaultValue = "") String address){
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username", username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email", email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address", address);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(userService.page(page, queryWrapper));
    }
}
