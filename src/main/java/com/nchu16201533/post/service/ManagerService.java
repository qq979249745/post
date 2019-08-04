package com.nchu16201533.post.service;

import com.nchu16201533.post.bean.Manager;
import com.nchu16201533.post.bean.ManagerExample;
import com.nchu16201533.post.dao.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 16201533
 * @Date: 2019/5/29 18:57
 * @Version 1.0
 */
@Service
public class ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    public List<Manager> queeryManager(Manager manager){
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria().andAccountEqualTo(manager.getAccount()).andPasswordEqualTo(manager.getPassword());
        return managerMapper.selectByExample(managerExample);
    }
}
