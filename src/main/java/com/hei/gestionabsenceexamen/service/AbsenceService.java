package com.hei.gestionabsenceexamen.service;

import com.hei.gestionabsenceexamen.entity.Absence;
import com.hei.gestionabsenceexamen.repository.AbsenceRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AbsenceService {

    private AbsenceRepository absenceRepository;

    public AbsenceService() throws SQLException {
        absenceRepository = new AbsenceRepository();
    }

    public List<Absence> getAllAbsences() throws SQLException {
        return absenceRepository.getAllAbsences();
    }

    public Absence getAbsenceById(Long id) throws SQLException {
        return absenceRepository.getAbsenceById(id);
    }

    public void saveAbsence(Absence absence) throws SQLException {
        absenceRepository.saveAbsence(absence);
    }

    public void updateAbsence(Long id, Absence absence) throws SQLException {
        absenceRepository.updateAbsence(id, absence);
    }

    public void deleteAbsence(Long id) throws SQLException {
        absenceRepository.deleteAbsence(id);
    }
}
