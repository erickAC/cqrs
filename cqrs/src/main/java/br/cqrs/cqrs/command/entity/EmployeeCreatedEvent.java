package br.cqrs.cqrs.command.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeCreatedEvent {

  private final Long id;
  private final String name;

}
