package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    //类被加载时就执行静态代码块,把配置文件的信息加载到类中。
    static {
        try {
            Properties pro=new Properties();
            pro.load(new FileReader("src/dbInfo/db.properties"));
            url=pro.getProperty("url");
            user=pro.getProperty("username");
            password=pro.getProperty("password");
            driver=pro.getProperty("driver");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //释放连接
    public  static void close(ResultSet rs, Statement stm, Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
