package service.crud;

import config.HibernateConfig;
import model.Coche;
import model.Multa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateCrud {

    public void insertIntoCochesDB(List<Coche> cochesList){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            for(Coche coche : cochesList){
                session.save(coche);
            }

            transaction.commit();

            System.out.println("Coches insertados correctamente en la base de datos");
        }
    }

    public Coche getCocheFromDb(Integer id){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Coche coche = session.get(Coche.class, id);

            transaction.commit();

            return coche;
        }
    }

    public List<Integer> selectIdCoches(){
        List<Integer> idCoches = new ArrayList<>();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            idCoches = session.createQuery("SELECT id FROM Coche", Integer.class).getResultList();

            transaction.commit();
        }
        return idCoches;
    }

    public void insertIntoMultasDB(List<Multa> multasList){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            for(Multa multa : multasList){
                session.save(multa);
            }

            transaction.commit();
            System.out.println("multas insertadas correctamente en la base de datos");

        }
    }

    public List<Multa> selectMultasFromDb(){
        List<Multa> multasList = new ArrayList<>();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            multasList = session.createQuery("from Multa", Multa.class).getResultList();
            transaction.commit();
        }
        return multasList;
    }

    public List<Integer> selectIdMultas(){
        List<Integer> idMultas = new ArrayList<>();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            idMultas = session.createQuery("SELECT id FROM Multa", Integer.class).getResultList();

            transaction.commit();
        }
        return idMultas;
    }

    public void updateMultas(Integer id, Integer newPorcentaxeReduccion){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Multa multa = session.get(Multa.class, id);

            if(multa != null){
                int porcentaxeReduccion = multa.getPorcentaxereduccion();
                multa.setPorcentaxereduccion(porcentaxeReduccion + newPorcentaxeReduccion);
            }
            transaction.commit();
            System.out.println("Porcentaxe reduccion actualuizado con éxito");
        }
    }

    public void updateMultasSinSuma(Integer id, Integer newPorcentaxeReduccion){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Multa multa = session.get(Multa.class, id);

            if(multa != null){
                multa.setPorcentaxereduccion(newPorcentaxeReduccion);
            }
            transaction.commit();
            System.out.println("Porcentaxe reduccion actualuizado con éxito");
        }
    }


    public void deleteDatosDB(String query){
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createQuery(query).executeUpdate();

            transaction.commit();

            System.out.println("Datos de la tabla borrados correctamente en la base de datos");
        }
    }






}
