package com.nchu16201533.post;

import com.nchu16201533.post.bean.Manager;
import com.nchu16201533.post.dao.ManagerMapper;
import com.nchu16201533.post.util.EncryptBasedDes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostApplicationTests {
    @Autowired
    private ManagerMapper managerMapper;
    @Test
    public void contextLoads() {
        Manager manager = new Manager();
        manager.setAccount("admin");
        manager.setPassword(EncryptBasedDes.encryptBasedDes("admin"));
        System.out.println(managerMapper.insertSelective(manager));;
    }

}
