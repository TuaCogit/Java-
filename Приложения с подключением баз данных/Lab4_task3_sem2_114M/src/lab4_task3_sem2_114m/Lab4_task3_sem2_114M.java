package lab4_task3_sem2_114m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lab4_task3_sem2_114M {
    public static void main(String[] args) {
        //данные для соединения с БД
        String url = "jdbc:postgresql://localhost:5432/basejava";
        String user = "postgres";
        String password = "qazxsw";
        //запрос на получение данных из таблицы
        String selectTableSQL = "SELECT s_name, d_name,t_name, a_date FROM attendance,students, discipline,teachers \n"
                        + "WHERE a_discipline = d_id and a_student= s_id and t_discipline=d_id order by d_name";
        //Выделение Connection-объекта для подключения к серверу базы данных
        //Операции JDBC выполняются с помощью объектов Connection, PreparedStatement и ResultSet
        try (Connection dbConnection = DriverManager.getConnection(url, user, password);
                //Использования PreparedStatement для формирования запроса
                PreparedStatement pst = dbConnection.prepareStatement(selectTableSQL);
                ResultSet rs = pst.executeQuery()) {
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
