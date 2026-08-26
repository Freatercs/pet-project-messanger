-- Создание таблицы пользователей
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(500),
    bio TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    last_seen_at TIMESTAMPTZ,
    CONSTRAINT username_min_length CHECK (LENGTH(username) >= 3),
    CONSTRAINT email_format CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
    );

-- Создание индексов для ускорения поиска
CREATE INDEX idx_users_first_name ON users(first_name);
CREATE INDEX idx_users_last_name ON users(last_name);
CREATE INDEX idx_users_created_at ON users(created_at);
CREATE INDEX idx_users_last_seen_at ON users(last_seen_at);

-- Комментарии к таблице и колонкам (документация)
COMMENT ON TABLE users IS 'Таблица пользователей мессенджера';
COMMENT ON COLUMN users.id IS 'Уникальный идентификатор пользователя';
COMMENT ON COLUMN users.username IS 'Уникальное имя пользователя (никнейм) - обязательно';
COMMENT ON COLUMN users.first_name IS 'Имя пользователя (опционально)';
COMMENT ON COLUMN users.last_name IS 'Фамилия пользователя (опционально)';
COMMENT ON COLUMN users.email IS 'Уникальный email пользователя - обязательно';
COMMENT ON COLUMN users.password_hash IS 'Хеш пароля (bcrypt/argon2)';
COMMENT ON COLUMN users.avatar_url IS 'URL аватара пользователя';
COMMENT ON COLUMN users.bio IS 'Краткая информация о пользователе';
COMMENT ON COLUMN users.created_at IS 'Дата и время регистрации';
COMMENT ON COLUMN users.last_seen_at IS 'Последнее время активности пользователя';