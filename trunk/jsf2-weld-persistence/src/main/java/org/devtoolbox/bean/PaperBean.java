package org.devtoolbox.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.devtoolbox.model.Paper;
import org.jboss.seam.transaction.TransactionPropagation;
import org.jboss.seam.transaction.Transactional;


public @Named @RequestScoped class PaperBean implements Serializable
	{
	private @Inject EntityManager entityManager;
	
	private int size;
	private String text;

	@Transactional(TransactionPropagation.REQUIRED)
	public String add ( ) 
		{
		Paper paper = new Paper();
		paper.setSize(this.size);
		paper.setText(this.text);
		try 
			{
			entityManager.persist(paper);
			} 
		catch (Exception e) 
			{
			e.printStackTrace();
			}
		
		return "paper.xhtml";
		}
	
	@Transactional(TransactionPropagation.REQUIRED)
	public Paper getPaper ( )
		{		
		return entityManager.find ( Paper.class, 1L );
		}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}

	private static final long serialVersionUID = 1L;
	}
