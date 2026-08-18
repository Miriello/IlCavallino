    -- Schema "Il Cavallino" — compatibile MySQL 8+ / MariaDB
-- Per PostgreSQL: sostituire AUTO_INCREMENT con SERIAL, ENUM con VARCHAR + CHECK

CREATE DATABASE IF NOT EXISTS ilcavallino CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ilcavallino;

-- --------------------------------------------------------
-- Utenti del sistema (Socio e Addetti)
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS utenti (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    nome           VARCHAR(100) NOT NULL,
    cognome        VARCHAR(100) NOT NULL,
    codice_fiscale VARCHAR(16)  NOT NULL UNIQUE,
    username       VARCHAR(50)  NOT NULL UNIQUE,
    password       VARCHAR(255) NOT NULL,   -- bcrypt in produzione
    ruolo          ENUM('SOCIO','CUCINA','VENDITA','MAGAZZINO','MARKETING') NOT NULL
);

-- --------------------------------------------------------
-- Fornitori
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS fornitori (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    partita_iva     VARCHAR(11)  NOT NULL UNIQUE,
    ragione_sociale VARCHAR(200) NOT NULL,
    email           VARCHAR(150),
    citta           VARCHAR(100),
    regione         VARCHAR(100),
    cap             VARCHAR(5)
);

-- --------------------------------------------------------
-- Prodotti in menu
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS prodotti (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(200)   NOT NULL,
    prezzo   DECIMAL(10,2)  NOT NULL,
    scadenza DATE
);

CREATE TABLE IF NOT EXISTS prodotto_ingredienti (
    prodotto_id      INT,
    ingrediente_nome VARCHAR(200),
    PRIMARY KEY (prodotto_id, ingrediente_nome),
    FOREIGN KEY (prodotto_id) REFERENCES prodotti(id) ON DELETE CASCADE
);

-- --------------------------------------------------------
-- Ingredienti in magazzino
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS ingredienti (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(200) NOT NULL,
    scadenza DATE
);

-- --------------------------------------------------------
-- Registro vendite
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS vendite (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    prodotto_id INT NOT NULL,
    quantita    INT NOT NULL,
    operatore   VARCHAR(50),
    data_ora    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (prodotto_id) REFERENCES prodotti(id)
);

-- --------------------------------------------------------
-- Ordini a fornitore (placeholder)
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS ordini_fornitore (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    fornitore_id INT NOT NULL,
    data_ordine  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    stato        ENUM('IN_ATTESA','EVASO','ANNULLATO') DEFAULT 'IN_ATTESA',
    note         TEXT,
    FOREIGN KEY (fornitore_id) REFERENCES fornitori(id)
);

-- --------------------------------------------------------
-- Dati demo
-- --------------------------------------------------------
INSERT IGNORE INTO utenti (nome, cognome, codice_fiscale, username, password, ruolo) VALUES
  ('Mario',  'Rossi',   'RSSMRA80A01H501Z', 'admin',      'admin123', 'SOCIO'),
  ('Luigi',  'Bianchi', 'BNCLGU90B02H501Y', 'cucina1',    'pass',     'CUCINA'),
  ('Anna',   'Verdi',   'VRDNNA85C03H501X', 'vendita1',   'pass',     'VENDITA'),
  ('Marco',  'Neri',    'NRIMRC75D04H501W', 'magazzino1', 'pass',     'MAGAZZINO'),
  ('Sara',   'Blu',     'BLUSRA70E05H501V', 'marketing1', 'pass',     'MARKETING');

INSERT IGNORE INTO fornitori (partita_iva, ragione_sociale, email, citta, regione, cap) VALUES
  ('12345678901', 'Carni Selezionate SRL', 'ordini@carniselezionate.it', 'Milano', 'Lombardia', '20100'),
  ('98765432109', 'Prodotti Freschi SpA',  'ordini@prodottifreschi.it',  'Roma',   'Lazio',     '00100');
