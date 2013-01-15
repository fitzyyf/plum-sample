1. 计算行的高度
 
  * 计算打印区域高度
  	
  	在page format...中查看到一下信息，(Height - (Top+Bottom)) = print heigth

  * 计算行高
  
	 (整个报表的高度-((报表标题的高度+表头的高度)-报表底部的高度))/行数=每行的高度
	 (print heigth - ((page Header height + column Header height) + footer height))/PAGE_MIN_LINE = line height