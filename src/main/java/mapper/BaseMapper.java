package mapper;

import java.util.List;
import empty.Page;

public interface BaseMapper<T> {
	//添加单个对象
	int insert(T entity);
	
	//修改单个对象
	int update(T entity);
	
	//删除单个对象 
	int delete(T entity);
	
	//通过主键批量删除数据
	int deleteList(String[] pks);
	
	//查询单个对象
	T select(T entity);
	
	//通过关键字分页查询数据列表
	List<T> selectPageList(Page<T> page);
	
	//通过关键字分页查询，返回总记录数
	Integer selectPageCount(Page<T> page);
	
	//通过关键字分页查询数据列表
	List<T> selectPageListUseDyc(Page<T> page);
		
	//通过关键字分页查询，返回总记录数
	Integer selectPageCountUseDyc(Page<T> page);
	
	

}
