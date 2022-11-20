package com.lq.financial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.financial.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-09  14:14
 * @Description: TODO
 * @Version: 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("UPDATE users SET integral = integral + 1 WHERE id = #{id}")
    int updateIntegral(int id);


    @Update("UPDATE users SET integral = integral - 1 WHERE id = #{id}")
    int updateSubtract(int id);
}
