package zzzde.project.technic.projmybatis.serevice.impl.t;

import zzzde.project.technic.projmybatis.persistent.dao.pst_dao.ChOperationMonthDao;
import zzzde.project.technic.projmybatis.serevice.TestPstEvn;
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
