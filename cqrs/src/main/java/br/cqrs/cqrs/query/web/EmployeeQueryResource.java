package br.cqrs.cqrs.query.web;

import java.util.List;

import br.cqrs.cqrs.query.entity.EmployeeQuery;
import br.cqrs.cqrs.query.repository.EmployeeQueryRepository;
import br.cqrs.cqrs.query.utils.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@QueryService
@RequiredArgsConstructor
public class EmployeeQueryResource {

  private final EmployeeQueryRepository employeeQueryRepository;

  @GetMapping
  public List<EmployeeQuery> findAll() {
    return employeeQueryRepository.findAll();
  }

}
