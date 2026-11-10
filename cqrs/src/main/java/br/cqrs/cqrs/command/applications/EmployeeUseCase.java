package br.cqrs.cqrs.command.applications;

import br.cqrs.cqrs.command.entity.Employee;
import br.cqrs.cqrs.command.entity.EmployeeCreatedEvent;
import br.cqrs.cqrs.command.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeUseCase {

  private final EmployeeRepository employeeRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  public Employee create(EmployeeCommand command) {
    Employee employee = new Employee(command.getName());
    employeeRepository.save(employee);
    applicationEventPublisher.publishEvent(new EmployeeCreatedEvent(employee.getId(),employee.getName()));
    return employee;
  }

}
