package com.lz.util;

import java.util.List;

public class Fenye {
	
	public static int lastPage(int totalPage,int page , int pageSize){
		
		int lastPage = 1;
		if (totalPage % pageSize == 0) {
			lastPage = totalPage / pageSize;
		} else
			lastPage = totalPage / pageSize + 1;

		if (page > lastPage) {
			page = lastPage;
		}
		if (page <= 0) {
			page = 1;
		}
		
		return lastPage;
	}

/*public List fenYe(List listInfo,int pageRecords,int currentPage,int allPage,int allRecords){
		
		if(currentPage<allPage){
			listInfo=listInfo.subList((currentPage-1)*pageRecords, currentPage*pageRecords);
		}else if(currentPage==allPage){
			listInfo=listInfo.subList((currentPage-1)*pageRecords, allRecords);
		}
		
		return listInfo;
		
		
	}*/


public static List fenye(List list, int totalPage, int page, int pageSize,
		int lastPage) {

	List list_new = null;
	// 当前页第一个的位置
	int firstSet = (page - 1) * pageSize;
	// 下方排序
	if (list.size() > pageSize) {
		if ((firstSet + pageSize) < (totalPage - 1)) {
			list_new = list.subList(firstSet, firstSet + pageSize);
		} else {
			list_new = list.subList(firstSet, totalPage);
		}
	} else {
		list_new = list;
	}

	return list_new;
}


}
