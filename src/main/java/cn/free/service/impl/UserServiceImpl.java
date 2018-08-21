package cn.free.service.impl;

import cn.free.dao.SaleMapper;
import cn.free.dao.UserMapper;
import cn.free.pojo.Sale;
import cn.free.pojo.SaleDetail;
import cn.free.pojo.User;
import cn.free.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Results;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

import static com.github.pagehelper.page.PageMethod.startPage;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Resource(name = "saleMapper")
    private SaleMapper saleMapper;

    @Override
    public int addRecord(Sale sale) {
        int a = 2;
        int row = userMapper.addRecord(sale);
        int num1 = userMapper.queryQuantity(sale.getProductId());
        int num2 = num1-sale.getQuantity();
        userMapper.destock(sale.getProductId(),num2);
        return row;
    }


    @Override
    public int queryQuantity(int productId) {
        int quantity = userMapper.queryQuantity(productId);
        return quantity;
    }

    @Override
    public PageInfo findAll(String obj,Integer pageNum,Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        System.out.println(pageNum);
        List<SaleDetail> saleDetailList = saleMapper.findAll(obj);
        System.out.println(saleDetailList);
        PageInfo pageInfo = new PageInfo(saleDetailList);
        System.out.println(pageInfo);
        return pageInfo;

    }

    @Override
    public User login(User user) {
        System.out.println(user+"login");

        user=userMapper.login(user);
        System.out.println(user+"service");
        return user;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }


}
