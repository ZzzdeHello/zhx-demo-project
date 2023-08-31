package project.myBatis.service_my;

import com.alibaba.druid.support.json.JSONUtils;
import project.myBatis.dao.local_dao.ProductDao;
import org.springframework.stereotype.Service;
import project.myBatis.service_api.TestMybatis;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class TestMyabtisImpl implements TestMybatis {

    @Resource
    private ProductDao productDao;
    @Override
    public String selectProduct() {
        List<Map> maps = productDao.selectProduct();
        Iterator<Map> iterator = maps.iterator();
        return JSONUtils.toJSONString(maps);
    }

//    @Override
//    public boolean saveToDb(List<ProductData> list) {
//        try {
//            for (ProductData product : list) productDao.insert(product);
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
}
