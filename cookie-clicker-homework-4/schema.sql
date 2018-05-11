CREATE TABLE events (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    trigger_at INTEGER,
    created_by INTEGER REFERENCES events(id),
    index(created_by)
);

INSERT INTO events (id, name, description, trigger_at, created_by)
VALUES
(0, 'Ponyville Polls', '+ You became one of the best selling hotel cooperation, according to polls of Ponyville villagers.', 60, 1),
(0, 'Twilight Award', '++ You obtained the "Most Outstanding Hotel Company" award from Princess Twilight Sparkle.', 300, 1),
(0, 'Celestia Review', '+++ Princess Celestia gave a rave review to one of your hotel.', 600, 2)
;

CREATE TABLE generators (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    rate INTEGER,
    base_cost INTEGER,
    unlock_at INTEGER,
    created_by INTEGER REFERENCES generators(id),
    index(created_by)
);

INSERT INTO generators (id, name, description, rate, base_cost, unlock_at, created_by) 
VALUES 
(0, 'Grandma', 'Grandma likes to make cookies', 5, 10, 10, 1),
(0, 'Factory', 'Factory to produce cookies', 10, 50, 50, 1),
(0, 'Mine', 'Mining cookies', 20, 200, 200, 2)
;