package org.philosophy.carwashing.mapper;

/*
ToDo добавить в мапперы, в которых поля не совпадают

    @Mapping(target="employeeId", source="entity.id")
    EmployeeDTO toDTO(Employee entity);

    @Mapping(target="id", source="dto.employeeId")
    Employee toEntity(EmployeeDTO dto);
 */
public interface GenericEntityMapper <E, D>{

    E toEntity(D d);
    D toDto(E e);
}
