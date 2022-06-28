import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class jf1{
    static Connection conn;
    static PreparedStatement ps = null;
    static ResultSet rs;
    static int  count=0;
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) { }
        String url="jdbc:mysql:///db";
            String user="root";
            String password="bb288299";
        try {
            conn= DriverManager.getConnection(url, user, password);
        } catch (Exception throwables) { }
    }
    public static void main(String[] args) {
        jf1();
    }
    
    private static void jf1(){
        initComponents();
        jf1.setVisible(true);
        
//      管理员登录
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf1.setVisible(false);
                jf2.setVisible(true);
            }
        });
//       首页登录 密码框
        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox1.isSelected()) {
                    //如果选中，显示密码
                    passwordField5.setEchoChar((char)0);
                } else {
                    //否则隐藏密码
                    passwordField5.setEchoChar('\u2022');
                }
            }
        });
//        管理员登录密码框
        checkBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox2.isSelected()) {
                    //如果选中，显示密码
                    passwordField4.setEchoChar((char)0);
                } else {
                    //否则隐藏密码
                    passwordField4.setEchoChar('\u2022');
                }
            }
        });
//        找回密码
        button29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf11.setVisible(true);
            }
        });
//        验证邮箱
        button33.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField2.getText();
                String email = textField4.getText();
                String regex = "[1-9]\\d{5,10}@qq\\.com";
                boolean b = email.matches(regex);
                if (text.equals("")||email.equals("")){
                    JOptionPane.showMessageDialog(null, "账号或邮箱不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else if (!b){
                    JOptionPane.showMessageDialog(null, "邮箱格式不正确", "提示", JOptionPane.PLAIN_MESSAGE);
                }else{
                    String sql = "select password from user where username=? and E_mail=?";
                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1,text);
                        ps.setString(2,email);
                        rs = ps.executeQuery();
                        while (rs.next()){
                            String pw=rs.getString("password");
                            JOptionPane.showMessageDialog(null, "该账号密码为："+pw, "提示", JOptionPane.PLAIN_MESSAGE);
                            jf11.setVisible(false);
                            textField2.setText("");
                            textField4.setText("");
                        }
                    } catch (SQLException throwables) {
                    }
                }
            }
        });
//        注册
        checkBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox4.isSelected()) {
                    //如果选中，显示密码
                    passwordField10.setEchoChar((char)0);
                    passwordField11.setEchoChar((char)0);
                } else {
                    //否则隐藏密码
                    passwordField10.setEchoChar('\u2022');
                    passwordField11.setEchoChar('\u2022');
                }
            }
        });

//        管理员登录界面取消按钮
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf2.setVisible(false);
                jf1.setVisible(true);
            }
        });
//        注册按钮
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf7.setVisible(true);
                jf1.setVisible(false);
            }
        });
//      注册界面取消按钮
        button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf7.setVisible(false);
                jf1.setVisible(true);
            }
        });
//        注册界面确认  注册  按钮
        button17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = textField10.getText();
                String pw = passwordField10.getText();
                String pw1 = passwordField11.getText();
                String email=passwordField15.getText();
                String regex = "[1-9]\\d{5,10}@qq\\.com";
                boolean b = email.matches(regex);


                    if (pw.equals("")||un.equals("")||pw1.equals("")){
                        JOptionPane.showMessageDialog(null, "账号或密码不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                    }else{
                        if (!pw.equals(pw1)){
                            JOptionPane.showMessageDialog(null, "两次输入的密码不一致", "提示",JOptionPane.PLAIN_MESSAGE);
                        }else{
                            if (!b){
                                JOptionPane.showMessageDialog(null, "QQ邮箱有误，请检查", "提示",JOptionPane.PLAIN_MESSAGE);
                            }else{
                                String sql = "insert into user(username,password,E_mail) values (?,?,?)";
                                try {
                                    ps = conn.prepareStatement(sql);
                                    ps.setString(1,un);
                                    ps.setString(2,pw);
                                    ps.setString(3,email);
                                    int count = ps.executeUpdate();
                                    if (count>0){
                                        JOptionPane.showMessageDialog(null, "注册成功，请登录", "提示",JOptionPane.PLAIN_MESSAGE);
                                        jf7.setVisible(false);
                                        jf1.setVisible(true);
                                    }
                                } catch (Exception throwables) {
                                    System.out.println(throwables);
                                }
                            } } }
            }
        });
//        用户登录按钮
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = textField1.getText();
                String pw = passwordField5.getText();
                if (un.equals("")||pw.equals("")){
                    JOptionPane.showMessageDialog(null, "账号或密码不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else{
                    String sql = "select * from user where username=? and password=?";

                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1,un);
                        ps.setString(2,pw);
                        rs = ps.executeQuery();
                        if (rs.next()){
                            JOptionPane.showMessageDialog(null, "登陆成功", "提示", JOptionPane.PLAIN_MESSAGE);
                            jf1.setVisible(false);
                             jf8.setVisible(true);
                            RefreshBook();
                        }else{
                            JOptionPane.showMessageDialog(null, "账号或密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
                            passwordField5.setText("");
                        }
                    } catch (SQLException ex) {
                    }
                }

            }
        });
//        管理员  确定登录按钮
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = textField3.getText();
                String pw = passwordField4.getText();

                if (un.equals("")||pw.equals("")){
                    JOptionPane.showMessageDialog(null, "账号或密码不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else{
                    String sql = "select * from administrator where username=? and password=?";
                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1,un);
                        ps.setString(2,pw);
                        rs = ps.executeQuery();
                        if (rs.next()){
                            JOptionPane.showMessageDialog(null, "登陆成功", "提示", JOptionPane.PLAIN_MESSAGE);
                            jf2.setVisible(false);
                            jf4.setVisible(true);
                            RefreshBook();
                        }else{
                            JOptionPane.showMessageDialog(null, "账号或密码错误", "提示", JOptionPane.PLAIN_MESSAGE);
                            passwordField4.setText("");
                        }
                    } catch (Exception ex) { }
                }


            }
        });
//       管理员 添加图书按钮
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf3.setVisible(true);
            }
        });
//       管理员 添加图书取消按钮
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jf3.setVisible(false);
            }
        });
//      管理员  修改图书按钮
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf6.setVisible(true);
            }
        });
//       管理员 修改图书取消按钮
        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf6.setVisible(false);
            }
        });
//     管理员 删除按钮
        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jf5.setVisible(true);
            }
        });
//       管理员 删除界面取消按钮
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jf5.setVisible(false);
            }
        });
//        用户 借鉴按钮
        button21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf9.setVisible(true);
            }
        });
//      用户  借鉴界面取消按钮
        button19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf9.setVisible(false);
            }
        });
//        用户 归还按钮
        button18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf10.setVisible(true);
            }
        });
//        用户 归还界面取消按钮
        button23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf10.setVisible(false);
            }
        });
//        修改图书确认按钮
        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                原
                String bookname = textField7.getText();
                String PressName = passwordField14.getText();
                String price = passwordField9.getText();
                String status = textField15.getText();

//                新
                String nbookname = textField13.getText();
                String nPressName = passwordField8.getText();
                String nprice = textField14.getText();
                String nstatus = textField16.getText();
//                update book set bookname='新梦的解析',PressName='新未知出版社',price='19.99',state='0' where bookname='梦的解析'and PressName='未知出版社'and price='32.8' and state='1';
                String sql = "update book set bookname=?,PressName=?,price=?,state=?where bookname=?and PressName=?and price=? and state=?";
                try {
                    ps= conn.prepareStatement(sql);
                    ps.setString(1,nbookname);
                    ps.setString(2,nPressName);
                    ps.setString(3,nprice);
                    ps.setString(4,nstatus);
                    ps.setString(5,bookname);
                    ps.setString(6,PressName);
                    ps.setString(7,price);
                    ps.setString(8,status);
                    int count = ps.executeUpdate();
                    if (count>0){
                        JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
                        jf6.setVisible(false);
                        textField7.setText("");
                        passwordField14.setText("");
                        passwordField9.setText("");
                        textField15.setText("");
                        textField13.setText("");
                        passwordField8.setText("");
                        textField14.setText("");
                        textField16.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null, "原图书数据可能有误，请重新检查", "提示", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException throwables) { }
                RefreshBook();
            }
        });
