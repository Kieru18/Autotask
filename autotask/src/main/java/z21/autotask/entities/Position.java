package z21.autotask.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positions_seq")
    @SequenceGenerator(name = "positions_seq", sequenceName = "positions_seq", allocationSize = 1)
    @NonNull
    @Column(name = "position_id")
    private Integer positionId;

    private String name;

    private String photo;

}
