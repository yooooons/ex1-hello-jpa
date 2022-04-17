package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {

            Movie movie = new Movie();
            movie.setDirector("A");
            movie.setActor("B");
            movie.setName("바람과 함께 사라지다.");
            movie.setPrice(10000);
            em.persist(movie);

            Album album = new Album();
            album.setArtist("빅뱅");
            album.setName("봄 여름 가을 겨울");
            album.setPrice(9000);
            em.persist(album);

            Movie movie1 = new Movie();
            movie1.setDirector("C");
            movie1.setActor("D");
            movie1.setName("드래곤볼.");
            movie1.setPrice(10000);
            em.persist(movie1);

            em.flush();
            em.clear();

            Item item = em.find(Item.class, movie1.getId());
            System.out.println("item = " + item);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();

        }

        emf.close();




    }
}
