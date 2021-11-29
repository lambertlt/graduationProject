package com.lambert.fun.new_app.service;

import com.lambert.fun.new_app.entity.PersonalColumn;

public interface PersonalColumnService {
    Object getPersonalColumnById(Long id);

    Object getPersonalColumnAll();

    Object newPersonalColumnSave(PersonalColumn personalColumn);

    Object deletePersonalColumnById(Long id);

    Object getPersonalColumnByUserId(Long id);

    Object updatePersonalColumn(PersonalColumn personalColumn);
}
