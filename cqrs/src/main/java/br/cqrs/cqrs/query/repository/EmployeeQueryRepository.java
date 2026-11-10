package br.cqrs.cqrs.query.repository;

import br.cqrs.cqrs.query.entity.EmployeeQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeQueryRepository extends JpaRepository<EmployeeQuery, Long> {
}
