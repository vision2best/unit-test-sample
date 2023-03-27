package life.hzzh.unit.test.controller;

import life.hzzh.unit.test.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@WebMvcTest
//你甚至还可以告诉框架只启动某一个 controller 这样更快，比如：
//@WebMvcTest(HelloController.class)
//public class ControllerTest{
public class ControllerTest extends ApplicationTest {


    // 注入Spring容器
    @Autowired
    private WebApplicationContext applicationContext;
    // 模拟Http请求
    private MockMvc mockMvc;


    @BeforeEach
    public void setupMockMvc() {
        // 初始化MockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    /**
     * 新增学生测试用例
     */
    @Test
    public void addStudent() throws Exception {
        String json = "{\"name\":\"张三\",\"className\":\"三年级一班\",\"age\":\"20\",\"sex\":\"男\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/student/save")    //构造一个post请求
                        // 发送端和接收端数据格式
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json.getBytes())
                )
                // 断言校验返回的code编码
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 添加处理器打印返回结果
                .andDo(MockMvcResultHandlers.print());
    }

}
