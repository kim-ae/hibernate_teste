package hibernate_teste;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Father")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Father {

    @Id
    @Column(name = "FATHER_ID", columnDefinition = "BINARY(16)", length = 16)
    private UUID id;
    
    @Column(name="TITLE")
    private String title;
    
    @Column(name="Composite")
    private boolean Composite = false;

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAsComposite(){
        Composite = true;
    }
    
}
