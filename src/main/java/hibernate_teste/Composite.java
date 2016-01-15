package hibernate_teste;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * Checkboxes will be always a child from Conditional. They have meanning only when associated with a conditional.
 */
@Entity
@Table(name="Composite")
public class Composite extends Father{

    @OneToMany(mappedBy = "composite", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Leaf> leafs = new ArrayList<Leaf>();
    
    @OneToOne
    @JoinColumn(name = "GRANDCHILD_ID")
    private Grandchild grandchild;
    
    public Composite(){
        this.setAsComposite();
    }
    
    public void addLeaf(Leaf c){
        leafs.add(c);
    }
    
    public List<Leaf> getLeaf(){
        List<Leaf> c = new ArrayList<Leaf>();
        c.addAll(leafs);
        return c;
    }
    
    public void setGrandchild(Grandchild c){
        grandchild =c;
    }
}