//          删除图书确认按钮
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookname = textField6.getText();
                String pressname = passwordField6.getText();
                String price = passwordField7.getText();
                String sql = "delete from book where bookname = ? and PressName=? and price=?";
                if (bookname.equals("")||pressname.equals("")||price.equals("")){
                    JOptionPane.showMessageDialog(null, "输入框不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else{
                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setString(1,bookname);
                        ps.setString(2,pressname);
                        ps.setDouble(3, Double.parseDouble(price));
                        int count = ps.executeUpdate();
                        if (count>0){
                            JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
                            jf5.setVisible(false);
                            textField6.setText("");
                            passwordField7.setText("");
                            passwordField6.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "数据可能有误，请重新检查", "提示", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                RefreshBook();
            }
        });
//        添加图书确定按钮
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookname = textField5.getText();
                String pressname = textField8.getText();
                String price = textField9.getText();
                String sql = "insert into book(bookname,PressName,price,state) values (?,?,?,1)";
                if (bookname.equals("")||pressname.equals("")||price.equals("")){
                    JOptionPane.showMessageDialog(null, "输入框不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else{
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,bookname);
                        ps.setString(2,pressname);
                        ps.setDouble(3,Double.parseDouble(price));
                        int count = ps.executeUpdate();
                        if (count>0){
                            JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.PLAIN_MESSAGE);
                            jf3.setVisible(false);
                            textField5.setText("");
                            textField8.setText("");
                            textField9.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "数据格式可能有误，请重新检查", "提示", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                RefreshBook();
            }
        });
//        管理员   查找图书确定按钮
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=textArea1.getText();
                String bookname=textArea2.getText();
                String pressname=textArea3.getText();
                String state=textArea4.getText();
                String price=textArea5.getText();

                if (id.equals("")&&bookname.equals("")&&pressname.equals("")&&price.equals("")&&state.equals("")){
                    JOptionPane.showMessageDialog(null, "输入框不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else if (!id.equals("")&&bookname.equals("")&&pressname.equals("")&&price.equals("")&&state.equals("")){
                    String sql = "select * from book where id=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,id);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }
                    JOptionPane.showMessageDialog(null, "根据id查找", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea1.setText("");
                }else if(id.equals("")&&!bookname.equals("")&&pressname.equals("")&&price.equals("")&&state.equals("")){
                    String sql = "select * from book where bookname=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,bookname);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }
                    JOptionPane.showMessageDialog(null, "书名查找成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea2.setText("");
                }else if(id.equals("")&&bookname.equals("")&&pressname.equals("")&&!price.equals("")&&state.equals("")){

                    String sql = "select * from book where price=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,price);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }

                    JOptionPane.showMessageDialog(null, "价格查找成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea5.setText("");
                }else if(id.equals("")&&bookname.equals("")&&pressname.equals("")&&price.equals("")&&!state.equals("")){
                    String sql = "select * from book where state=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,state);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }

                    JOptionPane.showMessageDialog(null, "状态查找成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea4.setText("");

                }else if(id.equals("")&&bookname.equals("")&&!pressname.equals("")&&price.equals("")&&state.equals("")){
                    String sql = "select * from book where PressName=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,pressname);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }
                    JOptionPane.showMessageDialog(null, "根据出版社成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea3.setText("");
                }
            }


        });
//        用户   查找图书确定按钮
        button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=textArea8.getText();
                String bookname=textArea9.getText();
                String pressname=textArea10.getText();
                String state=textArea6.getText();
                String price=textArea7.getText();


                if (id.equals("")&&bookname.equals("")&&pressname.equals("")&&price.equals("")&&state.equals("")){
                    JOptionPane.showMessageDialog(null, "输入框不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else if (!id.equals("")&&bookname.equals("")&&pressname.equals("")&&price.equals("")&&state.equals("")){
                    String sql = "select * from book where id=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,id);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }
                    JOptionPane.showMessageDialog(null, "根据id查找", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea8.setText("");
                }else if(id.equals("")&&!bookname.equals("")&&pressname.equals("")&&price.equals("")&&state.equals("")){
                    String sql = "select * from book where bookname=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,bookname);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }
                    JOptionPane.showMessageDialog(null, "书名查找成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea9.setText("");
                }else if(id.equals("")&&bookname.equals("")&&pressname.equals("")&&!price.equals("")&&state.equals("")){

                    String sql = "select * from book where price=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,price);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }

                    JOptionPane.showMessageDialog(null, "价格查找成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea7.setText("");
                }else if(id.equals("")&&bookname.equals("")&&pressname.equals("")&&price.equals("")&&!state.equals("")){
                    String sql = "select * from book where state=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,state);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }

                    JOptionPane.showMessageDialog(null, "状态查找成功", "提示", JOptionPane.PLAIN_MESSAGE);

                    textArea6.setText("");
                }else if(id.equals("")&&bookname.equals("")&&!pressname.equals("")&&price.equals("")&&state.equals("")){
                    String sql = "select * from book where PressName=? " ;
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,pressname);
                        rs= ps.executeQuery();
                        SingleQuery();
                    } catch (Exception throwables) {
                        System.out.println(throwables);
                    }
                    JOptionPane.showMessageDialog(null, "根据出版社成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    textArea10.setText("");
                }
            }
        });
//        借鉴
        button22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookname=textField11.getText();
                String pressname=passwordField12.getText();

                String sql = "update book set state=0 where bookname=?and PressName=?";
                if (textField11.equals("")||passwordField12.equals("")){
                    JOptionPane.showMessageDialog(null, "不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else{
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,bookname);
                        ps.setString(2,pressname);
                        int count = ps.executeUpdate();
                        if (count>0){
                            JOptionPane.showMessageDialog(null, "借书成功", "提示", JOptionPane.PLAIN_MESSAGE);
                            jf9.setVisible(false);
                            textField11.setText("");
                            passwordField12.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "数据可能有误，请重新检查", "提示", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                RefreshBook();
            }
        });
//         还书
        button24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookname=textField12.getText();
                String pressname=passwordField13.getText();

                String sql = "update book set state=1 where bookname=?and PressName=?";
                if (textField11.equals("")||passwordField12.equals("")){
                    JOptionPane.showMessageDialog(null, "不可为空", "提示", JOptionPane.PLAIN_MESSAGE);
                }else{
                    try {
                        ps= conn.prepareStatement(sql);
                        ps.setString(1,bookname);
                        ps.setString(2,pressname);
                        int count = ps.executeUpdate();
                        if (count>0){
                            JOptionPane.showMessageDialog(null, "还书成功", "提示", JOptionPane.PLAIN_MESSAGE);
                            jf10.setVisible(false);
                            textField12.setText("");
                            passwordField13.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "数据可能有误，请重新检查", "提示", JOptionPane.PLAIN_MESSAGE);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                RefreshBook();
            }
        });
//         查询所有
        button27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "查询成功", "提示", JOptionPane.PLAIN_MESSAGE);
                RefreshBook();
            }
        });
        button25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "查询成功", "提示", JOptionPane.PLAIN_MESSAGE);

                RefreshBook();
            }
        });
