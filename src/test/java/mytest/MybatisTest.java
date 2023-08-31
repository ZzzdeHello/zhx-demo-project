package mytest;

import com.github.pagehelper.PageHelper;
import project.myBatis.dao.pst2_dao.TestDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisTest extends BaseTest {

    @Autowired
    TestDao testDao;

    @Test
    public void test01 (){
        PageHelper.startPage(0,10,false);
        testDao.selectItem();
    }
}
