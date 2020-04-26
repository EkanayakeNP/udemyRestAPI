package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details all about the user") //for swagger2 documentation
@Entity
public class User {

		@Id
		@GeneratedValue
		private Integer id;
		
		@Size(min=2,message="Name should be minimun 2 charactors")
		@ApiModelProperty(notes="Name should be minimun 2 charactors") //for swagger2 documentation
		private String name;
		
		@Past
		@ApiModelProperty(notes="Birthday should be in the past") //for swagger2 documentation
		private Date birthday;
		
		@OneToMany(mappedBy="user")
		private List<Post> post;
		
		protected  User(){
			
		}
		public User(Integer id, String name, Date birthday) {
			super();
			this.id = id;
			this.name = name;
			this.birthday = birthday;
		}
		
		
		
		public List<Post> getPost() {
			return post;
		}
		public void setPost(List<Post> post) {
			this.post = post;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		
		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
		}
		
		
		
}
