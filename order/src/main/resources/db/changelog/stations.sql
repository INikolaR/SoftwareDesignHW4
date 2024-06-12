--changeset nikola:1
CREATE TABLE IF NOT EXISTS stations (
                                        id SERIAL PRIMARY KEY,
                                        station VARCHAR(50) NOT NULL
);

--changeset nikola:2
insert into stations (id, station) values
                                       (0, 'Moscow'),
                                        (1, 'SPB'),
                                        (2, 'Novgorod'),
                                        (3, 'Sevastopol'),
                                        (4, 'Sochi'),
                                        (5, 'Khabarovsk'),
                                        (6, 'Vladivostok'),
                                        (7, 'Petrozavodsk')
                                    on conflict (id) do nothing;