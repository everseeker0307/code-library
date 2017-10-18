package me.wuhao.multi_datasource.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by everseeker on 2017/9/1.
 */
@Controller
public class HVRController {
    @Autowired
    @Qualifier("hvrJdbcTemplate")
    private JdbcTemplate hvrJdbcTemplate;

    @Autowired
    @Qualifier("wuhaoJdbcTemplate")
    private JdbcTemplate wuhaoJdbcTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public String data() {
        List<Map<String, Object>> result = hvrJdbcTemplate.queryForList("select his.cal_total_floating_income_n_test(20161101, '25025798')");
        return new Gson().toJson(result);
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String data2() {
        List<Map<String, Object>> result = wuhaoJdbcTemplate.queryForList("select * from test2");
        return new Gson().toJson(result);
    }
}
