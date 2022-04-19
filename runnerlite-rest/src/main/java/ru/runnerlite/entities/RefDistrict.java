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
@Table(name = "ref_districts")
public class RefDistrict {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	//lazy
	@ManyToOne(optional = false)
	@JoinColumn(name = "REF_CITIES_ID", nullable = false)
	@ToString.Exclude
	private RefCity refCity;
}
