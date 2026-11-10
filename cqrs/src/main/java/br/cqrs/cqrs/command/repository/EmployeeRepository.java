package br.cqrs.cqrs.command.repository;

import br.cqrs.cqrs.command.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
