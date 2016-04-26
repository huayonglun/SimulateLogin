package demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//输出器
public class HtmlOutputer {

	
	/**
	 * 将数据以html形式输出
	 */
	public static void outputHtml(List<Grade> grades,String username){
		File file = new File("grade_"+username+".html");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			//输出数据
			os.write("<html><meta charset='UTF-8'><body>".getBytes("UTF-8"));
			os.write("<table border='1' width='80%' align='center'>".getBytes("UTF-8"));
			
			os.write(("<tr>"
					+ "<th style='width: 35px;'>序号</th>"
					+ "<th style='width: 140px;'>开课学期</th>"
					+ "<th style='width: 110px;'>课程编号</th>"
					+ "<th style='width: 110px;'>课程名称</th>"
					+ "<th style='width: 60px;'>成绩</th>"
					+ "<th style='width: 50px;'>学分</th>"
					+ "<th style='width: 50px;'>总学时</th>"
					+ "<th style='width: 60px;'>考核方式</th>"
					+ "<th style='width: 60px;'>课程属性</th>"
					+ "</tr>").getBytes("UTF-8"));
			for(Grade grade : grades){
				
				
				os.write("<tr>".getBytes());
				os.write("<td>".getBytes());
				os.write(grade.getGid().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getDate().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getCid().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getCname().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getScore().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getCredit().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getStudyhour().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getExammethod().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				
				os.write("<td>".getBytes());
				os.write(grade.getExamattr().getBytes("UTF-8"));
				os.write("</td>".getBytes());
				os.write("</tr>".getBytes());
				
				
			}
			os.write("</table>".getBytes());
			os.write("</body></html>".getBytes());
			
		}catch (Exception e) {
			System.out.println("输出器发生错误");
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.flush();
				} catch (IOException e) { 
					e.printStackTrace();
				}
				try {
					os.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
		
	}

}
