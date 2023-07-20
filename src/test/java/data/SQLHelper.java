package data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.SneakyThrows;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();
    private static String url = System.getProperty("db.url");
    private static String userDB = System.getProperty("app.userDB");
    private static String password = System.getProperty("app.password");

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, userDB, password);
    }

    public static void clearData() throws SQLException {
        var conn = DriverManager.getConnection(url, userDB, password);
        runner.update(conn, "DELETE FROM credit_request_entity;");
        runner.update(conn, "DELETE FROM payment_entity;");
        runner.update(conn, "DELETE FROM order_entity;");
    }

    @SneakyThrows
    public static Long getRecordsCountFromOrderEntity() {
        var countSQL = "SELECT COUNT(*) FROM order_entity";
        var conn = getConn();
        return runner.query(conn, countSQL, new ScalarHandler<Long>());
        /*try (var conn = getConn()) {
            var number = runner.query(conn, countSQL, new ScalarHandler<Long>());
            return number;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;*/
    }

    @SneakyThrows
    public static Long getRecordsCountFromPaymentEntity() {
        var countSQL = "SELECT COUNT(*) FROM payment_entity";
        var conn = getConn();
        return runner.query(conn, countSQL, new ScalarHandler<Long>());
        /*try (var conn = getConn()) {
            var number = runner.query(conn, countSQL, new ScalarHandler<Long>());
            return number;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;*/
    }
}
