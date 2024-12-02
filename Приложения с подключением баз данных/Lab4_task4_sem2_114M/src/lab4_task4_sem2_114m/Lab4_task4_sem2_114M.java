package lab4_task4_sem2_114m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lab4_task4_sem2_114M {
    //данные для соединения с БД
    static String url = "jdbc:postgresql://localhost:5432/basejava";
    static String user = "postgres";
    static String password = "qazxsw";

    public static void main(String[] args) {
        try {
            //вызов метода вывода данных из таблицы успеваемости
            SelectTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void SelectTable() throws SQLException {
        //sql запрос получения всех данных из табл.
        String selectTableSQL = "SELECT s_name, d_name,t_name, a_date FROM attendance,students, discipline,teachers \n"
                + "WHERE a_discipline = d_id and a_student= s_id and t_discipline=d_id order by d_name";
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
