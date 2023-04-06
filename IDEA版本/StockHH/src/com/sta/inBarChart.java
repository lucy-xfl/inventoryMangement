package com.sta;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.tool.Tool;

public class inBarChart {
	 ChartPanel frame1;
	public inBarChart() {
		
		CategoryDataset dataset = getDataSet();
		 JFreeChart chart = ChartFactory.createBarChart3D(
	            "入库商品数量", // 图表标题
                "商品种类", // 文件夹轴的显示标签
                "数量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
                );
		  CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
	      CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
	      
	      domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
	      domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
	      ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
	      
	      rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
	      chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
	      chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
	      
	      frame1=new ChartPanel(chart,true);  
	      
	}
	

	private  CategoryDataset getDataSet() {
		// TODO Auto-generated method stub
		
		//用于读取数据库 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
       int i=0;
        String SqlStr= "SELECT supname, sum(num) FROM instock GROUP BY supname order by sum(num) desc LIMIT 0, 3";
        ResultSet rs = Tool.showData(SqlStr, null);
        try {
			while(rs.next()) {
				String supname=rs.getString("supname");
				String data[]=new String[1];
				data[0]=supname;
				String sql="select * from instock  where  supname=? order by num desc LIMIT 0, 3";
				ResultSet rs1 = Tool.showData(sql,data);
				 
				 while(rs1.next()) {
					 int num = rs1.getInt("num");
					 String sup=rs1.getString("supname");
					 String sun=rs1.getString("stockname");
					dataset.addValue(num, sup, sun+i);
					i++;
					
				 }
				rs1.close();
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
     
       

        return dataset;
	}
	public ChartPanel getChartPanel(){
		return frame1;
		
	}
}
