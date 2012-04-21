package org.devtoolbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public @Entity @Table ( name = "paper" ) class Paper
	{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Id long id;
	private @Column ( name = "size" ) int size;
	private @Column ( name = "text" ) String text;
	
	public long getId ( )
		{
		return id;
		}

	public void setId ( long id )
		{
		this.id = id;
		}

	public int getSize ( )
		{
		return size;
		}
	
	public void setSize ( int size )
		{
		this.size = size;
		}
	
	public String getText ( )
		{
		return text;
		}
	
	public void setText ( String text )
		{
		this.text = text;
		}
	}
