package com.atguigu.spring5.service;


import com.atguigu.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,timeout = -1) //propagation传播行为，isolation隔离级别
public class UserService {

    @Autowired
    private UserDao userDao;

    public void accountMoney() {

            //lucy少100
            userDao.reduceMoney();

            //模拟异常
            int i = 10/0;

            //mary多100
            userDao.addMoney();

    }
}
