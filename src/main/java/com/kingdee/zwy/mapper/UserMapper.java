package com.kingdee.zwy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kingdee.zwy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description 测试
 * @Author HUZHAOYANG
 * @Date 2019/6/27 17:07
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //public User findById(@Param("id") String id);

}
