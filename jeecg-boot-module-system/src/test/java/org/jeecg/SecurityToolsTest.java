package org.jeecg;

import cn.hutool.json.JSONObject;
import org.jeecg.common.util.security.SecurityTools;
import org.jeecg.common.util.security.entity.*;
import org.jeecg.modules.gongyiguanli.entity.PorcessTable;
import org.jeecg.modules.gongyiguanli.service.IPorcessTableService;
import org.jeecg.modules.gongyiguanli.service.impl.PorcessTableServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SecurityToolsTest {
    @Autowired
    IPorcessTableService iPorcessTableService;

    @Autowired
    PorcessTable porcessTable;

    @Test
    public void add(){

    }
}
