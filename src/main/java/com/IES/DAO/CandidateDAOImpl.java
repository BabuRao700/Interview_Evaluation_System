package com.IES.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.IES.models.Candidate;
import com.IES.models.EvaluationPoint;

@Repository
public class CandidateDAOImpl implements CandidateDAO{
	
	@Autowired
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Candidate> getAllCandidates() {
		return sessionFactory.getCurrentSession().createQuery("from Candidate")
        .list();
	}

	@Override
	public void addCandidate(Candidate candidate) {
		sessionFactory.getCurrentSession().saveOrUpdate(candidate);
		
	}

	@Override
	public void deleteCandidate(int candidateId) {
		Candidate candidate = (Candidate) sessionFactory.getCurrentSession().load(
				Candidate.class, candidateId);
        if (null != candidate ) {
            this.sessionFactory.getCurrentSession().delete(candidate);
        }
		
	}

	@Override
	public Candidate updateCandidate(Candidate candidate) {
		sessionFactory.getCurrentSession().update(candidate);
        return candidate;
	}

	@Override
	public Candidate getCandidateById(int candidateId){
		return (Candidate) sessionFactory.getCurrentSession().get(
				Candidate .class, candidateId);
	}


	public void insertEvaluationPoints(EvaluationPoint evaluationPoint) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().save(evaluationPoint);
		
	}

	@Override
	public int updateCandidateStatus(int candidateId) {
		// TODO Auto-generated method stub
		Candidate candidate=(Candidate) sessionFactory.getCurrentSession().get(
				Candidate .class, candidateId);
		
		if(candidate.isEvaluated()==false)
		{
			candidate.setEvaluated(true);
			sessionFactory.getCurrentSession().update(candidate);
			return 1;
		}
		
		return -1;
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> getAllEvaluatedCandidates() {
		return sessionFactory.getCurrentSession().createQuery("from Candidate where IsEvaluated=true")
		        .list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> getAllNotEvaluatedCandidates() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from Candidate where IsEvaluated=false")
		        .list();
	}
	
/*	
	public Candidate getCandidateByIdAndUserId(int candidateId){
		return (Candidate) sessionFactory.getCurrentSession().get(
				Candidate .class, candidateId);
	}*/
	
	

}
