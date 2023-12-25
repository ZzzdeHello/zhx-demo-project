package zzzde.project.technic.projmybatis.persistent.dao.pst_dao;

import zzzde.project.technic.projmybatis.persistent.dao.BaseDao;
import zzzde.project.technic.projmybatis.persistent.entity.ChOperatorsInfoMonthDo;

import java.util.List;
import java.util.Map;

public interface ChOperationMonthDao extends BaseDao<ChOperatorsInfoMonthDo,String> {
    List<Map> selectOneItem();
}
