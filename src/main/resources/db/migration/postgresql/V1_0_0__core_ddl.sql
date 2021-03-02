CREATE TABLE playlist (
    id VARCHAR PRIMARY KEY,
    href VARCHAR NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE track_log (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    playlist_id VARCHAR NOT NULL,
    artist_id VARCHAR NOT NULL,
    artist_name VARCHAR NOT NULL,
    album_id VARCHAR NOT NULL,
    album_name VARCHAR NOT NULL,
    duration BIGINT NOT NULL,
    explicit bool NOT NULL,
    added_by VARCHAR NOT NULL,
    added_at timestamptz NOT NULL,
    CONSTRAINT pk_track_log_id PRIMARY KEY (id, added_at),
    CONSTRAINT fk_track_log_playlist FOREIGN KEY (playlist_id) REFERENCES playlist(id)
);

CREATE INDEX added_at_idx_desc ON track_log (added_at DESC);