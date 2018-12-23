package question7;

import java.sql.*;

public class SQLInsert {
        static int i = 1;
        Connection con;
        PreparedStatement statement;
        ResultSet rs;

        public Connection getCon() {
            return con;
        }
        public Statement getStatement(){
            return statement;
        }

        public ResultSet getRs() {
            return rs;
        }
        public SQLInsert(Connection con) {
            this.con = con;
        }
        public void insert(Object xh, Object xm, Object xmEn, Object xb, Object bj, Object zyh, Object zym, Object yxh, Object yxm, Object nj, Object csrq, Object xjzt, Object rxrq
        , Object yxmen, Object zymen, Object xz, Object mz) throws SQLException {

            while(i == 1){
            String sql= "drop table if exists mlgb.StuInfo";
            String sql1 ="CREATE TABLE mlgb.StuInfo ("
                   +" `学号` varchar(50) NOT NULL,"
           +" `姓名` varchar(50) NOT NULL,"
           + "`英文名` varchar(50) NOT NULL,"
            +"`性别` varchar(50) NOT NULL,"
            +"`班级` varchar(50) NOT NULL,"
           + "`专业号` varchar(50) NOT NULL,"
           + "`专业名` varchar(50) NOT NULL,"
           + "`院系号` varchar(50) NOT NULL,"
           + "`院系名` varchar(50) NOT NULL,"
          +  "`年级` varchar(50) NOT NULL,"
           + "`出生日期` varchar(50) NOT NULL,"
           + "`学籍状态` varchar(50) NOT NULL,"
           + "`入学日期` varchar(50) NOT NULL,"
           + "`英文院系名` varchar(50) NOT NULL,"
           + "`中文院系名` varchar(50) NOT NULL,"
           + "`学制` varchar(50) NOT NULL,"
            +"`民族` varchar(50) NOT NULL,"
            + "PRIMARY KEY (`学号`))";
                statement = con.prepareStatement(sql);
                statement.executeUpdate();
                statement = con.prepareStatement(sql1);
                statement.executeUpdate();
                i++;
            }
            String sql2 = "INSERT into mlgb.StuInfo(学号,姓名,英文名,性别,班级,专业号,专业名,院系号,院系名,年级,出生日期,学籍状态,入学日期,英文院系名," +
                    "中文院系名,学制," +
                    "民族) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            statement = con.prepareStatement(sql2);
            statement.setString(1, String.valueOf(xh));
            statement.setString(2, String.valueOf(xm));
            statement.setString(3, String.valueOf(xmEn));
            statement.setString(4, String.valueOf(xb));
            statement.setString(5, String.valueOf(bj));
            statement.setString(6, String.valueOf(zyh));
            statement.setString(7, String.valueOf(zym));
            statement.setString(8, String.valueOf(yxh));
            statement.setString(9, String.valueOf(yxm));
            statement.setString(10, String.valueOf(nj));
            statement.setString(11, String.valueOf(csrq));
            statement.setString(12, String.valueOf(xjzt));
            statement.setString(13, String.valueOf(rxrq));
            statement.setString(14, String.valueOf(yxmen));
            statement.setString(15, String.valueOf(zymen));
            statement.setString(16, String.valueOf(xz));
            statement.setString(17, String.valueOf(mz));
            statement.executeUpdate();
            JDBCUtil.close(statement,con);
        }





}
