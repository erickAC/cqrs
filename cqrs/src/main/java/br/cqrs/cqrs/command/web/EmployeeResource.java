package br.cqrs.cqrs.command.web;

import br.cqrs.cqrs.command.applications.EmployeeCommand;
import br.cqrs.cqrs.command.applications.EmployeeUseCase;
import br.cqrs.cqrs.command.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeResource {

  private final EmployeeUseCase employeeUseCase;

  @PostMapping
  public ResponseEntity<Long> create(@RequestBody EmployeeCommand command) {
    Employee employee = employeeUseCase.create(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(employee.getId());
  }

}
