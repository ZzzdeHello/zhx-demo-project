package project.myBatis.dao.pst_dao;

import project.myBatis.dao.BaseDao;
import project.myBatis.model.ChOperatorsInfoMonthDo;

import java.util.List;
import java.util.Map;

public interface ChOperationMonthDao extends BaseDao<ChOperatorsInfoMonthDo,String> {
    List<Map> selectOneItem();
}
