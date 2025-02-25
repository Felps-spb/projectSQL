package banco.SQL.teste.projectSQL.persistence.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class EmployeeEntity {
    private long id;
    private String name;
    private BigDecimal salary;
    private OffsetDateTime birthday;

}
