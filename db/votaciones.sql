CREATE DATABASE sistema_votaciones;
USE sistema_votaciones;

CREATE TABLE voter (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    has_voted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE candidate(
	candidate_id INT AUTO_INCREMENT PRIMARY KEY,
    candidate_name VARCHAR(100) NOT NULL,
    party VARCHAR(100),
    votes INT NOT NULL DEFAULT 0
);

CREATE TABLE vote(
	vote_id INT AUTO_INCREMENT PRIMARY KEY,
    voter_id INT NOT NULL,
    candidate_id INT NOT NULL,
    UNIQUE (voter_id),
    
    CONSTRAINT fk_vote_voter
        FOREIGN KEY (voter_id)
        REFERENCES voter(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_vote_candidate
        FOREIGN KEY (candidate_id)
        REFERENCES candidate(candidate_id)
        ON DELETE CASCADE
);