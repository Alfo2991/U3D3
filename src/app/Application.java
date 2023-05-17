package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;

import app.Evento.TipoEvento;
import app.Persona.Sesso;
import utils.JpaUtil;

public class Application {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/gestioneventi";
        String username = "postgres";
        String password = "qwerty1234";

        try {
            System.out.println("Connecting to PostgreSQL...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");

            PersonaDAO personaDAO = new PersonaDAO();
            Persona persona = new Persona("Mario", "Rossi", "mario@example.com", LocalDate.of(1990, 5, 10), Sesso.MASCHIO);
            personaDAO.save(persona);

            LocationDAO locationDAO = new LocationDAO();
            Location location = new Location("Nome Location", "Città");
            locationDAO.save(location);

            EventoDAO eventoDAO = new EventoDAO();
            Evento evento = new Evento("Titolo Evento", LocalDate.of(2023, 5, 17), "Descrizione Evento", TipoEvento.PUBBLICO, 100);
            evento.setLocation(location);

            PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();
            Partecipazione partecipazione = new Partecipazione(persona, evento, Partecipazione.Stato.CONFERMATA);
            evento.addPartecipazione(partecipazione);

            eventoDAO.save(evento);

            System.out.println("Event saved successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (JpaUtil.getEntityManagerFactory() != null) {
                JpaUtil.getEntityManagerFactory().close();
            }
        }
    }
}
