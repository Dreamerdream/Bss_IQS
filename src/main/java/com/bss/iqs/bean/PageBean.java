package com.bss.iqs.bean;

import java.util.List;

public class PageBean {

	// 传递的参数或是配置的参数
	private int currentPage; // 当前页
	private int pageSize; // 每页显示多少条记录
	// 查询数据库
	private List recordList; // 本页的数据列表
	private int recordCount; // 总记录数
	// 计算
	private int pageCount; // 总页数
	private int beginPageIndex; // 页码列表的开始索引（包含）
	private int endPageIndex; // 页码列表的结束索引（包含）
	
	private String url;//表单跳转路径  即确定调用action的哪个查询方法
			// 计算 pageCount

	/**
	 * 只接受5个必要的属性，会自动的计算出其他3个属性的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordList
	 * @param recordCount
	 * @param url 
	 */
	public PageBean(int currentPage, int pageSize, List recordList, int recordCount, String url) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordList = recordList;
		this.recordCount = recordCount;
		this.url = url;

		// 计算 pageCount
		pageCount = (recordCount + pageSize - 1) / pageSize;
 
		// 计算 beginPageIndex 与 endPageIndex
		// >> 总页码小于等于10页时，全部显示
		if (pageCount <= 8) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页码大于8页时，就只显示当前页附近的共8个页码
		else {
			// 默认显示 前3页 + 当前页 + 后4页
			beginPageIndex = currentPage - 3; // 7 - 4 = 3;
			endPageIndex = currentPage + 4; // 7 + 5 = 12; --> 3 ~ 12

			// 如果前面不足4个页码时，则显示前10页
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 8;
			}
			// 如果后面不足5个页码时，则显示后10页
			else if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 7;
			}
		}
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
