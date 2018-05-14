package com.IES.DAO;

import java.util.List;

import com.IES.models.Candidate;
import com.IES.models.EvaluationPoint;

public interface CandidateDAO {
	public List<Candidate> getAllCandidates();
	public void addCandidate(Candidate candidate);
	public void deleteCandidate(int candidateId);
	public Candidate updateCandidate(Candidate candidate);
	public Candidate getCandidateById(int candidateId);
	public void insertEvaluationPoints(EvaluationPoint evaluationPoint);
	public int updateCandidateStatus(int candidateId);
	public List<Candidate> getAllEvaluatedCandidates();
	public List<Candidate> getAllNotEvaluatedCandidates();
	
}
