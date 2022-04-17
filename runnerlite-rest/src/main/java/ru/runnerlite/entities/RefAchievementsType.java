package ru.runnerlite.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ref_achievements_type")
public class RefAchievementsType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "CODE", nullable = false, length = 10)
	private String code;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "PICTURE_PATH")
	private String picturePath;
	
	public Integer getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPicturePath() {
		return picturePath;
	}
	
}