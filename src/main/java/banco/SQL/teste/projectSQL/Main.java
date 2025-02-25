package banco.SQL.teste.projectSQL;
import banco.SQL.teste.projectSQL.persistence.entity.EmployeeDAO;
import banco.SQL.teste.projectSQL.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


public class Main {

    private final static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {

        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbc_sample", "root", "")
                .load();
        flyway.migrate();

        //CRIAR PESSOA:

        /*
        var employee = new EmployeeEntity();
        employee.setName("Luiz Felipe");
        employee.setSalary(new BigDecimal("3500"));
        employee.setBirthday(OffsetDateTime.now().minusYears(18));
        System.out.println(employee);
        employeeDAO.insert(employee);*/

        //EXIBE TODOS DA TABELA
        employeeDAO.findAll().forEach(System.out::println);

        //EXIBE POR ID
       /*
       System.out.println(employeeDAO.findById(2));
       */

        //DELETA PESSOA
        /*
        employeeDAO.delete(employeeDAO.findById(2));
        */

        //ALTERA PESSOA NA TABELA
        /*
        var employee = new EmployeeEntity();
        employee.setId(3);
        employee.setName("John Doe");
        employee.setSalary(new BigDecimal("10000.00"));
        employee.setBirthday(OffsetDateTime.now().minusYears(18));
        employeeDAO.update(employee);
        employeeDAO.findAll().forEach(System.out::println);
        */
    }
}
