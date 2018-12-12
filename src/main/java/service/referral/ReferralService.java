package service.referral;

import model.Referral;
import util.exception.NotFoundException;

import java.util.List;

public interface ReferralService {

    Referral create(Referral referral, int visitId);

    void update(Referral referral, int visitId) throws NotFoundException;

    void delete(int id, int visitId) throws NotFoundException;

    Referral get(int id, int visitId) throws NotFoundException;

    List<Referral> getAll(int visitId);
}
