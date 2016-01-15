package hibernate_teste;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Main {
    
    private static final UUID GRANDCHILD_ID = UUID.randomUUID();
    private static final UUID CHILD_ID = UUID.randomUUID();
    private static final UUID COMPOSITE_ID = UUID.randomUUID();
    private static final UUID LEAF_ID_1 = UUID.randomUUID();
    private static final UUID LEAF_ID_2 = UUID.randomUUID();
    
    private static final String GRANDCHILD_TITLE = "Grandchild";
    private static final String CHILD_TITLE = "Child";
    private static final String COMPOSITE_TITLE = "Composite";
    private static final String LEAF_TITLE_1 = "Leaf 1";
    private static final String LEAF_TITLE_2 = "Leaf 2";
    
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry service = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
        SessionFactory sessionFactory =  configuration.buildSessionFactory(service);
        Session session = sessionFactory.openSession();
        
        saveGrandchild(session);
        saveChild(session);
        System.out.println("What kind of questions I have?");
        get(session).forEach(q -> {
            System.out.println(q.getClass().toString());
        });

        remove(GRANDCHILD_ID, session, Grandchild.class);
        session.close();
        
    }
    
    @SuppressWarnings("unchecked")
    public static List<Father> get(Session session){
        return session.createCriteria(Father.class).add(Restrictions.eq("leaf", false)).list();
    }
    
    public static void remove(UUID id, Session session, Class<?> questionClass){
        session.beginTransaction();
        Father father = (Father) session.byId(questionClass).getReference(id);
        session.delete(father);
        System.out.println("Removing: "+father.getTitle());
        session.getTransaction().commit();
        
    }
    
    public static void saveGrandchild(Session session){
        session.beginTransaction();
        Grandchild grandchild = new Grandchild();
        grandchild.setId(GRANDCHILD_ID);
        grandchild.setTitle(GRANDCHILD_TITLE);
        
        Composite clist = new Composite();
        clist.setId(COMPOSITE_ID);
        clist.setTitle(COMPOSITE_TITLE);
        clist.addLeaf(new Leaf(LEAF_ID_1, LEAF_TITLE_1, clist));
        clist.addLeaf(new Leaf(LEAF_ID_2, LEAF_TITLE_2, clist));
        
        grandchild.setComposite(clist);
        session.save(grandchild);
        
        session.getTransaction().commit();
        System.out.println("Grandchild inserted");
    }
    
    public static void saveChild(Session session){
        session.beginTransaction();
        Child m = new Child();
        m.setId(CHILD_ID);
        m.setTitle(CHILD_TITLE);
        session.save(m);
        session.getTransaction().commit();
        System.out.println("Child inserted");
    }
}
