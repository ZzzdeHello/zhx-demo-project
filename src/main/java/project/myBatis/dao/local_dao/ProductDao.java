package project.myBatis.dao.local_dao;


import project.myBatis.dao.BaseDao;
import project.myBatis.model.ProductDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductDao extends BaseDao<ProductDo, String> {

    List<Map> selectProduct();
    List<Map> selectProductOrShopProduct(@Param("flag") String flag);
    void insert(@Param("vo") ProductDo vo);
}