//        退出登录
        button26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "您已退出，请重新登录", "提示", JOptionPane.PLAIN_MESSAGE);

                jf8.setVisible(false);
                jf1.setVisible(true);
                textField1.setText("");
                passwordField5.setText("");
            }
        });
        button28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "您已退出，请重新登录", "提示", JOptionPane.PLAIN_MESSAGE);

                jf4.setVisible(false);
                jf2.setVisible(true);
                textField3.setText("");
                passwordField4.setText("");
            }
        });

    }
    //        刷新图书列表
    public static void RefreshBook(){

        String sql = "select * from book";
        try {
            ps=conn.prepareStatement(sql);
            rs= ps.executeQuery();
           SingleQuery();
        } catch (Exception throwables) { }
    }
    //    条件查询
    public static void SingleQuery()throws Exception{
        String[] col = {"书籍编号", "书籍名称", "书籍出版社", "书籍价格", "状态"};
        DefaultTableModel BookInfo = new DefaultTableModel(col, 0){
            public boolean isCellEditable(int row, int column) {return false;}
        };
        while (rs.next()) {
            int id = rs.getInt("id");
            String bookname = rs.getString("bookname");
            String PressName = rs.getString("PressName");
            double price = rs.getDouble("price");
            int status = rs.getInt("state");
            String[] str_row = {String.valueOf(id), bookname,PressName, String.valueOf(price),(status>0?"未出借":"已出借")};
            BookInfo.addRow(str_row);
            table1.setModel(BookInfo);
            table2.setModel(BookInfo);
        }
    }

    private static void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        jf1 = new JFrame();
        dialogPane = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        button3 = new JButton();
        passwordField5 = new JPasswordField();
        checkBox1 = new JCheckBox();
        button29 = new JButton();
        jf2 = new JFrame();
        dialogPane2 = new JPanel();
        label4 = new JLabel();
        button4 = new JButton();
        button5 = new JButton();
        label5 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        passwordField4 = new JPasswordField();
        checkBox2 = new JCheckBox();
        jf3 = new JFrame();
        dialogPane3 = new JPanel();
        label7 = new JLabel();
        button6 = new JButton();
        button7 = new JButton();
        label8 = new JLabel();
        textField5 = new JTextField();
        label9 = new JLabel();
        label11 = new JLabel();
        textField8 = new JTextField();
        textField9 = new JTextField();
        jf4 = new JFrame();
        dialogPane4 = new JPanel();
        button10 = new JButton();
        button13 = new JButton();
        label18 = new JLabel();
        textArea4 = new JTextArea();
        label19 = new JLabel();
        textArea5 = new JTextArea();
        button27 = new JButton();
        button28 = new JButton();
        label12 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label13 = new JLabel();
        textArea2 = new JTextArea();
        label14 = new JLabel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        textArea3 = new JTextArea();
        button8 = new JButton();
        button9 = new JButton();
        jf5 = new JFrame();
        dialogPane5 = new JPanel();
        label10 = new JLabel();
        button11 = new JButton();
        button12 = new JButton();
        label15 = new JLabel();
        textField6 = new JTextField();
        label16 = new JLabel();
        scrollPane9 = new JScrollPane();
        passwordField6 = new JTextArea();
        label17 = new JLabel();
        scrollPane10 = new JScrollPane();
        passwordField7 = new JTextArea();
        jf6 = new JFrame();
        dialogPane6 = new JPanel();
        label20 = new JLabel();
        button14 = new JButton();
        button15 = new JButton();
        label21 = new JLabel();
        textField7 = new JTextField();
        label22 = new JLabel();
        scrollPane8 = new JScrollPane();
        passwordField8 = new JTextArea();
        label23 = new JLabel();
        scrollPane7 = new JScrollPane();
        passwordField9 = new JTextArea();
        label39 = new JLabel();
        textField13 = new JTextField();
        label40 = new JLabel();
        scrollPane6 = new JScrollPane();
        passwordField14 = new JTextArea();
        label41 = new JLabel();
        textField14 = new JTextField();
        label42 = new JLabel();
        textField15 = new JTextField();
        label43 = new JLabel();
        textField16 = new JTextField();
        jf7 = new JFrame();
        dialogPane7 = new JPanel();
        label24 = new JLabel();
        button16 = new JButton();
        button17 = new JButton();
        label25 = new JLabel();
        textField10 = new JTextField();
        label26 = new JLabel();
        passwordField10 = new JPasswordField();
        label27 = new JLabel();
        passwordField11 = new JPasswordField();
        checkBox4 = new JCheckBox();
        label44 = new JLabel();
        scrollPane5 = new JScrollPane();
        passwordField15 = new JTextArea();
        jf8 = new JFrame();
        dialogPane8 = new JPanel();
        button18 = new JButton();
        label28 = new JLabel();
        textArea6 = new JTextArea();
        label29 = new JLabel();
        textArea7 = new JTextArea();
        button25 = new JButton();
        button26 = new JButton();
        label30 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea8 = new JTextArea();
        label31 = new JLabel();
        textArea9 = new JTextArea();
        label32 = new JLabel();
        scrollPane4 = new JScrollPane();
        table2 = new JTable();
        textArea10 = new JTextArea();
        button20 = new JButton();
        button21 = new JButton();
        jf9 = new JFrame();
        dialogPane9 = new JPanel();
        label33 = new JLabel();
        button19 = new JButton();
        button22 = new JButton();
        label34 = new JLabel();
        textField11 = new JTextField();
        label35 = new JLabel();
        scrollPane11 = new JScrollPane();
        passwordField12 = new JTextArea();
        jf10 = new JFrame();
        dialogPane10 = new JPanel();
        label36 = new JLabel();
        button23 = new JButton();
        button24 = new JButton();
        label37 = new JLabel();
        textField12 = new JTextField();
        label38 = new JLabel();
        scrollPane12 = new JScrollPane();
        passwordField13 = new JTextArea();
        jf11 = new JFrame();
        dialogPane11 = new JPanel();
        label46 = new JLabel();
        textField2 = new JTextField();
        label47 = new JLabel();
        textField4 = new JTextField();
        button33 = new JButton();

        //======== jf1 ========
        {
            var jf1ContentPane = jf1.getContentPane();

            //======== dialogPane ========
            {
                dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
                border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
                , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
                .BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (
                new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
                .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

                //---- label1 ----
                label1.setText("      \u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");

                //---- button1 ----
                button1.setText("\u6ce8\u518c");

                //---- button2 ----
                button2.setText("\u767b\u5f55");

                //---- label2 ----
                label2.setText("\u8d26\u53f7:");

                //---- label3 ----
                label3.setText("\u5bc6\u7801:");

                //---- button3 ----
                button3.setText("\u7ba1\u7406\u5458\u767b\u5f55");

                //---- checkBox1 ----
                checkBox1.setText("\u663e\u793a\u5bc6\u7801");

                //---- button29 ----
                button29.setText("\u627e\u56de\u5bc6\u7801");

                GroupLayout dialogPaneLayout = new GroupLayout(dialogPane);
                dialogPane.setLayout(dialogPaneLayout);
                dialogPaneLayout.setHorizontalGroup(
                    dialogPaneLayout.createParallelGroup()
                        .addGroup(dialogPaneLayout.createSequentialGroup()
                            .addGroup(dialogPaneLayout.createParallelGroup()
                                .addGroup(dialogPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                                    .addComponent(button3))
                                .addGroup(dialogPaneLayout.createSequentialGroup()
                                    .addGap(93, 93, 93)
                                    .addGroup(dialogPaneLayout.createParallelGroup()
                                        .addGroup(dialogPaneLayout.createSequentialGroup()
                                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(dialogPaneLayout.createSequentialGroup()
                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(passwordField5, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                            .addComponent(checkBox1)
                                            .addGap(16, 16, 16)))))
                            .addContainerGap())
                        .addGroup(dialogPaneLayout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(button1)
                            .addGap(18, 18, 18)
                            .addComponent(button29)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                            .addComponent(button2)
                            .addGap(50, 50, 50))
                );
                dialogPaneLayout.setVerticalGroup(
                    dialogPaneLayout.createParallelGroup()
                        .addGroup(dialogPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button3))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                            .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(22, 22, 22)
                            .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3)
                                .addComponent(checkBox1))
                            .addGap(45, 45, 45)
                            .addGroup(dialogPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1)
                                .addComponent(button2)
                                .addComponent(button29))
                            .addGap(22, 22, 22))
                );
            }

            GroupLayout jf1ContentPaneLayout = new GroupLayout(jf1ContentPane);
            jf1ContentPane.setLayout(jf1ContentPaneLayout);
            jf1ContentPaneLayout.setHorizontalGroup(
                jf1ContentPaneLayout.createParallelGroup()
                    .addGroup(jf1ContentPaneLayout.createSequentialGroup()
                        .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
            );
            jf1ContentPaneLayout.setVerticalGroup(
                jf1ContentPaneLayout.createParallelGroup()
                    .addGroup(jf1ContentPaneLayout.createSequentialGroup()
                        .addComponent(dialogPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
            );
            jf1.pack();
            jf1.setLocationRelativeTo(jf1.getOwner());
        }

        //======== jf2 ========
        {
            var jf2ContentPane = jf2.getContentPane();

            //======== dialogPane2 ========
            {
                dialogPane2.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
                swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border
                . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
                ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,dialogPane2. getBorder
                ( )) ); dialogPane2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
                .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
                ( ); }} );

                //---- label4 ----
                label4.setText("      \u7ba1\u7406\u5458\u767b\u5f55");

                //---- button4 ----
                button4.setText("\u53d6\u6d88");

                //---- button5 ----
                button5.setText("\u767b\u5f55");

                //---- label5 ----
                label5.setText("\u8d26\u53f7:");

                //---- label6 ----
                label6.setText("\u5bc6\u7801:");

                //---- checkBox2 ----
                checkBox2.setText("\u663e\u793a\u5bc6\u7801");

                GroupLayout dialogPane2Layout = new GroupLayout(dialogPane2);
                dialogPane2.setLayout(dialogPane2Layout);
                dialogPane2Layout.setHorizontalGroup(
                    dialogPane2Layout.createParallelGroup()
                        .addGroup(dialogPane2Layout.createSequentialGroup()
                            .addGroup(dialogPane2Layout.createParallelGroup()
                                .addGroup(dialogPane2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                                .addGroup(dialogPane2Layout.createSequentialGroup()
                                    .addGap(93, 93, 93)
                                    .addGroup(dialogPane2Layout.createParallelGroup()
                                        .addGroup(dialogPane2Layout.createSequentialGroup()
                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(passwordField4, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(checkBox2))
                                        .addGroup(dialogPane2Layout.createSequentialGroup()
                                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)))))
                            .addContainerGap(16, Short.MAX_VALUE))
                        .addGroup(dialogPane2Layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(button4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                            .addComponent(button5)
                            .addGap(50, 50, 50))
                );
                dialogPane2Layout.setVerticalGroup(
                    dialogPane2Layout.createParallelGroup()
                        .addGroup(dialogPane2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                            .addGroup(dialogPane2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(29, 29, 29)
                            .addGroup(dialogPane2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label6)
                                .addComponent(passwordField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkBox2))
                            .addGap(38, 38, 38)
                            .addGroup(dialogPane2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button4)
                                .addComponent(button5))
                            .addGap(22, 22, 22))
                );
            }

            GroupLayout jf2ContentPaneLayout = new GroupLayout(jf2ContentPane);
            jf2ContentPane.setLayout(jf2ContentPaneLayout);
            jf2ContentPaneLayout.setHorizontalGroup(
                jf2ContentPaneLayout.createParallelGroup()
                    .addGroup(jf2ContentPaneLayout.createSequentialGroup()
                        .addComponent(dialogPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
            );
            jf2ContentPaneLayout.setVerticalGroup(
                jf2ContentPaneLayout.createParallelGroup()
                    .addGroup(jf2ContentPaneLayout.createSequentialGroup()
                        .addComponent(dialogPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
            );
            jf2.pack();
            jf2.setLocationRelativeTo(jf2.getOwner());
        }

        //======== jf3 ========
        {
            var jf3ContentPane = jf3.getContentPane();

            //======== dialogPane3 ========
            {
                dialogPane3.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane3.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
                . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing
                . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
                Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
                ) ,dialogPane3. getBorder( )) ); dialogPane3. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
                public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName (
                ) )) throw new RuntimeException( ); }} );

                //---- label7 ----
                label7.setText("\u6dfb\u52a0");

                //---- button6 ----
                button6.setText("\u53d6\u6d88");

                //---- button7 ----
                button7.setText("\u6dfb\u52a0");

                //---- label8 ----
                label8.setText("\u56fe\u4e66\u540d\u79f0:");

                //---- label9 ----
                label9.setText("\u51fa\u7248\u793e\u540d\u79f0:");

                //---- label11 ----
                label11.setText("\u56fe\u4e66\u4ef7\u683c:");

                GroupLayout dialogPane3Layout = new GroupLayout(dialogPane3);
                dialogPane3.setLayout(dialogPane3Layout);
                dialogPane3Layout.setHorizontalGroup(
                    dialogPane3Layout.createParallelGroup()
                        .addGroup(dialogPane3Layout.createSequentialGroup()
                            .addContainerGap(16, Short.MAX_VALUE)
                            .addGroup(dialogPane3Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, dialogPane3Layout.createSequentialGroup()
                                    .addComponent(label8)
                                    .addGap(28, 28, 28)
                                    .addComponent(textField5, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, dialogPane3Layout.createSequentialGroup()
                                    .addGroup(dialogPane3Layout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane3Layout.createSequentialGroup()
                                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane3Layout.createSequentialGroup()
                                            .addComponent(label11)
                                            .addGap(31, 31, 31)))
                                    .addGroup(dialogPane3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(textField8, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField9, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(115, Short.MAX_VALUE))
                        .addGroup(dialogPane3Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(button6)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                            .addComponent(button7)
                            .addGap(64, 64, 64))
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane3Layout.createSequentialGroup()
                            .addContainerGap(214, Short.MAX_VALUE)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addGap(94, 94, 94))
                );
                dialogPane3Layout.setVerticalGroup(
                    dialogPane3Layout.createParallelGroup()
                        .addGroup(dialogPane3Layout.createSequentialGroup()
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addGroup(dialogPane3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label8))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(dialogPane3Layout.createParallelGroup()
                                .addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(dialogPane3Layout.createSequentialGroup()
                                    .addComponent(textField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(18, 18, 18)
                            .addGroup(dialogPane3Layout.createParallelGroup()
                                .addComponent(label11, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                            .addGroup(dialogPane3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button6)
                                .addComponent(button7))
                            .addContainerGap())
                );
            }

            GroupLayout jf3ContentPaneLayout = new GroupLayout(jf3ContentPane);
            jf3ContentPane.setLayout(jf3ContentPaneLayout);
            jf3ContentPaneLayout.setHorizontalGroup(
                jf3ContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jf3ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dialogPane3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            jf3ContentPaneLayout.setVerticalGroup(
                jf3ContentPaneLayout.createParallelGroup()
                    .addComponent(dialogPane3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jf3.pack();
            jf3.setLocationRelativeTo(jf3.getOwner());
        }

        //======== jf4 ========
        {
            var jf4ContentPane = jf4.getContentPane();

            //======== dialogPane4 ========
            {
                dialogPane4.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane4.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
                EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing
                . border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,
                java . awt. Color .red ) ,dialogPane4. getBorder () ) ); dialogPane4. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
                { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )
                throw new RuntimeException( ) ;} } );

                //---- button10 ----
                button10.setText("\u4fee\u6539");

                //---- button13 ----
                button13.setText("\u5220\u9664");

                //---- label18 ----
                label18.setText("\u56fe\u4e66\u72b6\u6001\uff1a");

                //---- label19 ----
                label19.setText("\u56fe\u4e66\u4ef7\u683c\uff1a");

                //---- button27 ----
                button27.setText("\u67e5\u8be2\u6240\u6709");

                //---- button28 ----
                button28.setText("\u9000\u51fa\u767b\u5f55");

                GroupLayout dialogPane4Layout = new GroupLayout(dialogPane4);
                dialogPane4.setLayout(dialogPane4Layout);
                dialogPane4Layout.setHorizontalGroup(
                    dialogPane4Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label18)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textArea4, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(label19)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textArea5, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button27)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button13)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                            .addComponent(button10)
                            .addGap(18, 18, 18)
                            .addComponent(button28)
                            .addGap(44, 44, 44))
                );
                dialogPane4Layout.setVerticalGroup(
                    dialogPane4Layout.createParallelGroup()
                        .addGroup(dialogPane4Layout.createSequentialGroup()
                            .addGroup(dialogPane4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button10)
                                .addComponent(label18)
                                .addComponent(textArea4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label19)
                                .addComponent(textArea5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button27)
                                .addComponent(button13)
                                .addComponent(button28))
                            .addGap(0, 12, Short.MAX_VALUE))
                );
            }

            //---- label12 ----
            label12.setText("\u56fe\u4e66\u7f16\u53f7\uff1a");

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(textArea1);
            }

            //---- label13 ----
            label13.setText("\u56fe\u4e66\u540d\u79f0\uff1a");

            //---- label14 ----
            label14.setText("\u51fa\u7248\u793e\u540d\u79f0\uff1a");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(table1);
            }

            //---- button8 ----
            button8.setText("\u67e5\u8be2");

            //---- button9 ----
            button9.setText("\u6dfb\u52a0");

            GroupLayout jf4ContentPaneLayout = new GroupLayout(jf4ContentPane);
            jf4ContentPane.setLayout(jf4ContentPaneLayout);
            jf4ContentPaneLayout.setHorizontalGroup(
                jf4ContentPaneLayout.createParallelGroup()
                    .addGroup(jf4ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jf4ContentPaneLayout.createSequentialGroup()
                        .addGroup(jf4ContentPaneLayout.createParallelGroup()
                            .addGroup(jf4ContentPaneLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(label12)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(label13)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textArea2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label14)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textArea3, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button8)
                                .addGap(30, 30, 30)
                                .addComponent(button9))
                            .addComponent(dialogPane4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            jf4ContentPaneLayout.setVerticalGroup(
                jf4ContentPaneLayout.createParallelGroup()
                    .addGroup(jf4ContentPaneLayout.createSequentialGroup()
                        .addGroup(jf4ContentPaneLayout.createParallelGroup()
                            .addGroup(jf4ContentPaneLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jf4ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label12)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label13)
                                    .addComponent(textArea2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label14)
                                    .addComponent(textArea3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, jf4ContentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jf4ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(button8)
                                    .addComponent(button9))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dialogPane4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
            );
            jf4.pack();
            jf4.setLocationRelativeTo(jf4.getOwner());
        }

        //======== jf5 ========
        {
            var jf5ContentPane = jf5.getContentPane();

            //======== dialogPane5 ========
            {
                dialogPane5.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane5.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                new javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion"
                ,javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
                ,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12)
                ,java.awt.Color.red),dialogPane5. getBorder()));dialogPane5. addPropertyChangeListener(
                new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
                ){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException()
                ;}});

                //---- label10 ----
                label10.setText("\u5220\u9664");

                //---- button11 ----
                button11.setText("\u53d6\u6d88");

                //---- button12 ----
                button12.setText("\u5220\u9664");

                //---- label15 ----
                label15.setText("\u56fe\u4e66\u540d\u79f0:");

                //---- label16 ----
                label16.setText("\u51fa\u7248\u793e\u540d\u79f0:");

                //======== scrollPane9 ========
                {
                    scrollPane9.setViewportView(passwordField6);
                }

                //---- label17 ----
                label17.setText("\u56fe\u4e66\u4ef7\u683c:");

                //======== scrollPane10 ========
                {
                    scrollPane10.setViewportView(passwordField7);
                }

                GroupLayout dialogPane5Layout = new GroupLayout(dialogPane5);
                dialogPane5.setLayout(dialogPane5Layout);
                dialogPane5Layout.setHorizontalGroup(
                    dialogPane5Layout.createParallelGroup()
                        .addGroup(dialogPane5Layout.createSequentialGroup()
                            .addContainerGap(16, Short.MAX_VALUE)
                            .addGroup(dialogPane5Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, dialogPane5Layout.createSequentialGroup()
                                    .addComponent(label15)
                                    .addGap(28, 28, 28)
                                    .addComponent(textField6, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, dialogPane5Layout.createSequentialGroup()
                                    .addGroup(dialogPane5Layout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane5Layout.createSequentialGroup()
                                            .addComponent(label16, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane5Layout.createSequentialGroup()
                                            .addComponent(label17)
                                            .addGap(31, 31, 31)))
                                    .addGroup(dialogPane5Layout.createParallelGroup()
                                        .addComponent(scrollPane10, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane9, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(115, Short.MAX_VALUE))
                        .addGroup(dialogPane5Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(button11)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                            .addComponent(button12)
                            .addGap(64, 64, 64))
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane5Layout.createSequentialGroup()
                            .addContainerGap(214, Short.MAX_VALUE)
                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addGap(94, 94, 94))
                );
                dialogPane5Layout.setVerticalGroup(
                    dialogPane5Layout.createParallelGroup()
                        .addGroup(dialogPane5Layout.createSequentialGroup()
                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addGroup(dialogPane5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label15))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(dialogPane5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(dialogPane5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(scrollPane10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label17, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                            .addGroup(dialogPane5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button11)
                                .addComponent(button12))
                            .addContainerGap())
                );
            }

            GroupLayout jf5ContentPaneLayout = new GroupLayout(jf5ContentPane);
            jf5ContentPane.setLayout(jf5ContentPaneLayout);
            jf5ContentPaneLayout.setHorizontalGroup(
                jf5ContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jf5ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dialogPane5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            jf5ContentPaneLayout.setVerticalGroup(
                jf5ContentPaneLayout.createParallelGroup()
                    .addComponent(dialogPane5, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jf5.pack();
            jf5.setLocationRelativeTo(jf5.getOwner());
        }

        //======== jf6 ========
        {
            var jf6ContentPane = jf6.getContentPane();

            //======== dialogPane6 ========
            {
                dialogPane6.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane6.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
                0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
                . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
                red ) ,dialogPane6. getBorder () ) ); dialogPane6. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
                beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

                //---- label20 ----
                label20.setText("\u4fee\u6539");

                //---- button14 ----
                button14.setText("\u53d6\u6d88");

                //---- button15 ----
                button15.setText("\u4fee\u6539");

                //---- label21 ----
                label21.setText("\u56fe\u4e66\u540d\u79f0:");

                //---- label22 ----
                label22.setText("\u51fa\u7248\u793e\u540d\u79f0:");

                //======== scrollPane8 ========
                {
                    scrollPane8.setViewportView(passwordField8);
                }

                //---- label23 ----
                label23.setText("\u56fe\u4e66\u4ef7\u683c:");

                //======== scrollPane7 ========
                {
                    scrollPane7.setViewportView(passwordField9);
                }

                //---- label39 ----
                label39.setText("\u65b0\u56fe\u4e66\u540d\u79f0:");

                //---- label40 ----
                label40.setText("\u65b0\u51fa\u7248\u793e\u540d\u79f0:");

                //======== scrollPane6 ========
                {
                    scrollPane6.setViewportView(passwordField14);
                }

                //---- label41 ----
                label41.setText("\u65b0\u56fe\u4e66\u4ef7\u683c:");

                //---- label42 ----
                label42.setText("\u72b6\u6001:");

                //---- label43 ----
                label43.setText("\u65b0\u72b6\u6001:");

                GroupLayout dialogPane6Layout = new GroupLayout(dialogPane6);
                dialogPane6.setLayout(dialogPane6Layout);
                dialogPane6Layout.setHorizontalGroup(
                    dialogPane6Layout.createParallelGroup()
                        .addGroup(dialogPane6Layout.createSequentialGroup()
                            .addGroup(dialogPane6Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, dialogPane6Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label22))
                                .addGroup(dialogPane6Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addGroup(dialogPane6Layout.createParallelGroup()
                                        .addGroup(dialogPane6Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(label42))
                                        .addGroup(dialogPane6Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(label21)
                                            .addComponent(label23)))))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(dialogPane6Layout.createParallelGroup()
                                .addGroup(dialogPane6Layout.createSequentialGroup()
                                    .addGroup(dialogPane6Layout.createParallelGroup()
                                        .addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane6, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                    .addGroup(dialogPane6Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label41)
                                        .addComponent(label40))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(dialogPane6Layout.createParallelGroup()
                                        .addComponent(scrollPane8, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField14, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                    .addGap(114, 114, 114))
                                .addGroup(dialogPane6Layout.createSequentialGroup()
                                    .addComponent(textField7, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(label39)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textField13, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(dialogPane6Layout.createSequentialGroup()
                                    .addComponent(textField15, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                    .addGap(49, 49, 49)
                                    .addComponent(label43)
                                    .addGap(18, 18, 18)
                                    .addComponent(textField16, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(dialogPane6Layout.createSequentialGroup()
                            .addGap(195, 195, 195)
                            .addComponent(button14)
                            .addGap(183, 183, 183)
                            .addComponent(button15)
                            .addContainerGap(264, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane6Layout.createSequentialGroup()
                            .addContainerGap(344, Short.MAX_VALUE)
                            .addComponent(label20, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addGap(295, 295, 295))
                );
                dialogPane6Layout.setVerticalGroup(
                    dialogPane6Layout.createParallelGroup()
                        .addGroup(dialogPane6Layout.createSequentialGroup()
                            .addComponent(label20, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addGroup(dialogPane6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label21)
                                .addComponent(label39)
                                .addComponent(textField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(19, 19, 19)
                            .addGroup(dialogPane6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label22, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label40)
                                .addComponent(scrollPane8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(dialogPane6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label23, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label41, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(dialogPane6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label42, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label43, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                            .addGroup(dialogPane6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button14)
                                .addComponent(button15))
                            .addContainerGap())
                );
            }

            GroupLayout jf6ContentPaneLayout = new GroupLayout(jf6ContentPane);
            jf6ContentPane.setLayout(jf6ContentPaneLayout);
            jf6ContentPaneLayout.setHorizontalGroup(
                jf6ContentPaneLayout.createParallelGroup()
                    .addGroup(jf6ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dialogPane6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jf6ContentPaneLayout.setVerticalGroup(
                jf6ContentPaneLayout.createParallelGroup()
                    .addGroup(jf6ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dialogPane6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jf6.pack();
            jf6.setLocationRelativeTo(jf6.getOwner());
        }

        //======== jf7 ========
        {
            var jf7ContentPane = jf7.getContentPane();

            //======== dialogPane7 ========
            {
                dialogPane7.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane7.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
                border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
                , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
                .BOLD ,12 ), java. awt. Color. red) ,dialogPane7. getBorder( )) ); dialogPane7. addPropertyChangeListener (
                new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
                .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

                //---- label24 ----
                label24.setText("\u6ce8\u518c");

                //---- button16 ----
                button16.setText("\u53d6\u6d88");

                //---- button17 ----
                button17.setText("\u6ce8\u518c");

                //---- label25 ----
                label25.setText("\u7528\u6237\u540d:");

                //---- label26 ----
                label26.setText("\u5bc6\u7801:");

                //---- label27 ----
                label27.setText("\u786e\u8ba4\u5bc6\u7801:");

                //---- checkBox4 ----
                checkBox4.setText("\u663e\u793a\u5bc6\u7801");

                //---- label44 ----
                label44.setText("Email:");

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(passwordField15);
                }

                GroupLayout dialogPane7Layout = new GroupLayout(dialogPane7);
                dialogPane7.setLayout(dialogPane7Layout);
                dialogPane7Layout.setHorizontalGroup(
                    dialogPane7Layout.createParallelGroup()
                        .addGroup(dialogPane7Layout.createSequentialGroup()
                            .addContainerGap(65, Short.MAX_VALUE)
                            .addGroup(dialogPane7Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, dialogPane7Layout.createSequentialGroup()
                                    .addComponent(label25)
                                    .addGap(28, 28, 28)
                                    .addComponent(textField10, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, dialogPane7Layout.createSequentialGroup()
                                    .addGroup(dialogPane7Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(dialogPane7Layout.createSequentialGroup()
                                            .addComponent(label44)
                                            .addGap(31, 31, 31))
                                        .addGroup(dialogPane7Layout.createSequentialGroup()
                                            .addComponent(label27)
                                            .addGap(18, 18, 18))
                                        .addGroup(dialogPane7Layout.createSequentialGroup()
                                            .addComponent(label26, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                    .addGroup(dialogPane7Layout.createParallelGroup()
                                        .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordField11, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordField10, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkBox4)
                            .addContainerGap(43, Short.MAX_VALUE))
                        .addGroup(dialogPane7Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(button16)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                            .addComponent(button17)
                            .addGap(64, 64, 64))
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane7Layout.createSequentialGroup()
                            .addContainerGap(249, Short.MAX_VALUE)
                            .addComponent(label24, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addGap(94, 94, 94))
                );
                dialogPane7Layout.setVerticalGroup(
                    dialogPane7Layout.createParallelGroup()
                        .addGroup(dialogPane7Layout.createSequentialGroup()
                            .addComponent(label24, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addGroup(dialogPane7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label25))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(dialogPane7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label26, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(dialogPane7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkBox4)
                                .addComponent(label27, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(dialogPane7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label44, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                            .addGroup(dialogPane7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button16)
                                .addComponent(button17))
                            .addContainerGap())
                );
            }

            GroupLayout jf7ContentPaneLayout = new GroupLayout(jf7ContentPane);
            jf7ContentPane.setLayout(jf7ContentPaneLayout);
            jf7ContentPaneLayout.setHorizontalGroup(
                jf7ContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jf7ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dialogPane7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            jf7ContentPaneLayout.setVerticalGroup(
                jf7ContentPaneLayout.createParallelGroup()
                    .addComponent(dialogPane7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jf7.pack();
            jf7.setLocationRelativeTo(jf7.getOwner());
        }

        //======== jf8 ========
        {
            var jf8ContentPane = jf8.getContentPane();

            //======== dialogPane8 ========
            {
                dialogPane8.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane8.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
                . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
                . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
                awt .Font .BOLD ,12 ), java. awt. Color. red) ,dialogPane8. getBorder( )) )
                ; dialogPane8. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
                ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
                ;

                //---- button18 ----
                button18.setText("\u5f52\u8fd8");

                //---- label28 ----
                label28.setText("\u56fe\u4e66\u72b6\u6001\uff1a");

                //---- label29 ----
                label29.setText("\u56fe\u4e66\u4ef7\u683c\uff1a");

                //---- button25 ----
                button25.setText("\u67e5\u8be2\u6240\u6709");

                //---- button26 ----
                button26.setText("\u9000\u51fa\u767b\u5f55");

                GroupLayout dialogPane8Layout = new GroupLayout(dialogPane8);
                dialogPane8.setLayout(dialogPane8Layout);
                dialogPane8Layout.setHorizontalGroup(
                    dialogPane8Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label28)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textArea6, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(label29)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textArea7, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                            .addComponent(button25)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button18)
                            .addGap(18, 18, 18)
                            .addComponent(button26)
                            .addGap(26, 26, 26))
                );
                dialogPane8Layout.setVerticalGroup(
                    dialogPane8Layout.createParallelGroup()
                        .addGroup(dialogPane8Layout.createSequentialGroup()
                            .addGroup(dialogPane8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label28)
                                .addComponent(textArea6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label29)
                                .addComponent(textArea7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button18)
                                .addComponent(button25)
                                .addComponent(button26))
                            .addGap(0, 12, Short.MAX_VALUE))
                );
            }

            //---- label30 ----
            label30.setText("\u56fe\u4e66\u7f16\u53f7\uff1a");

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(textArea8);
            }

            //---- label31 ----
            label31.setText("\u56fe\u4e66\u540d\u79f0\uff1a");

            //---- label32 ----
            label32.setText("\u51fa\u7248\u793e\u540d\u79f0\uff1a");

            //======== scrollPane4 ========
            {
                scrollPane4.setViewportView(table2);
            }

            //---- button20 ----
            button20.setText("\u67e5\u8be2");

            //---- button21 ----
            button21.setText("\u501f\u9274");

            GroupLayout jf8ContentPaneLayout = new GroupLayout(jf8ContentPane);
            jf8ContentPane.setLayout(jf8ContentPaneLayout);
            jf8ContentPaneLayout.setHorizontalGroup(
                jf8ContentPaneLayout.createParallelGroup()
                    .addGroup(jf8ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jf8ContentPaneLayout.createSequentialGroup()
                        .addGroup(jf8ContentPaneLayout.createParallelGroup()
                            .addGroup(jf8ContentPaneLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(label30)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label31)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textArea9, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label32)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textArea10, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button20)
                                .addGap(30, 30, 30)
                                .addComponent(button21))
                            .addComponent(dialogPane8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            jf8ContentPaneLayout.setVerticalGroup(
                jf8ContentPaneLayout.createParallelGroup()
                    .addGroup(jf8ContentPaneLayout.createSequentialGroup()
                        .addGroup(jf8ContentPaneLayout.createParallelGroup()
                            .addGroup(jf8ContentPaneLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jf8ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label30)
                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label31)
                                    .addComponent(textArea9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label32)
                                    .addComponent(textArea10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, jf8ContentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jf8ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(button20)
                                    .addComponent(button21))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dialogPane8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
            );
            jf8.pack();
            jf8.setLocationRelativeTo(jf8.getOwner());
        }

        //======== jf9 ========
        {
            var jf9ContentPane = jf9.getContentPane();

            //======== dialogPane9 ========
            {
                dialogPane9.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane9.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
                border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
                , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
                .BOLD ,12 ), java. awt. Color. red) ,dialogPane9. getBorder( )) ); dialogPane9. addPropertyChangeListener (
                new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
                .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

                //---- label33 ----
                label33.setText("\u501f\u9274");

                //---- button19 ----
                button19.setText("\u53d6\u6d88");

                //---- button22 ----
                button22.setText("\u501f\u9274");

                //---- label34 ----
                label34.setText("\u56fe\u4e66\u540d\u79f0:");

                //---- label35 ----
                label35.setText("\u51fa\u7248\u793e\u540d\u79f0:");

                //======== scrollPane11 ========
                {
                    scrollPane11.setViewportView(passwordField12);
                }

                GroupLayout dialogPane9Layout = new GroupLayout(dialogPane9);
                dialogPane9.setLayout(dialogPane9Layout);
                dialogPane9Layout.setHorizontalGroup(
                    dialogPane9Layout.createParallelGroup()
                        .addGroup(dialogPane9Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(button19)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                            .addComponent(button22)
                            .addGap(64, 64, 64))
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane9Layout.createSequentialGroup()
                            .addContainerGap(214, Short.MAX_VALUE)
                            .addComponent(label33, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addGap(94, 94, 94))
                        .addGroup(dialogPane9Layout.createSequentialGroup()
                            .addContainerGap(30, Short.MAX_VALUE)
                            .addGroup(dialogPane9Layout.createParallelGroup()
                                .addGroup(dialogPane9Layout.createSequentialGroup()
                                    .addComponent(label35, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane11, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                .addGroup(dialogPane9Layout.createSequentialGroup()
                                    .addComponent(label34)
                                    .addGap(28, 28, 28)
                                    .addComponent(textField11, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(113, Short.MAX_VALUE))
                );
                dialogPane9Layout.setVerticalGroup(
                    dialogPane9Layout.createParallelGroup()
                        .addGroup(dialogPane9Layout.createSequentialGroup()
                            .addComponent(label33, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addGroup(dialogPane9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label34))
                            .addGap(56, 56, 56)
                            .addGroup(dialogPane9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label35, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38)
                            .addGroup(dialogPane9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button19)
                                .addComponent(button22))
                            .addContainerGap())
                );
            }

            GroupLayout jf9ContentPaneLayout = new GroupLayout(jf9ContentPane);
            jf9ContentPane.setLayout(jf9ContentPaneLayout);
            jf9ContentPaneLayout.setHorizontalGroup(
                jf9ContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jf9ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dialogPane9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            jf9ContentPaneLayout.setVerticalGroup(
                jf9ContentPaneLayout.createParallelGroup()
                    .addComponent(dialogPane9, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jf9.pack();
            jf9.setLocationRelativeTo(jf9.getOwner());
        }

        //======== jf10 ========
        {
            var jf10ContentPane = jf10.getContentPane();

            //======== dialogPane10 ========
            {
                dialogPane10.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane10.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border
                .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog"
                ,java.awt.Font.BOLD,12),java.awt.Color.red),dialogPane10. getBorder
                ()));dialogPane10. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
                .beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException
                ();}});

                //---- label36 ----
                label36.setText("\u5f52\u8fd8");

                //---- button23 ----
                button23.setText("\u53d6\u6d88");

                //---- button24 ----
                button24.setText("\u5f52\u8fd8");

                //---- label37 ----
                label37.setText("\u56fe\u4e66\u540d\u79f0:");

                //---- label38 ----
                label38.setText("\u51fa\u7248\u793e\u540d\u79f0:");

                //======== scrollPane12 ========
                {
                    scrollPane12.setViewportView(passwordField13);
                }

                GroupLayout dialogPane10Layout = new GroupLayout(dialogPane10);
                dialogPane10.setLayout(dialogPane10Layout);
                dialogPane10Layout.setHorizontalGroup(
                    dialogPane10Layout.createParallelGroup()
                        .addGroup(dialogPane10Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(button23)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                            .addComponent(button24)
                            .addGap(64, 64, 64))
                        .addGroup(GroupLayout.Alignment.TRAILING, dialogPane10Layout.createSequentialGroup()
                            .addContainerGap(214, Short.MAX_VALUE)
                            .addComponent(label36, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
                            .addGap(94, 94, 94))
                        .addGroup(dialogPane10Layout.createSequentialGroup()
                            .addContainerGap(30, Short.MAX_VALUE)
                            .addGroup(dialogPane10Layout.createParallelGroup()
                                .addGroup(dialogPane10Layout.createSequentialGroup()
                                    .addComponent(label38, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane12, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                .addGroup(dialogPane10Layout.createSequentialGroup()
                                    .addComponent(label37)
                                    .addGap(28, 28, 28)
                                    .addComponent(textField12, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(113, Short.MAX_VALUE))
                );
                dialogPane10Layout.setVerticalGroup(
                    dialogPane10Layout.createParallelGroup()
                        .addGroup(dialogPane10Layout.createSequentialGroup()
                            .addComponent(label36, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addGroup(dialogPane10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label37))
                            .addGap(56, 56, 56)
                            .addGroup(dialogPane10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label38, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38)
                            .addGroup(dialogPane10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button23)
                                .addComponent(button24))
                            .addContainerGap())
                );
            }

            GroupLayout jf10ContentPaneLayout = new GroupLayout(jf10ContentPane);
            jf10ContentPane.setLayout(jf10ContentPaneLayout);
            jf10ContentPaneLayout.setHorizontalGroup(
                jf10ContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, jf10ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dialogPane10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            jf10ContentPaneLayout.setVerticalGroup(
                jf10ContentPaneLayout.createParallelGroup()
                    .addComponent(dialogPane10, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jf10.pack();
            jf10.setLocationRelativeTo(jf10.getOwner());
        }

        //======== jf11 ========
        {
            var jf11ContentPane = jf11.getContentPane();

            //======== dialogPane11 ========
            {
                dialogPane11.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane11.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing.border
                .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
                .Color.red),dialogPane11. getBorder()));dialogPane11. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
                propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
                ;}});

                //---- label46 ----
                label46.setText("\u8d26\u53f7:");

                //---- label47 ----
                label47.setText("\u90ae\u7bb1\u8d26\u53f7:");

                //---- button33 ----
                button33.setText("\u9a8c\u8bc1\u90ae\u7bb1");

                GroupLayout dialogPane11Layout = new GroupLayout(dialogPane11);
                dialogPane11.setLayout(dialogPane11Layout);
                dialogPane11Layout.setHorizontalGroup(
                    dialogPane11Layout.createParallelGroup()
                        .addGroup(dialogPane11Layout.createSequentialGroup()
                            .addGroup(dialogPane11Layout.createParallelGroup()
                                .addGroup(dialogPane11Layout.createSequentialGroup()
                                    .addGap(93, 93, 93)
                                    .addComponent(label46, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE))
                                .addGroup(dialogPane11Layout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(label47, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(button33)))
                            .addContainerGap(63, Short.MAX_VALUE))
                );
                dialogPane11Layout.setVerticalGroup(
                    dialogPane11Layout.createParallelGroup()
                        .addGroup(dialogPane11Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(dialogPane11Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label46, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(22, 22, 22)
                            .addGroup(dialogPane11Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label47)
                                .addComponent(button33))
                            .addGap(97, 97, 97))
                );
            }

            GroupLayout jf11ContentPaneLayout = new GroupLayout(jf11ContentPane);
            jf11ContentPane.setLayout(jf11ContentPaneLayout);
            jf11ContentPaneLayout.setHorizontalGroup(
                jf11ContentPaneLayout.createParallelGroup()
                    .addGroup(jf11ContentPaneLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(dialogPane11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
            );
            jf11ContentPaneLayout.setVerticalGroup(
                jf11ContentPaneLayout.createParallelGroup()
                    .addGroup(jf11ContentPaneLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dialogPane11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
            );
            jf11.pack();
            jf11.setLocationRelativeTo(jf11.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFor  Designer - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private static JFrame jf1;
    private static JPanel dialogPane;
    private static JLabel label1;
    private static JButton button1;
    private static JButton button2;
    private static JLabel label2;
    private static JTextField textField1;
    private static JLabel label3;
    private static JButton button3;
    private static JPasswordField passwordField5;
    private static JCheckBox checkBox1;
    private static JButton button29;
    private static JFrame jf2;
    private static JPanel dialogPane2;
    private static JLabel label4;
    private static JButton button4;
    private static JButton button5;
    private static JLabel label5;
    private static JTextField textField3;
    private static JLabel label6;
    private static JPasswordField passwordField4;
    private static JCheckBox checkBox2;
    private static JFrame jf3;
    private static JPanel dialogPane3;
    private static JLabel label7;
    private static JButton button6;
    private static JButton button7;
    private static JLabel label8;
    private static JTextField textField5;
    private static JLabel label9;
    private static JLabel label11;
    private static JTextField textField8;
    private static JTextField textField9;
    private static JFrame jf4;
    private static JPanel dialogPane4;
    private static JButton button10;
    private static JButton button13;
    private static JLabel label18;
    private static JTextArea textArea4;
    private static JLabel label19;
    private static JTextArea textArea5;
    private static JButton button27;
    private static JButton button28;
    private static JLabel label12;
    private static JScrollPane scrollPane1;
    private static JTextArea textArea1;
    private static JLabel label13;
    private static JTextArea textArea2;
    private static JLabel label14;
    private static JScrollPane scrollPane2;
    private static JTable table1;
    private static JTextArea textArea3;
    private static JButton button8;
    private static JButton button9;
    private static JFrame jf5;
    private static JPanel dialogPane5;
    private static JLabel label10;
    private static JButton button11;
    private static JButton button12;
    private static JLabel label15;
    private static JTextField textField6;
    private static JLabel label16;
    private static JScrollPane scrollPane9;
    private static JTextArea passwordField6;
    private static JLabel label17;
    private static JScrollPane scrollPane10;
    private static JTextArea passwordField7;
    private static JFrame jf6;
    private static JPanel dialogPane6;
    private static JLabel label20;
    private static JButton button14;
    private static JButton button15;
    private static JLabel label21;
    private static JTextField textField7;
    private static JLabel label22;
    private static JScrollPane scrollPane8;
    private static JTextArea passwordField8;
    private static JLabel label23;
    private static JScrollPane scrollPane7;
    private static JTextArea passwordField9;
    private static JLabel label39;
    private static JTextField textField13;
    private static JLabel label40;
    private static JScrollPane scrollPane6;
    private static JTextArea passwordField14;
    private static JLabel label41;
    private static JTextField textField14;
    private static JLabel label42;
    private static JTextField textField15;
    private static JLabel label43;
    private static JTextField textField16;
    private static JFrame jf7;
    private static JPanel dialogPane7;
    private static JLabel label24;
    private static JButton button16;
    private static JButton button17;
    private static JLabel label25;
    private static JTextField textField10;
    private static JLabel label26;
    private static JPasswordField passwordField10;
    private static JLabel label27;
    private static JPasswordField passwordField11;
    private static JCheckBox checkBox4;
    private static JLabel label44;
    private static JScrollPane scrollPane5;
    private static JTextArea passwordField15;
    private static JFrame jf8;
    private static JPanel dialogPane8;
    private static JButton button18;
    private static JLabel label28;
    private static JTextArea textArea6;
    private static JLabel label29;
    private static JTextArea textArea7;
    private static JButton button25;
    private static JButton button26;
    private static JLabel label30;
    private static JScrollPane scrollPane3;
    private static JTextArea textArea8;
    private static JLabel label31;
    private static JTextArea textArea9;
    private static JLabel label32;
    private static JScrollPane scrollPane4;
    private static JTable table2;
    private static JTextArea textArea10;
    private static JButton button20;
    private static JButton button21;
    private static JFrame jf9;
    private static JPanel dialogPane9;
    private static JLabel label33;
    private static JButton button19;
    private static JButton button22;
    private static JLabel label34;
    private static JTextField textField11;
    private static JLabel label35;
    private static JScrollPane scrollPane11;
    private static JTextArea passwordField12;
    private static JFrame jf10;
    private static JPanel dialogPane10;
    private static JLabel label36;
    private static JButton button23;
    private static JButton button24;
    private static JLabel label37;
    private static JTextField textField12;
    private static JLabel label38;
    private static JScrollPane scrollPane12;
    private static JTextArea passwordField13;
    private static JFrame jf11;
    private static JPanel dialogPane11;
    private static JLabel label46;
    private static JTextField textField2;
    private static JLabel label47;
    private static JTextField textField4;
    private static JButton button33;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
