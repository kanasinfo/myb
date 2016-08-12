package com.myb.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.content.SpruceColumn;

public interface SpruceColumnRepository extends JpaRepository<SpruceColumn, String>{
	List<SpruceColumn> findByParentColumnIsNullOrderBySortNumberAsc();
	SpruceColumn findByShortName(String shortName);
}
