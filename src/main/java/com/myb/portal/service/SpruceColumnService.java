package com.myb.portal.service;

import java.util.List;

import com.myb.portal.model.content.ContentArticle;
import com.myb.portal.model.content.SpruceColumn;

public interface SpruceColumnService {
	List<SpruceColumn> findByParent(String parentId);
	SpruceColumn findByShortName(String shortName);
	ContentArticle getDefaultArticle(SpruceColumn sc);
	String getColumnTemplatePath(String spruceColumnId);
	SpruceColumn findOne(String columnId);
}
