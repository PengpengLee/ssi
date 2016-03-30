package cn.itcast.ssi.common.entity;

import java.util.List;

/**
 * <p>分页数据结果集封装类</p>
 * 用于封装分页数据，提供前台使用
 * 
 * @param <T> 数据对象类型，需要在使用时指定
 * @author lipp
 * @date 2015-01-19
 */
public class PageResultSet<T> {
	/** 当前页 */
	private Integer page = 1;
	
	/** 每页记录数 */
	private Integer rows = 10;
	
	/** 总数据数 */
	private Integer total = 0;

	/** 数据列表 */
	private List<T> data;
	
	
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}


}
