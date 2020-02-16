package LoginFrame;

import Dao.UserDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm {
    UserDao Dao;
    JFrame frame;
    JPanel panel;
    JTextField userText;
    JPasswordField passwordText;
    JButton loginButton;
    JLabel noticLable;


    //初始化窗口,DAO
    public void init(){
        Dao=new UserDao();
        // 创建 JFrame 实例
        frame = new JFrame("Login Example");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        panel= new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);
    }
    public void display(){
        // 设置界面可见
        frame.setVisible(true);
    }
    //表单验证
    public void validate() throws SQLException {
        String Username=userText.getText();
        String PsWord= String.valueOf(passwordText.getPassword());
        if(Dao.findUser(Username,PsWord)){
            noticLable.setText("密码OK");
        }else {
            noticLable.setText("用户不存在或密码错误");
        }


    }
    //放置容器
    private void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);
        // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        //提示标签
        noticLable = new JLabel();
        noticLable.setBounds(110,80,80,25);
        panel.add(noticLable);
        // 创建登录按钮
        loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击按钮时执行表单验证
                try {
                    validate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(loginButton);
    }
}

