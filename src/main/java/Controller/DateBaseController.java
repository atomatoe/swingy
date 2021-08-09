package Controller;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DateBaseController {
    private static DateBaseController instance;
    private static Connection connection;
    private static Statement statement;
    private static final String DB_Driver = "org.h2.Driver";
    private boolean connection_error;

    private DateBaseController() {
        try {
            this.connection_error = false;
            String DB_URL = "jdbc:h2:/." + System.getProperty("user.dir") + "/src/main/resources/database/swingy;mv_store=false";
            Class.forName(DB_Driver); // Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection(DB_URL); // соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            statement = connection.createStatement();
            try {
                statement.executeUpdate("CREATE TABLE heroes (" +
                        "id bigint generated always as identity," +
                        "name VARCHAR(20) NOT NULL," +
                        "class VARCHAR(20) NOT NULL," +
                        "attack INT NOT NULL," +
                        "defense INT NOT NULL," +
                        "hitPoints INT NOT NULL," +
                        "lvl INT NOT NULL," +
                        "exp INT NOT NULL," +
                        "coordinatesX INT NOT NULL," +
                        "coordinatesY INT NOT NULL," +
                        "photo VARCHAR(100) NOT NULL," +
                        "photoFace VARCHAR(100) NOT NULL," +
                        "photoLeft VARCHAR(100) NOT NULL," +
                        "photoRight VARCHAR(100) NOT NULL," +
                        "photoBehind VARCHAR(100) NOT NULL," +
                        "axe INT NOT NULL," +
                        "armor INT NOT NULL," +
                        "helm INT NOT NULL," +
                        "killedMonsters INT NOT NULL," +
                        "allSteps INT NOT NULL" +
                        ");");
            } catch (Exception e) {
                System.out.println("База данных уже создана!");
            }
        } catch (ClassNotFoundException e) {
//            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
            this.connection_error = true;
        } catch (SQLException e) {
//            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL!");
            this.connection_error = true;
        }
    }

    public void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
//            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL Update!");
            this.connection_error = true;
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
//            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            this.connection_error = true;
            System.out.println("Ошибка SQL Query!");
            return null;
        }
        return resultSet;
    }

    public void close_connection() {
        try {
            connection.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
        } catch (SQLException e) {
            System.out.println("Ошибка в отключении соединения с ДБ !");
            this.connection_error = true;
        }
    }

    public static DateBaseController getInstance() {
        if(instance == null) {
            instance = new DateBaseController();
        }
        return instance;
    }

    public boolean isConnection_error() {
        return connection_error;
    }
}
