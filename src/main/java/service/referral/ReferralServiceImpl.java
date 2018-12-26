package service.referral;

import model.Referral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repository.ReferralRepository;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.checkNotFoundWithId;

@Service
public class ReferralServiceImpl implements ReferralService {

    @Autowired
    private ReferralRepository referralRepository;

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public Referral create(Referral referral, int visitId) {
        Assert.notNull(referral, "referral should not be null");
        return referralRepository.save(referral, visitId);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void update(Referral referral, int visitId) throws NotFoundException {
        checkNotFoundWithId(referralRepository.save(referral, visitId), referral.getId());
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public void delete(int id, int visitId) throws NotFoundException {
        checkNotFoundWithId(referralRepository.delete(id, visitId), id);
    }

    @Override
    public Referral get(int id, int visitId) throws NotFoundException {
        return checkNotFoundWithId(referralRepository.get(id, visitId), id);
    }

    @Override
    public List<Referral> getAll(int visitId) {
        return referralRepository.getAll(visitId);
    }
}
