package org.example.JDBS.DAO;

import org.example.JDBS.Entity.Customers;
import org.example.JDBS.dto.CustomersFilter;
import org.example.JDBS.utils.ConnectionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomersDao implements CrudDao<Customers> {
    private final static CustomersDao INSTANCE = new CustomersDao();
    private final static String SAVE_SQL = """
            INSERT INTO customers(full_name, email, city, registered_at)
            VALUES (?,?,?,?)
            """;
    private final static String DELETE_SQL = """
            DELETE FROM customers c 
            WHERE c.id = ?
            """;
    private final static String FIND_BY_ID_SQL = """
            SELECT * FROM customers c
            WHERE c.id = ?
            """;
    private final static String UPDATE_SQL = """
            UPDATE customers c
            SET full_name = ?, email = ?, city = ?, registered_at = ?
            WHERE c.id = ?
            """;
    private static final String GET_ALL_SQL = """
            SELECT id, full_name, email, city, registered_at FROM customers c
            """;


    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customers> getAll() {
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(GET_ALL_SQL)) {
            var resultSet = statement.executeQuery();
            List<Customers> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(buildCustomers(resultSet));
            }
            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Customers> getAll(CustomersFilter filter) {
        List<Object> params = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.full_name() != null) {
            params.add(filter.full_name());
            whereSql.add("full_name = ?");
        }
        if (filter.city() != null) {
            params.add(filter.city());
            whereSql.add("city = ?");
        }
        params.add(filter.limit());
        params.add(filter.offset());
        var where = whereSql.stream().collect(Collectors.joining(" AND ",
                " WHERE ",
                " LIMIT ? OFFSET ? "));
        String sql = GET_ALL_SQL + where;
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            var resultSet = statement.executeQuery();
            List<Customers> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(buildCustomers(resultSet));
            }
            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Customers> getById(Long id) {
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            Customers optionalCustomers = null;
            if (resultSet.next()) {
                optionalCustomers = buildCustomers(resultSet);
            }
            return Optional.ofNullable(optionalCustomers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customers save(Customers entity) {
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getCity());
            statement.setDate(4, (Date) entity.getRegistrationDate());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Customers entity) {
        try (var connection = ConnectionManager.openConnection();
             var statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getCity());
            statement.setDate(4, (Date) entity.getRegistrationDate());
            statement.setLong(5, entity.getId());
            return statement.executeUpdate() == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Customers buildCustomers(ResultSet rs) throws SQLException {
        return new Customers(rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getString("city"),
                rs.getDate("registered_at"));
    }

    public static CustomersDao getInstance() {
        return INSTANCE;
    }

    private CustomersDao() {
    }
}
