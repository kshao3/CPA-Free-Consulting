package com.lq.financial.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: LQ
 * @CreateTime: 2022-10-20  22:57
 * @Description: TODO
 * @Version: 1.0
 */
@TableName(value = "historicalrecord")
@Data
public class HistoricalRecord {
    private int id;
    private int uid;
    private int accelistid;
}
