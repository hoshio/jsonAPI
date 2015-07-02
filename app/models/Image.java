package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
	public Image(){}
	public Image(int id, String imageName, List<Info> infos) {
		this.id = id;
		this.imageName = imageName;
		this.infos = infos;
	}

	public int id;
	
	@JsonProperty("Image_name")
	public String imageName;
	
	public List<Info> infos;
	
	public static class Info {
		public Info(){}
		public Info(String infoName) {
			this.infoName = infoName;
		}

		@JsonProperty("Info_name")
		public String infoName;
		
	}
}