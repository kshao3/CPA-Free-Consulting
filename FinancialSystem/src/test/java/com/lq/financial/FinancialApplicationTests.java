package com.lq.financial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.lq.financial.mapper.AcceListMapper;
import com.lq.financial.mapper.HistoricalRecordMapper;
import com.lq.financial.mapper.UserMapper;
import com.lq.financial.pojo.AcceList;
import com.lq.financial.pojo.Metting;
import com.lq.financial.pojo.QueryInfo;
import com.lq.financial.pojo.User;
import com.lq.financial.service.IMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.DoubleToIntFunction;
import java.util.stream.Collectors;

@SpringBootTest
class FinancialApplicationTests {

    @Autowired
    private IMailService iMailService;

    @Autowired
    private AcceListMapper acceListMapper;

    @Autowired
    private HistoricalRecordMapper historicalRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
       /* List<Map<String,BigDecimal>> mapList = new ArrayList<>();
        Map<String,BigDecimal> map = null;
        String test ="{\"results\":[{\"objectID\":357,\n" +
                "                \"geoPoints\":[{\"x\":504604.59802246094,\"y\":305569.9150390625}]},\n" +
                "            {\"objectID\":358,\n" +
                "                    \"geoPoints\":[{\"x\":504602.2680053711,\"y\":305554.43603515625}]}]}";

        String result = JSONPath.read(test, "$.results.objectID[*]").toString();
        System.out.println(result);
        PageHelper pageHelper = new PageHelper();
        PageHelper.orderBy("a desc");*/


        String demo = "{\n" +
                "\t\"datagrid8List\": [{\n" +
                "\t\t\"id\": \"3b2cf56a-b2bf-4657-a7cd-ff93c35fe126\",\n" +
                "\t\t\"bmtotal\": 1,\n" +
                "\t\t\"retatol\": 0,\n" +
                "\t\t\"retotal\": 1,\n" +
                "\t\t\"sctotal\": 1,\n" +
                "\t\t\"category\": \"1\",\n" +
                "\t\t\"comments\": \"\",\n" +
                "\t\t\"\\t sctotal\": 0,\n" +
                "\t\t\"bmunitqty\": 1,\n" +
                "\t\t\"reunitqty\": 1,\n" +
                "\t\t\"scunitqty\": 1,\n" +
                "\t\t\"bmunitprice\": 1,\n" +
                "\t\t\"description\": \"1\",\n" +
                "\t\t\"reunitprice\": 1,\n" +
                "\t\t\"scunitprice\": 1,\n" +
                "\t\t\"subcategory\": \"1\"\n" +
                "\t}, {\n" +
                "\t\t\"id\": \"89898989-b2bf-4657-a7cd-ff93c35fe126\",\n" +
                "\t\t\"bmtotal\": 1,\n" +
                "\t\t\"retatol\": 0,\n" +
                "\t\t\"retotal\": 1,\n" +
                "\t\t\"sctotal\": 1,\n" +
                "\t\t\"category\": \"1\",\n" +
                "\t\t\"comments\": \"\",\n" +
                "\t\t\"\\t sctotal\": 0,\n" +
                "\t\t\"bmunitqty\": 1,\n" +
                "\t\t\"reunitqty\": 1,\n" +
                "\t\t\"scunitqty\": 1,\n" +
                "\t\t\"bmunitprice\": 1,\n" +
                "\t\t\"description\": \"1\",\n" +
                "\t\t\"reunitprice\": 1,\n" +
                "\t\t\"scunitprice\": 1,\n" +
                "\t\t\"subcategory\": \"1\"\n" +
                "\n" +
                "\t}]\n" +
                "}";

        JSONArray parseArrayLits = JSONObject.parseArray(JSONObject.parseObject(demo).get("datagrid8List").toString());
        List<Metting> mettings = parseArrayLits.toJavaList(Metting.class);
        System.out.println(mettings);
       /* for (int i = 0; i < parseArrayLits.size(); i++) {
            JSONObject jsonObject = parseArrayLits.getJSONObject(i);

        } */

        /*JSONArray parseArrayLits = JSONObject.parseArray(.toString());
        System.out.println(parseArrayLits);*/


      /*  JSONObject jsonObject = JSONObject.parseObject(test);
        JSONArray objects = JSON.parseArray(jsonObject.getString("results"));
        System.out.println("原数据：" + objects);
        for (int i = 0; i < objects.size(); i++) {
            map = new LinkedHashMap<>();
            JSONObject jsonObject1 = objects.getJSONObject(i);
            String objectID = jsonObject1.getString("objectID");
            JSONArray geoPoints = jsonObject1.getJSONArray("geoPoints");
            JSONObject jsonObject2 = geoPoints.getJSONObject(0);
            BigDecimal x =   BigDecimal.valueOf(Double.parseDouble(jsonObject2.getString("x")));
            map.put("x",x);
            BigDecimal y =   BigDecimal.valueOf(Double.parseDouble(jsonObject2.getString("y")));
            map.put("y",y);
            mapList.add(map);
        }
        System.out.println(mapList);*/

    }

}
