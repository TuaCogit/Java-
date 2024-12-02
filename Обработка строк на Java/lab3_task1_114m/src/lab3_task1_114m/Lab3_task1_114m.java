package lab3_task1_114m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Lab3_task1_114m {

    //JDBC драйвер для работы с postgresql
    static String driver = "org.postgresql.Driver";
    //данные для соединения с БД
    static String connection = "jdbc:postgresql://localhost:5432/basejava";
    static String user = "postgres";
    static String password = "qazxsw";

    public static void main(String[] args) {
        try {
            //вызов метода добавления данных из одной табл. в другую с условием
            insertWhereTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //метод соединение с БД
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(driver); //указываем JDBC драйвер
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            //JDBC драйвер обеспечивает соединение с базой данных
            dbConnection = DriverManager.getConnection(connection, user, password);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    private static void insertWhereTable() throws SQLException {
        Connection dbConnection = null; //соединение
        Statement statement = null; //создание запросов
        String id_Group = "109"; //номер группы
        int dolgCount = 1; //кол-во долгов
        //sql запрос получения всех данных из табл.
        String selectTableSQL = "SELECT * FROM \"T_GroupSelected\" ";
        //sql запрос добавления данных из одной таблицы в другую 
        //с условием, что номер группы = id_Group и кол-во долгов = dolgCount, заданным выше в коде
        String insertTableSQL = "INSERT INTO \"T_GroupSelected\" (\"id_Student\", \"firstName\", \"lastName\", \"id_Group\")\n"
                + "   SELECT \"id_Student\", \"firstName\", \"lastName\", \"id_Group\"\n"
                + "   FROM \"T_Student\"\n"
                + "    WHERE \"id_Group\"='" + id_Group + "' AND \"dolgCount\">" + dolgCount;
        try {
            dbConnection = getDBConnection(); //метод соединения с БД
            //создание объекта для отправки инструкций SQL в базу данных
            statement = dbConnection.createStatement(); 
            //метод для выполнения команд SQL добавления
            statement.executeUpdate(insertTableSQL);
            //результаты запроса выполнения команды SELECT
            ResultSet rs = statement.executeQuery(selectTableSQL);
            System.out.println("В таблицу добавлены:");
            //выборка из результатов 
            while (rs.next()) {
                int id = rs.getInt(1); //id_Student
                String rsFirstName = rs.getString(2); //firstName
                String rsLastName = rs.getString(3); //lastName
                String rsId_Group = rs.getString(4); //id_Group
                //вывод в консоль добавленных из табл. в табл. студентов
                System.out.println("Студент: " + id + " " + rsFirstName + " " + rsLastName + " " + rsId_Group);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        dbConnection.close();
    }

}
