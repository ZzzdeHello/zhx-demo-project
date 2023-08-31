package project.myBatis.service_pst;

import project.myBatis.dao.pst_dao.ChOperationMonthDao;
import project.myBatis.service_api.TestPstEvn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TestPstEvnImpl implements TestPstEvn {

    @Resource
    private ChOperationMonthDao chOperationMonthDao;
    @Override
    public String testPstEvn() {
       // Map result = new HashMap<>();
        List<Map> maps = chOperationMonthDao.selectOneItem();
        return maps.toString();
    }
}
