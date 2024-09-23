package com.hei.gestionabsenceexamen.controller;

import com.hei.gestionabsenceexamen.entity.Absence;
import com.hei.gestionabsenceexamen.service.AbsenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    private AbsenceService absenceService;

    public AbsenceController() throws SQLException {
        absenceService = new AbsenceService();
    }

    @GetMapping
    public List<Absence> getAllAbsences() throws SQLException {
        return absenceService.getAllAbsences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Absence> getAbsenceById(@PathVariable Long id) throws SQLException {
        Absence absence = absenceService.getAbsenceById(id);
        if (absence != null) {
            return ResponseEntity.ok(absence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public void createAbsence(@RequestBody Absence absence) throws SQLException {
        absenceService.saveAbsence(absence);
    }

    @PutMapping("/{id}")
    public void updateAbsence(@PathVariable Long id, @RequestBody Absence absence) throws SQLException {
        absenceService.updateAbsence(id, absence);
    }

    @DeleteMapping("/{id}")
    public void deleteAbsence(@PathVariable Long id) throws SQLException {
        absenceService.deleteAbsence(id);
    }
}
