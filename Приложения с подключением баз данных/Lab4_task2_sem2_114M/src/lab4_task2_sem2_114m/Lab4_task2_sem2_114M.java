package lab4_task2_sem2_114m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lab4_task2_sem2_114M {
     //данные для соединения с БД
    static String connection = "jdbc:postgresql://localhost:5432/basejava";
    static String user = "postgres";
    static String password = "qazxsw";
    public static void main(String[] args) {
        try {//вызов метода вывода данных из таблицы успеваемости
            SelectTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //метод соединение с БД
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {//JDBC драйвер обеспечивает соединение с базой данных
            dbConnection = DriverManager.getConnection(connection, user, password);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    private static void SelectTable() throws SQLException {
        Connection dbConnection = null; //соединение
        Statement statement = null; //создание запросов
        //sql запрос получения всех данных из табл.
        String selectTableSQL = "SELECT a_id, s_name, d_name,t_name, a_date FROM attendance,students, discipline,teachers \n"
                        + "WHERE a_discipline = d_id and a_student= s_id and t_discipline=d_id order by a_id";
        //sql запрос добавления объекта
        String insertTableSQL = "INSERT INTO attendance (a_id, a_student, a_discipline,a_date) VALUES (5, 3, 1, '1.04.2023');";
        //sql запрос изменения объекта
        String updateTableSQL = "UPDATE attendance SET a_student = 4 WHERE a_id = 2";
        //sql запрос удаления объекта
        String deleteTableSQL = "DELETE FROM attendance WHERE a_id=1";
        //sql запрос поиска по параметру
        String searchTableSQL = "SELECT s_name, d_name, a_date FROM attendance,students,discipline \n"
                        + "WHERE a_discipline = d_id and a_student= s_id and d_name='Математика'";
        try {
            dbConnection = getDBConnection(); //метод соединения с БД
            //создание объекта для отправки инструкций SQL в базу данных
            statement = dbConnection.createStatement(); 
            //метод для выполнения команд SQL добавления
            statement.executeUpdate(insertTableSQL);
           //метод для выполнения команд SQL изменения
           statement.executeUpdate(updateTableSQL);
           //метод для выполнения команд SQL удаления
           statement.executeUpdate(deleteTableSQL);
            //результаты запроса выполнения команды SELECT
            ResultSet rs = statement.executeQuery(selectTableSQL);
           //вывод полученных данных из таблицы посещаемости
            System.out.println("№| студент  |  дисциплина  | преподаватель | дата посещения");
            while (rs.next()) {
                System.out.println(rs.getInt(1)+ " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getDate(5));
            } 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        dbConnection.close();
    }

}
