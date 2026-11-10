package br.cqrs.cqrs.listeners;

import br.cqrs.cqrs.command.entity.EmployeeCreatedEvent;
import br.cqrs.cqrs.query.entity.EmployeeQuery;
import br.cqrs.cqrs.query.repository.EmployeeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class EmployeeListener {

  private final EmployeeQueryRepository employeeQueryRepository;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  @Transactional(transactionManager = "queryTransactionManager", propagation = Propagation.REQUIRES_NEW)
  public void on(EmployeeCreatedEvent event) {
    EmployeeQuery employeeQuery = new EmployeeQuery(event.getId(), event.getName());
    employeeQueryRepository.save(employeeQuery);
  }

}
