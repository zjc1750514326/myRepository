package empty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 337297181251071639L;

	//当前页 easyUI页面提供
	private Integer page;
	//页大小 easyUI页面提供
	private Integer rows;
	// 总记录数 数据库查询提供
	private Integer totalRecord;
	//页面数据列表  数据库查询提供
	private List<T> list;
	//查询关键字 easyUI页面提供  改用多条件查询了
	private String keyWord;
	//多条件查询 easyUI页面提供
	private T paramEntity;
	//需要这里处理
	private Integer start;
	// map中存放着easyUI页面需要的 总记录数(totalRecord) 和 记录列表(list)
	private Map<String, Object> pageMap = new HashMap<String, Object>() ;

	// 总页数
	private Integer totalPage;

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage() {

		this.totalPage = totalRecord % rows == 0?(totalRecord / rows):(totalRecord / rows)+1;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public void setParamEntity(T paramEntity) {
		this.paramEntity = paramEntity;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setTotalRecord(Integer totalRecord) {
		pageMap.put("total", totalRecord);
		this.totalRecord = totalRecord;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setList(List<T> list) {
		pageMap.put("rows", list);
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public Integer getStart() {
		this.start = (page-1)*rows;
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "Page [page=" + page + ", rows=" + rows + ", totalRecord="
				+ totalRecord + ", list=" + list + ", keyWord=" + keyWord
				+ ", paramEntity=" + paramEntity + ", start=" + start + "]";
	}
}
