package com.genx.druid.service;

import com.genx.druid.entitiy.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: genx
 * @date: 2019/3/5 23:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/*.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
        userService.findAll();
    }

    @Test
    public void getModel() {
        userService.getModel(1L);
    }

    @Test
    public void save() {

        UserEntity model = new UserEntity();
        model.setUsername(RandomStringUtils.randomAlphanumeric(16));
        userService.save(model);
    }
}