package Dao;

import Utils.DBUtils;
import entilty.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    DBUtils dbUtils;
    Statement sts;
    ResultSet rs;
    Connection conn;
    User user;
public UserDao(){
    if(dbUtils==null){
        new DBUtils();
    }
}


public Boolean findUser(String uname,String pwd) throws SQLException {
   conn = dbUtils.getConnection();
    sts=conn.createStatement();
    rs=sts.executeQuery("select * from student ");
    user=new User();
    while (rs.next()){
        String dbname=rs.getString("username");
        if(dbname.equals(uname)){
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));
            user.setSalary(rs.getString("salary"));
            user.setUsername(uname);
            break;
        }
    }
    if(checkPwd(user,pwd)){
        return true;
    }
    return false;
}
public  boolean checkPwd(User user,String pwd){
        if(user.getPassword().equals(pwd)){
            return true;
        }
        return false;
    }
}

