package com.lq.financial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.financial.pojo.AcceList;
import com.lq.financial.pojo.HistoricalRecord;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-20  22:57
 * @Description: TODO
 * @Version: 1.0
 */
public interface HistoricalRecordMapper extends BaseMapper<HistoricalRecord> {

    @Select("SELECT * FROM accelist,historicalrecord WHERE accelist.`id` = historicalrecord.`accelistid` AND historicalrecord.`uid` = #{uid}")
    List<AcceList> showHistoricalAll(int uid);

}
