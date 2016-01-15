package hibernate_teste;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Child")
public class Child extends Father {
    
    @Column(name = "BOOL")
    private boolean bool;

    public Child setBool(boolean b){
        bool = b;
        return this;
    }
}
