package cn.free.dao;

import cn.free.pojo.Product;
import cn.free.pojo.Sale;
import cn.free.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userMapper")
public interface UserMapper {

    public User login(User user);
    public int insert(User user);
    public int addRecord(Sale sale);
    public int queryQuantity(int productId);
    public int destock(@Param(value = "productId") int productId, @Param(value = "num") int num);

}
