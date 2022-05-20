package ru.runnerlite.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode
@Table(name = "teams_volunteers_template")
public class TeamsVolunteerTemplate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TEAMS_ID", nullable = false)
    @ToString.Exclude
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REF_VOLUNTEERS_POSITION_ID", nullable = false)
    @ToString.Exclude
    private RefVolunteersPosition refVolunteersPosition;

    @Column(name="QTY",nullable = false)
    private Integer qty;

}
