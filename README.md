Transactions:
```sql
CREATE TABLE users (
                      id SERIAL PRIMARY KEY,
                      nickname VARCHAR(50) NOT NULL,
                      email VARCHAR(100) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE sessions (
                         id SERIAL PRIMARY KEY,
                         user_id INT NOT NULL,
                         token VARCHAR(255) NOT NULL,
                         expires TIMESTAMP NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);
```
```sql
CREATE TABLE stations (
                         id SERIAL PRIMARY KEY,
                         station VARCHAR(50) NOT NULL
);
CREATE TABLE orders (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(100) NOT NULL,
                       from_station_id INT NOT NULL,
                       to_station_id INT NOT NULL,
                       status INT NOT NULL,
                       created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (from_station_id) REFERENCES stations(id),
                       FOREIGN KEY (to_station_id) REFERENCES stations(id)
);
insert into stations (id, station) values (0, 'Moscow');
insert into stations (id, station) values (1, 'SPB');
insert into stations (id, station) values (2, 'Novgorod');
insert into stations (id, station) values (3, 'Sevastopol');
insert into stations (id, station) values (4, 'Sochi');
insert into stations (id, station) values (5, 'Khabarovsk');
insert into stations (id, station) values (6, 'Vladivostok');
insert into stations (id, station) values (7, 'Petrozavodsk');
```