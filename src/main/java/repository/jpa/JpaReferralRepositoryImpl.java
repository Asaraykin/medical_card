package repository.jpa;

import model.Referral;
import model.Visit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.ReferralRepository;
import util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaReferralRepositoryImpl implements ReferralRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Referral save(Referral referral, int visitId) {
        Visit ref = em.getReference(Visit.class, visitId);
        if(!referral.isNew() && get(referral.getId(), visitId) == null){
            return null;
        }
        else {
            if (referral.isNew()){
                referral.setVisit(ref);
                em.persist(referral);
                return referral;
            }
            else {
                referral.setVisit(ref);
                return em.merge(referral);
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int visitId) throws NotFoundException {
        return em.createNamedQuery(Referral.DELETE)
                .setParameter("id", id)
                .setParameter("visit_id", visitId)
                .executeUpdate() != 0;
    }

    @Override
    public Referral get(int id, int visitId) throws NotFoundException {
        Referral referral = em.find(Referral.class, id);
        return referral == null && referral.getVisit().getId() != visitId ? null : referral;
    }

    @Override
    public List<Referral> getAll(int visitId) {
        return em.createNamedQuery(Referral.GET_ALL)
                .setParameter("visit_id", visitId)
                .getResultList();
    }
}
