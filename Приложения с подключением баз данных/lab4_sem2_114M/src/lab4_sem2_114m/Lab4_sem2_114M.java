package lab4_sem2_114m;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab4_sem2_114M {
    public static void main(String[] args) throws SQLException {
    //данные для соединения с БД
        String url = "jdbc:postgresql://localhost:5432/basejava";
        String user = "postgres";
        String password = "qazxsw";
        //Выделение Connection-объекта для подключения к серверу базы данных
        //Операции JDBC выполняются с помощью объектов Connection, Statement и ResultSet
        try (Connection dbConnection = DriverManager.getConnection(url, user, password);
                //Выделение Statement-объекта под Connection, созданный ранее для выполнения SQL-команды.
                Statement st = dbConnection.createStatement();
                //запрос sql на получение данных из талицы посещаемости
                ResultSet rs = st.executeQuery("SELECT s_name, d_name,t_name, a_date FROM attendance,students, discipline,teachers \n"
                        + "WHERE a_discipline = d_id and a_student= s_id and t_discipline=d_id order by d_name")) {
            //вывод полученных данных из таблицы посещаемости
            System.out.println(" студент  |  дисциплина  | преподаватель | дата посещения");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getDate(4));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
