package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "generators";

	public GeneratorsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {

		Object data = context.getAttribute(CONTEXT_NAME);

		if (data == null) {
			return new ArrayList<>();
		}

		return (ArrayList<Generator>) data;
		
	}

	@Override
	public Optional<Generator> getById(int id) {
		
		List<Generator> list = getAll();

		for ( Generator g : list ) {
			if (id == g.getId())
				return Optional.of(g);
		}

		return Optional.empty();

	}

	@Override
	public void set(int id, Generator generator) {

		List<Generator> list = getAll();

		for ( Generator g : list ) {
			if (id == g.getId()) {

				g.setId(generator.getId());
				g.setName(generator.getName());
				g.setDescription(generator.getDescription());
				g.setRate(generator.getRate());
				g.setBaseCost(generator.getBaseCost());
				g.setUnlockAt(generator.getUnlockAt());

				//e.setId(event.getId());
				//e.setName(event.getName());
				//e.setDescription(event.getDescription());
				//e.setTriggerAt(event.getTriggerAt());
			}
		}

		context.setAttribute(CONTEXT_NAME, list);

	}

	@Override
	public void add(Generator generator) {
		
		List<Generator> list = getAll();

		list.add(generator);
		
		context.setAttribute(CONTEXT_NAME, list);

	}

	@Override
	public void remove(int id) {
		
		List<Generator> entries = getAll();

		for (int i = 0 ; i < entries.size(); i++) {
			if (entries.get(i).getId() == id)
				entries.remove(i);
		}

		context.setAttribute(CONTEXT_NAME, entries);

	}
}
