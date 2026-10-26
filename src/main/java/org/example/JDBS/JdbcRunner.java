package org.example.JDBS;

import org.example.JDBS.DAO.CustomersDao;
import org.example.JDBS.dto.CustomersFilter;
import org.example.JDBS.utils.ConnectionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {
    static void main() {
        CustomersDao customersDao = CustomersDao.getInstance();
//        System.out.println(customersDao.getById(2L).get());
        //        System.out.println(customersDao.save(new Customers("Никита"
//                , "reklow@gmail.com",
//                "Гомель",
//                Date.valueOf(LocalDate.now()))));
//        System.out.println(customersDao.update(new Customers(10L,
//                "Никита"
//                , "reklowwolker@gmail.com",
//                "МИНСК",
//                Date.valueOf(LocalDate.now()))));
//        System.out.println(customersDao.getAll());
//        System.out.println("__________________________________");
//        System.out.println(customersDao.delete(10L));
//        System.out.println(customersDao.getAll());
        System.out.println(customersDao.getAll(new CustomersFilter("Иван Петров","Минск",3,0)));

    }

    public static List<Long> getCustomersIdByOrders(Long customerId) {
        String sql = """
                SELECT id FROM orders WHERE customer_id = ?;
                """;
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, customerId);

            ResultSet resultSet = statement.executeQuery();
            List<Long> ordersId = new ArrayList<>();
            while (resultSet.next()) {
                ordersId.add(resultSet.getLong(1));
            }
            return ordersId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Long> getCustomersIdByOrders_v2(Long customerId) {
        String sql = """
                SELECT id FROM orders 
                WHERE customer_id = %s;
                """.formatted(customerId);
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            List<Long> ordersId = new ArrayList<>();
            while (result.next()) {
                ordersId.add(result.getLong(1));
            }
            return ordersId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Long> getOrdersIdBetweenData(LocalDateTime startDate, LocalDateTime endDate) {
        String sql = """
                        SELECT id FROM orders
                        WHERE created_at BETWEEN ? AND ?;
                """;
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(startDate.toLocalDate()));
            statement.setDate(2, Date.valueOf(endDate.toLocalDate()));
            //распределили наш запрос на части 2 - 2 - 2
            statement.setFetchSize(2);
            //максимальное кол-во результатирующих строк (2)
            statement.setMaxRows(2);
            //таймаут запрос
            statement.setQueryTimeout(1);
            ResultSet resultSet = statement.executeQuery();
            List<Long> ordersId = new ArrayList<>();
            while (resultSet.next()) {
                // позволяет на получить результативную таблицу и работать только с ней -> отключится от бд
//                RowSetFactory factory = RowSetProvider.newFactory();
//                CachedRowSet cachedRowSet = factory.createCachedRowSet();
//                cachedRowSet.populate(resultSet);
                ordersId.add(resultSet.getLong(1));
            }
            System.out.println(ordersId);
            return ordersId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}