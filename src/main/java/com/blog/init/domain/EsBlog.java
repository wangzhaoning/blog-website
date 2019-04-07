package com.blog.init.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName="blog",type="blog")
public class EsBlog implements Serializable {

	private static final long serialVersionUID = 1L;
		@Id
		private String id;
		private String title;
		private String summary;
		private String content;
		protected  EsBlog() {
			
		}
		public EsBlog(String title, String summary, String content) {
			this.title = title;
			this.summary = summary;
			this.content = content;
		}
		
}
