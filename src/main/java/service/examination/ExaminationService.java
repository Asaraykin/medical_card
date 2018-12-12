package service.examination;

import model.Examination;
import util.exception.NotFoundException;

import java.util.List;

public interface ExaminationService {
    Examination create(Examination examination, int referralId);

    void update(Examination examination, int referralId) throws NotFoundException;

    void delete(int id, int referralId) throws NotFoundException;

    Examination get(int id, int referralId) throws NotFoundException;

    List<Examination> getAll(int referralId);
}
