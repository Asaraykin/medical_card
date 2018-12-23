package repository;

import model.Referral;
import util.exception.NotFoundException;

import java.util.List;

public interface ReferralRepository {
    Referral save(Referral referral, int visitId);

    // false if referral do not belong to parentId
    boolean delete(int id, int visitId) throws NotFoundException;

    Referral get(int id, int visitId) throws NotFoundException;

    // ORDERED dateTime desc
    List<Referral> getAll(int visitId);
}
