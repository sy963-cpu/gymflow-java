-- ============================================================
-- GymFlow Datenbank Initialisierung
-- ============================================================

-- Nutzer-Tabelle
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Trainingsplan-Tabelle
CREATE TABLE training_plans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Übungen-Tabelle
CREATE TABLE exercises (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plan_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    equipment VARCHAR(255),
    sets INT DEFAULT 3,
    reps INT DEFAULT 10,
    weight DECIMAL(8, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (plan_id) REFERENCES training_plans(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Trainingseinheiten-Tabelle
CREATE TABLE training_sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    plan_id INT NOT NULL,
    date DATE NOT NULL,
    duration INT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (plan_id) REFERENCES training_plans(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Beispieldaten
INSERT INTO users (name, email, password_hash) VALUES
('Max Mustermann', 'max@example.de', 'hash123'),
('Anna Schmidt', 'anna@example.de', 'hash456');

INSERT INTO training_plans (user_id, name, description) VALUES
(1, 'Ganzkörpertraining', 'Trainingsplan für alle Muskelgruppen'),
(2, 'Oberkörper Fokus', 'Schwerpunkt auf Brust und Schultern');

INSERT INTO exercises (plan_id, name, equipment, sets, reps, weight) VALUES
(1, 'Bankdrücken', 'Bank', 4, 8, 100.00),
(1, 'Kniebeugen', 'Barbell', 4, 10, 120.00),
(2, 'Schulterdrücken', 'Dumbbell', 3, 10, 30.00);
