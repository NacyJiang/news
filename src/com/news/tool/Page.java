package com.news.tool;

import java.util.List;

import com.news.dao.NewsDao;
import com.news.entity.News;
import com.news.entity.PageBean;

public class Page {
	
	private PageBean page;
	private List pagelist;
	private List listall;
	
	public Page(PageBean page,List pagelist, List listall){
		this.page = page;
		this.pagelist = pagelist;
		this.listall = listall;
	}
	
	public void dopage(String pagesize,String pageno){
		
		if(pageno==null){
			//��һ�η��ʣ���dao��ȡ���м�¼						
			if(pagesize != null){
				if(pagesize.equals("0"))
					page.setPagesize(listall.size());
				else{
					int size = Integer.parseInt(pagesize);
					
					if(size >= listall.size())
						page.setPagesize(listall.size());
					else
						page.setPagesize(size);	
				}
			}
					
			page.setCurrentpage(1);
			page.setRows(listall.size());
			int pages = page.getRows()%page.getPagesize()==0?page.getRows()/page.getPagesize():page.getRows()/page.getPagesize()+1;
			page.setPages(pages);
			
			for(int i=0;i<page.getPagesize();i++)
				pagelist.add(listall.get(i));		

		}else{
			
			//����ÿҳ��ʾ����
			if(pagesize != null){
				int size = Integer.parseInt(pagesize);
				
				if(size >= page.getRows())
					page.setPagesize(page.getRows());
				else
					page.setPagesize(size);	
				
			}
		
			//����Ѿ�����ҳ ��βҳ ��ֱ����ת
			int p = Integer.parseInt(pageno);			
			if(p!=0 && !(p>=(page.getPages()+1)) ){
			
				page.setCurrentpage(Integer.parseInt(pageno));
				

				//�Ƴ���һ��ҳ������	
				int size = pagelist.size();
				for(int i = 0;i<size;i++){
					pagelist.remove(0);//ÿ�ζ���0 ����Ϊÿ�ΰѵ�һ��ɾ���󣬺���ľͻ���ǰ���
				}
				
				//��ͨҳ
				if(page.getCurrentpage() < page.getPages()){
					for(int i = 0;i<page.getPagesize();i++){
						pagelist.add(listall.get(i+(page.getCurrentpage()-1)*page.getPagesize()));
					}			
				}else{//���һҳ
					int last = listall.size() % page.getPagesize();
					if(last != 0){//����һҳû��pagesize����¼
						 for(int i = 0;i<last;i++){
							 pagelist.add(listall.get(i+(page.getCurrentpage()-1)*page.getPagesize()));	
						 }				
					}else{//����һҳû�иպ���pagesize����¼
						for(int i = 0;i<page.getPagesize();i++){
							pagelist.add(listall.get(i+(page.getCurrentpage()-1)*page.getPagesize()));
						}
					}
		
				}
			}
			
		}	
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public List getPagelist() {
		return pagelist;
	}

	public void setPagelist(List pagelist) {
		this.pagelist = pagelist;
	}

	public List getListall() {
		return listall;
	}

	public void setListall(List listall) {
		this.listall = listall;
	}



}
