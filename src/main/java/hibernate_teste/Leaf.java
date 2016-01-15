package hibernate_teste;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Leaf")
public class Leaf {
    
    @Id
    @Column(name="ID", columnDefinition = "BINARY(16)")
    private UUID id;
    
    @Column(name="TITLE")
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "LEAF_ID", columnDefinition = "BINARY(16)")
    private Composite composite;
    
    public Leaf(){}
    public Leaf(UUID id, String title, Composite checkboxesId) {
        super();
        this.id = id;
        this.title = title;
        this.composite = checkboxesId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
