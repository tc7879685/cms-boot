package com.finance.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finance.modules.model.UserModel;
import com.finance.modules.system.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper接口
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 通过用户账号查询用户信息
     * @param userName 如若前端后端对应一致，则可以不用谢@Param("userName")
     * 当你使用了使用@Param注解来声明参数时，如果使用 #{} 或 ${} 的方式都可以。
     * 当你不使用@Param注解来声明参数时，必须使用使用 #{}方式。如果使用 ${} 的方式，会报错。
     * 不使用@Param注解时，参数只能有一个，并且是Javabean。在SQL语句里可以引用JavaBean的属性，而且只能引用JavaBean的属性
     * @Select("SELECT * from Table where id = ${id}")
     * Enchashment selectUserById(User user);
     * @return
     */
     UserInfo getUserByName(@Param("userName")String userName,@Param("corpCode")String corpCode);


    public List<UserModel> getUserList(Page page, UserInfo userInfo);

}
