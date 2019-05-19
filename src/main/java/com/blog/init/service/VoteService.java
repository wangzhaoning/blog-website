package com.blog.init.service;

import java.util.Optional;

import com.blog.init.domain.Vote;


/**
 * Vote 服务接口.
 * 
 */
public interface VoteService {
	/**
	 * 根据id获取 Vote
	 * @param id
	 * @return
	 */
	Optional<Vote> getVoteById(Integer id);
	/**
	 * 删除Vote
	 * @param id
	 * @return
	 */
	void removeVote(Integer id);
}
