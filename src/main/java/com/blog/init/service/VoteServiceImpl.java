package com.blog.init.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.init.domain.Vote;
import com.blog.init.repository.VoteRepository;

/**
 * Vote 服务实现.
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;
    
	@Override
	public Optional<Vote> getVoteById(Integer id) {
		return voteRepository.findById(id);
	}
	
	@Override
	public void removeVote(Integer id) {
		voteRepository.deleteById(id);
	}
}
