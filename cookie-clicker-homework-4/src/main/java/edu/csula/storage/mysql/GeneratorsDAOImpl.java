package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;
import edu.csula.storage.mysql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below

	protected static final String getAllQuery = "SELECT * FROM generators;";
	protected static final String getByIdQuery = "SELECT * FROM generators WHERE id=?;";
	protected static final String setQuery = "UPDATE generators SET name=?, description=?, rate=?, base_cost=?, unlock_at=? WHERE id=?;";
	protected static final String addQuery = "INSERT INTO generators (id, name, description, rate, base_cost, unlock_at, created_by) VALUES (?, ?, ?, ?, ?, ?, ?);";
	protected static final String removeQuery = "DELETE FROM generators WHERE id=?;";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get all generators from jdbc
		//return new ArrayList<>();

		List<Generator> result = new ArrayList<>();

		try (Connection c = context.getConnection(); 
			Statement stmt = c.createStatement()) {

			ResultSet rs = stmt.executeQuery(getAllQuery);

			while (rs.next()) {
            	Generator generator = new Generator(
            		rs.getInt(1),
            		rs.getString(2),
            		rs.getString(3),
            		rs.getInt(4),
            		rs.getInt(5),
            		rs.getInt(6)
            	);
				result.add(generator);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get specific generator by id
		//return Optional.empty();

		try (Connection c = context.getConnection(); 
			PreparedStatement stmt = c.prepareStatement(getByIdQuery)) 
		{
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
        	Generator generator = new Generator(
        		rs.getInt(1),
        		rs.getString(2),
        		rs.getString(3),
        		rs.getInt(4),
        		rs.getInt(5),
        		rs.getInt(6)
        	);

        	return Optional.of(generator);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();

	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: update specific generator by id

		try (Connection c = context.getConnection(); 
			PreparedStatement stmt = c.prepareStatement(setQuery)) 
		{

    		stmt.setString(1, generator.getName());
    		stmt.setString(2, generator.getDescription());
    		stmt.setInt(3, generator.getRate());
    		stmt.setInt(4, generator.getBaseCost());
    		stmt.setInt(5, generator.getUnlockAt());

			stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(Generator generator) {
		
		try (
			Connection c = 
			context.getConnection(); 
			PreparedStatement stmt = c.prepareStatement(addQuery)) {

			stmt.setInt(1, generator.getId());
			stmt.setString(2, generator.getName());
    		stmt.setString(3, generator.getDescription());
    		stmt.setInt(4, generator.getRate());
    		stmt.setInt(5, generator.getBaseCost());
    		stmt.setInt(6, generator.getUnlockAt());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(int id) {

		try (Connection c = context.getConnection(); 
			PreparedStatement stmt = c.prepareStatement(removeQuery)) 
		{
			
			stmt.setInt(1, id);

			stmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
