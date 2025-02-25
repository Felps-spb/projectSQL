package banco.SQL.teste.projectSQL.persistence.entity;

import banco.SQL.teste.projectSQL.persistence.ConnectionUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {
    public void insert(final EmployeeEntity employee) {
        try {
            var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO employees (name, salary, birthday) VALUES" +
                    " ('" + employee.getName() + "', '" + employee.getSalary().toString() +
                    "', '" + formatOffsetDateTime(employee.getBirthday()) + "')");
            statement.close();
            System.out.println("Inserted " + employee.getName() + " " + employee.getSalary());
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void update(final EmployeeEntity employee) {
        try {
            var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement();

            statement.executeUpdate(
                    "UPDATE employees SET " +
                            "name = '" + employee.getName() + "', " +
                            "salary = " + employee.getSalary().toString() + ", " +
                            "birthday = '" + formatOffsetDateTime(employee.getBirthday()) + "' " +
                            "WHERE id = " + employee.getId()
            );
            statement.close();
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void delete(final EmployeeEntity employee) {
        try{
            var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM employees WHERE id = '" + employee.getId() + "'");
            statement.close();
            System.out.println("Deleted " + employee.getName() + " " + employee.getSalary());
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public List<EmployeeEntity> findAll() {
        List<EmployeeEntity> employees = new ArrayList<>();
        try {
            var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement();

            statement.executeQuery("SELECT * FROM employees");
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var employee = new EmployeeEntity();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                employee.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                employees.add(employee);
            }
            statement.close();
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
        return employees;
    }

    public EmployeeEntity findById(final long id) {
        var employee = new EmployeeEntity();
        try {
            var connection = ConnectionUtil.getConnection();
            var statement = connection.createStatement();

            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                employee.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
            }
            statement.close();
        }catch (SQLException err) {
            throw new RuntimeException(err);
        }
        return employee;
    }


    private String formatOffsetDateTime(final OffsetDateTime dateTime) {
        var utcDateTime = dateTime.withOffsetSameInstant(UTC);
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
