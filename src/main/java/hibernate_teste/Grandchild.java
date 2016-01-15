package hibernate_teste;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Grandchild")
public class Grandchild extends Child{
    
    @Column(name = "VAR")
    private String var;
    
    @OneToOne(mappedBy="grandchild", cascade = CascadeType.ALL)
    private Composite composite;
    
    public void setVar(String c){
        var = c;
    }
    
    public void setComposite(Composite cs){
        composite = cs;
    }
}
