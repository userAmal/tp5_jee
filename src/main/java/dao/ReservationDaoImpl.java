package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Reservation;
import util.JPAutil;

public class ReservationDaoImpl implements IReservationDAO {
	private EntityManager entityManager = JPAutil.getEntityManager("TP5");

	@Override
	public Reservation save(Reservation r) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(r);
		tx.commit();
		return r;
	}

	@Override
	public List<Reservation> ReservationsParMC(String mc) {
		List<Reservation> res = entityManager.createQuery("select r from resrvations r where r.NOM_CLIENT like :mc").setParameter("mc", "%" + mc + "%").getResultList();

		return res;
	}

	@Override
	public Reservation getReservation(Long id) {
		return entityManager.find(Reservation.class, id);
	}

	@Override
	public Reservation updateReservation(Reservation r) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(r);
		tx.commit();
		return r;
	}

	@Override
	public void deleteReservation(Long id) {
		Reservation reservation = entityManager.find(Reservation.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(reservation);
		entityManager.getTransaction().commit();
	}

}