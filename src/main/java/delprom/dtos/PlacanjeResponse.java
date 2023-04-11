package delprom.dtos;

import java.util.List;

public class PlacanjeResponse {

	private List<PlacanjeDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;

	public PlacanjeResponse() {

	}

	public PlacanjeResponse(List<PlacanjeDto> content, int pageNo, int pageSize, long totalElements, int totalPages,
			boolean last) {
		this.content = content;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}

	public List<PlacanjeDto> getContent() {
		return content;
	}

	public void setContent(List<PlacanjeDto> content) {
		this.content = content;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

}
