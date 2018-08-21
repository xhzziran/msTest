package cn.free.dao;

import cn.free.pojo.SaleDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "saleMapper")
public interface SaleMapper {

    public List<SaleDetail> findAll(@Param("obj") String obj);
}
