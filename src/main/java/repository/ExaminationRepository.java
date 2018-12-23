package repository;

import model.Examination;
import util.exception.NotFoundException;

import java.util.List;

public interface ExaminationRepository {
    Examination save(Examination examination, int referralId);

    // false if examination do not belong to parentId
    boolean delete(int id, int referralId) throws NotFoundException;

    Examination get(int id, int referralId) throws NotFoundException;

    // ORDERED dateTime desc
    List<Examination> getAll(int referralId);
}
