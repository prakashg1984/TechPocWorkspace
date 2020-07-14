package com.example.springsecurity.bo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Greeting {

	  @Digits(integer=100, fraction=0)
	  private long id;
	  
	  @NotNull
	  @Size(min=2 , max=10)
	  private String content;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getContent() {
	    return content;
	  }

	  public void setContent(String content) {
	    this.content = content;
	  }

}
