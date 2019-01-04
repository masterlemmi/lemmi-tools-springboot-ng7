package com.lemzki.tools.people.db.service;


import com.lemzki.tools.people.db.dto.ComplexPersonDTO;

//adds children, parents, reationships to a DTO
public interface RelationsUtil {

    ComplexPersonDTO addRelations (ComplexPersonDTO dto);
}
