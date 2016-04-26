package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Demo {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("src/login_info.properties")));
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		//map装载表单数据
		Map<String,String> map = new HashMap<String,String>();
		map.put("USERNAME", username);
		map.put("PASSWORD", password);
		
		
		
		//模拟浏览器创建连接，发起请求
		Connection conn = Jsoup.connect("http://jwcnew.nefu.edu.cn/dblydx_jsxsd/xk/LoginToXk");
		conn.header("Host", "jwcnew.nefu.edu.cn");
		conn.header("Referer", "http://jwcnew.nefu.edu.cn");
		conn.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)");
		Response response = conn.ignoreContentType(true).method(Method.POST).data(map).execute();
		
		
		//获取cookies
		Map<String,String> cookies = response.cookies();
		
		Set<String> keys = cookies.keySet();
		for(String key:keys){
			System.out.println(key+":"+cookies.get(key));
		}
		
		//利用cookies保存的登录信息进行登录
		Document document = Jsoup.connect("http://jwcnew.nefu.edu.cn/dblydx_jsxsd/kscj/cjcx_list").cookies(cookies).post();
		document.charset(Charset.forName("UTF-8"));
		
		
		//分析页面，得到成绩的标签
		Elements trs = document.getElementsByTag("tr");
		
		trs.remove(0);
		trs.remove(0);
		System.out.println("共"+trs.size()+"项成绩");
		
		//定义容器，存放爬取的每一条成绩信息
		List<Grade> list = new ArrayList<Grade>();
		
		for(Element tr : trs){
			Elements datas = tr.select("td");
			Grade grade = new Grade();
			grade.setGid(datas.get(0).text());
			grade.setDate(datas.get(1).text());
			grade.setCid(datas.get(2).text());
			grade.setCname(datas.get(3).text());
			grade.setScore(datas.get(4).text());
			grade.setCredit(datas.get(5).text());
			grade.setStudyhour(datas.get(6).text());
			grade.setExammethod(datas.get(7).text());
			grade.setExamattr(datas.get(8).text());
			System.out.println();
			list.add(grade);

		}
		
		//将数据输出到html文件中
		HtmlOutputer.outputHtml(list,username);
		
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		java.sql.Connection con =dataSource.getConnection();
		Statement st = con.createStatement();
		st.execute("create database if not exists grade character set utf8");
		st.execute("use grade");
		st.execute("create table if not exists grade_"+username+""
				+ "("
				+ "gid varchar(20) primary key,date varchar(20) ,cid varchar(20) ,"
				+ "cname varchar(50) ,score varchar(5) ,credit varchar(5) ,"
				+ "studyhour varchar(5) ,exammethod varchar(10) ,examattr varchar(10) "
				+ ") DEFAULT CHARSET=utf8");
		
		String insertSql = "insert into grade_"+username+" values(?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(dataSource);
		for(Grade grade:list){
			Object[] params = {grade.getGid(),grade.getDate(),grade.getCid(),
					grade.getCname(),grade.getScore(),grade.getCredit(),
					grade.getStudyhour(),grade.getExammethod(),grade.getExamattr()};
			qr.update(con,insertSql, params);
			
		}
		
		con.close();
		
		
		
		
		
	}

}
