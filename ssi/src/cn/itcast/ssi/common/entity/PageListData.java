package cn.itcast.ssi.common.entity;


/**
 * <p>分页列表数据对象类</p>
 * 用于封装分页数据，提供前台使用
 * 
 * @param <T> 数据对象类型，需要在使用时指定
 * @author lipp
 * @date 2015-01-19
 */
public class PageListData {
	
	/** 当前页 */
	private Integer page = 1;
	
	/** 每页记录数 */
	private Integer rows = 10;
	
	/** 排序字段 */
	private String sidx = "createTime";
	
	/** 排序方式 */
	private String sord = "desc";
	
	
	/** 分页开始位置 */
	private Integer start = 1;
	
	/** 分页结束位置 */
	private Integer end = 10;
	
	
	/** searchFeild 查询字段 */
	//private String sf;
	
	/** searchString 查询条件 */
	//private String ss;
	

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

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	/*public String getSf() {
		return sf;
	}

	public void setSf(String sf) {
		this.sf = sf;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}*/

}
