package cn.free.service;

import cn.free.pojo.Sale;
import cn.free.pojo.SaleDetail;
import cn.free.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    public User login(User user);
    public void insert(User user);

    public int addRecord(Sale sale);
    public int queryQuantity(int productId);
    public PageInfo findAll(String obj,Integer pageNum,Integer pageSize);
}
