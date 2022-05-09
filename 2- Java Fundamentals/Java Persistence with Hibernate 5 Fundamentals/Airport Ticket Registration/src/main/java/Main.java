import airport.Airport;
import airport.Passenger;
import airport.Ticket;
import airport.TicketKey;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.m02.ex01");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Airport airport = new Airport(1, "Henri Coanda");

        Passenger john = new Passenger(1, "John Smith");
        john.setCity("Cairo");
        john.setNumber("3");
        john.setStreet("Tadamon St.");
        john.setZipCode("11511");
        john.setPrefix("+20");
        john.setLineNumber("1123456789");
        john.setAirport(airport);

        Passenger mike = new Passenger(2, "Michael Johnson");
        mike.setCity("Giza");
        mike.setNumber("3");
        mike.setStreet("S St.");
        mike.setZipCode("12345");
        mike.setAirport(airport);

        airport.addPassenger(john);
        airport.addPassenger(mike);

        Ticket ticket1 = new Ticket();
        TicketKey ticketKey1 = new TicketKey();
        ticketKey1.setSeries("AA");
        ticketKey1.setNumber("1234");
        ticket1.setId(ticketKey1);
        ticket1.setPassenger(john);
        john.addTicket(ticket1);

        em.persist(airport);
        em.persist(john);
        em.persist(mike);

        em.persist(ticket1);

        em.getTransaction().commit();
        emf.close();
    }
}
