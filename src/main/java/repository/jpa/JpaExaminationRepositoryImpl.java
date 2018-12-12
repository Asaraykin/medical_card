package repository.jpa;

import model.Examination;
import model.Referral;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import repository.ExaminationRepository;
import util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaExaminationRepositoryImpl implements ExaminationRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Examination save(Examination examination, int referralId) {
        if(!examination.isNew() && examination.getReferral().getId() != referralId){
            return null;
        }
        else {
            Referral referral = em.getReference(Referral.class, referralId);
            examination.setReferral(referral);
            if (examination.isNew()){
                em.persist(examination);
                return examination;
            }
            else {
                em.merge(examination);
                return examination;
            }
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int referralId) throws NotFoundException {
        return em.createNamedQuery(Examination.DELETE)
                .setParameter("id", id)
                .setParameter("referral_id", referralId)
                .executeUpdate() != 0;
    }

    @Override
    public Examination get(int id, int referralId) throws NotFoundException {
        Examination examination = em.find(Examination.class, id);
        return examination == null && examination.getReferral().getId() != referralId ? null : examination;
    }

    @Override
    public List<Examination> getAll(int referralId) {
        return em.createNamedQuery(Examination.GET_ALL)
                .setParameter("referral_id", referralId)
                .getResultList();
    }
}
