package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Jogo;
import util.JPAUtil;

import java.util.List;

public class JogoDAO {

    private EntityManager em;

    public JogoDAO() {
        em = JPAUtil.criarEntityManager();
    }

    public void salvar(Jogo jogo) {
        em.getTransaction().begin();
        em.persist(jogo);
        em.getTransaction().commit();
    }

    public static void editar(Jogo jogo) {
        EntityManager em = JPAUtil.criarEntityManager();
        em.getTransaction().begin();
        em.merge(jogo);
        em.getTransaction().commit();
        em.close();
    }

    public static void excluir(Jogo jogo) {
        EntityManager em = JPAUtil.criarEntityManager();
        em.getTransaction().begin();
        jogo = em.find(Jogo.class, jogo.getId());
        em.remove(jogo);
        em.getTransaction().commit();
        em.close();
    }
    
    public static Jogo acharPorId(int id) {
        EntityManager em = JPAUtil.criarEntityManager();
        Jogo j = em.find(Jogo.class, id);
        em.close();
        return j;
    }
   
    public static List<Jogo> listar() {
        EntityManager em = JPAUtil.criarEntityManager();
        Query query = em.createQuery("SELECT j FROM Jogo j");
        List<Jogo> resultado = query.getResultList();
        em.close();
        return resultado;
    }
}