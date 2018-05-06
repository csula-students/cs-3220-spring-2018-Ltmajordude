package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.EventsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EventsDAOImpl implements EventsDAO {
	private final Database context;

	// TODO: https://github.com/csula/cs3220-spring-2018/blob/master/src/main/java/edu/csula/cs3220/examples/jdbc/GuestBookDAO.java

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM events";
	protected static final String getByIdQuery = "SELECT * FROM events WHERE id=";
	protected static final String setQuery = "UPDATE events SET name=?, description=?, trigger_at=? WHERE id=";
	protected static final String addQuery = "INSERT INTO events (id, name, description, trigger_at, created_by) VALUES (?, ?, ?, ?, ?)";
	protected static final String removeQuery = "DELETE FROM events WHERE id=";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {

		List<Event> result = new ArrayList<>();
		try (Connection c = context.getConnection(); 
			Statement stmt = c.createStatement()) {

			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				Event event = new Event(
            		rs.getInt(1),
            		rs.getString(2),
            		rs.getString(3),
            		rs.getInt(4)
            	);
				result.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return result;

	}

	@Override
	public Optional<Event> getById(int id) {
		// TODO: get specific event by id
		//return Optional.empty();

		//Event result = null;
		//List<Event> result = new ArrayList<>();

		try (Connection c = context.getConnection(); 
			Statement stmt = c.createStatement()) {

			ResultSet rs = stmt.executeQuery(
				"SELECT * FROM events WHERE id=1");

			Event event = new Event(
        		rs.getInt(1),
        		rs.getString(2),
        		rs.getString(3),
        		rs.getInt(4)
        	);

			//stmt.setInt(1, id);
			//stmt.executeQuery();

			/*
			ResultSet rs = stmt.executeQuery(getByIdQuery);
			
			while (rs.next()) {

				Event event = new Event(
            		rs.getInt(1),
            		rs.getString(2),
            		rs.getString(3),
            		rs.getInt(4)
            	);

				result = event;

			}
			*/
			return Optional.of(event); 

		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
		//return Optional.of(event);

	}

	@Override
	public void set(int id, Event event) {
		// TODO: update specific event by id
	}

	@Override
	public void add(Event event) {		

		try (Connection c = context.getConnection(); 
			PreparedStatement stmt = c.prepareStatement(addQuery)) {
			
			stmt.setInt(1, event.getId());
            stmt.setString(2, event.getName());
            stmt.setString(3, event.getDescription());
            stmt.setInt(4, event.getTriggerAt());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove event by id

		//

	}
}
