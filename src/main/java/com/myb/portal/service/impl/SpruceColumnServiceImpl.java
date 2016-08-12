package com.myb.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myb.portal.model.content.ContentArticle;
import com.myb.portal.model.content.SpruceColumn;
import com.myb.portal.model.design.SpruceTemplate;
import com.myb.portal.repository.ContentArticleRepository;
import com.myb.portal.repository.SpruceColumnRepository;
import com.myb.portal.service.SpruceColumnService;

@Service()
@Singleton
public class SpruceColumnServiceImpl implements SpruceColumnService{

	@Autowired
	private SpruceColumnRepository spruceColumnRepository;
	
	@Autowired
	private ContentArticleRepository contentArticleRepository;
	
	
	@Override
	@Transactional
	public List<SpruceColumn> findByParent(String parentId) {
		List<SpruceColumn> scs;
		if(parentId == null){
			scs = spruceColumnRepository.findByParentColumnIsNullOrderBySortNumberAsc();
		}else{
			SpruceColumn pc = spruceColumnRepository.findOne(parentId);
			scs = (pc == null? new ArrayList<SpruceColumn>(): new ArrayList<SpruceColumn>(pc.getChildrenColumns()));
		}
		for(SpruceColumn c:scs){
			c.updateParentView();
			c.setHasChildren(c.getChildrenColumns()!=null&&c.getChildrenColumns().size()>0);
		}
		return scs;
	}

	@Override
	@Transactional
	public SpruceColumn findByShortName(String shortName) {
		SpruceColumn sc = spruceColumnRepository.findByShortName(shortName);
		return sc;
	}

	@Override
	@Transactional
	public ContentArticle getDefaultArticle(SpruceColumn sc) {
		if(sc.getDefaultArticleId() == null || sc.getDefaultArticleId().equals("")){
			return null;
		}
		ContentArticle ca = contentArticleRepository.findOne(sc.getDefaultArticleId());
		return ca;
	}

	@Override
	public String getColumnTemplatePath(String spruceColumnId) {
		SpruceColumn col = this.spruceColumnRepository.findOne(spruceColumnId);
		SpruceTemplate temp = col.getSpruceTemplate();
		if(temp==null)return null;
		if(temp.getTemplatePath()==null)return null;
		return temp.getTemplatePath();
	}

	@Override
	@Transactional
	public SpruceColumn findOne(String columnId) {
		SpruceColumn sc = spruceColumnRepository.findOne(columnId);
		if(sc!= null){
			sc.updateParentView();
			sc.setHasChildren(sc.getChildrenColumns()!=null&&sc.getChildrenColumns().size()>0);
		}
		return sc;
	}

	public SpruceColumnRepository getSpruceColumnRepository() {
		return spruceColumnRepository;
	}

	public void setSpruceColumnRepository(
			SpruceColumnRepository spruceColumnRepository) {
		this.spruceColumnRepository = spruceColumnRepository;
	}

	public ContentArticleRepository getContentArticleRepository() {
		return contentArticleRepository;
	}

	public void setContentArticleRepository(
			ContentArticleRepository contentArticleRepository) {
		this.contentArticleRepository = contentArticleRepository;
	}

}
