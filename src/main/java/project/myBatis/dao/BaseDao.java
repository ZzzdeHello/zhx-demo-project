package project.myBatis.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T,PK>{
	
	List<T> getPageList(@Param("vo") T vo, @Param("start") Integer start, @Param("limit") Integer limit);
	
	Integer count(@Param("vo") T vo);
	
	List<T> query(@Param("vo") T vo);
	
	List<T> queryProcess(@Param("vo") T vo);

	T load(@Param("rowId") PK id);

	void insert(@Param("vo") T vo);
	
	void update(@Param("vo") T vo);

	void delete(@Param("rowId") PK id);
	
}
