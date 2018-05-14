package com.IES.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IES.DAO.CandidateDAO;
import com.IES.models.Candidate;
import com.IES.models.EvaluationPoint;


@Service
@Transactional
public class CandidateServiceImpl implements CandidateService{

	
	@Autowired
	private CandidateDAO candidateDao;
	
	@Override
	@Transactional
	public List<Candidate> getAllCandidates() {
		// TODO Auto-generated method stub
		return candidateDao.getAllCandidates();
	}

	@Override
	@Transactional
	public void addCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		candidateDao.addCandidate(candidate);
	}

	@Override
	@Transactional
	public void deleteCandidate(int candidateId) {
		// TODO Auto-generated method stub
		candidateDao.deleteCandidate(candidateId);
	}

	@Override
	public Candidate updateCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		return candidateDao.updateCandidate(candidate);
	}
	
	@Override
	public Candidate getCandidateById(int candidateId) {
		// TODO Auto-generated method stub
		return candidateDao.getCandidateById(candidateId);
	}

	@Override
	@Transactional
	public void insertEvaluationPoints(EvaluationPoint evaluationPoint) {
		// TODO Auto-generated method stub
		candidateDao.insertEvaluationPoints(evaluationPoint);
	}

	@Override
	public int updateCandidateStatus(int candidateID) {
		// TODO Auto-generated method stub
		return candidateDao.updateCandidateStatus(candidateID);
		
	}
	
	public List<Candidate> getAllEvaluatedCandidates() {
		return candidateDao.getAllEvaluatedCandidates();
		
	}

	public List<Candidate> getAllNotEvaluatedCandidates() {
		// TODO Auto-generated method stub
		return candidateDao.getAllNotEvaluatedCandidates();//AllEvaluatedCandidates(candidateId);
	}

}
