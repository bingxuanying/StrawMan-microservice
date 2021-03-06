require("dotenv/config");

const serverSetting = {
  port: process.env.PORT || 3000,
};

const postgresSetting = {
  user: process.env.POSTGRES_USER || "postgres",
  host: process.env.POSTGRES_HOST || "localhost",
  database: process.env.POSTGRES_DB || "test",
  password: process.env.POSTGRES_PASSWORD || "Maple012",
  port: process.env.POSTGRES_PORT || null,
};

module.exports = Object.assign({}, { serverSetting, postgresSetting });
