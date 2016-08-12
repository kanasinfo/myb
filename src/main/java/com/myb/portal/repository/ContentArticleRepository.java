package com.myb.portal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.content.ContentArticle;
import com.myb.portal.model.content.SpruceColumn;

public interface ContentArticleRepository extends JpaRepository<ContentArticle, String>{
	Page<ContentArticle> findByColumn(SpruceColumn sc, Pageable page);
	Page<ContentArticle> findByColumnOrderByIssueTimeDesc(SpruceColumn sc, Pageable page);
	Page<ContentArticle> findByColumnIsNullOrderBySortNumberAsc(Pageable page);
	Page<ContentArticle> findByColumnIsNullOrderByIssueTimeDesc(Pageable page);
}
