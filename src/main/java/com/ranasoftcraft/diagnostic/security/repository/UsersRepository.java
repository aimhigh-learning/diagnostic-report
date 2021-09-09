package com.ranasoftcraft.diagnostic.security.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.diagnostic.security.entity.Users;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, String> {
	
}
