
CREATE TABLE "main"."sys_user" (
  "id" INTEGER NOT NULL AUTOINCREMENT,
  "username" TEXT,
  "password" TEXT,
  "avatar" TEXT,
  "roles" TEXT,
  PRIMARY KEY ("id")
);


CREATE TABLE "main"."sm_domain" (
  "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "domain" TEXT,
  "create_time" INTEGER,
  "update_time" INTEGER,
  "expires_time" INTEGER,
  "last_generate_time" INTEGER,
  "status" INTEGER
);
