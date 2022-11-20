package com.lq.financial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.financial.pojo.AcceList;
import com.lq.financial.pojo.QueryInfo;

import java.util.List;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-18  16:16
 * @Description: TODO
 * @Version: 1.0
 */
public interface AcceListMapper extends BaseMapper<AcceList> {

    public List<AcceList> selectAllByLike(QueryInfo query);

    public List<AcceList> selectAllByAll(QueryInfo query);

}
