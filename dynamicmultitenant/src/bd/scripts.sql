-- public.appfunctionality definition

-- Drop table

-- DROP TABLE public.appfunctionality;

CREATE TABLE public.appfunctionality (
	functionalityid int8 NOT NULL,
	description varchar(100) NULL,
	"name" varchar(50) NULL,
	CONSTRAINT appfunctionality_pkey PRIMARY KEY (functionalityid)
);


-- public.apprefreshtoken definition

-- Drop table

-- DROP TABLE public.apprefreshtoken;

CREATE TABLE public.apprefreshtoken (
	refreshtokenid bigserial NOT NULL,
	active bool NULL,
	generated_at timestamp(6) NULL,
	refreshtoken varchar(128) NULL,
	valid_until timestamp(6) NULL,
	CONSTRAINT apprefreshtoken_pkey PRIMARY KEY (refreshtokenid)
);


-- public.approle definition

-- Drop table

-- DROP TABLE public.approle;

CREATE TABLE public.approle (
	roleid int8 NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT approle_pkey PRIMARY KEY (roleid)
);


-- public.appuser definition

-- Drop table

-- DROP TABLE public.appuser;

CREATE TABLE public.appuser (
	userid int8 NOT NULL,
	active bool NULL,
	email varchar(255) NULL,
	firstaccess bool NULL,
	"name" varchar(255) NULL,
	twofactor bool NULL,
	CONSTRAINT appuser_pkey PRIMARY KEY (userid),
	CONSTRAINT uk_hcij6hlq32eras7xlmvbx2sbm UNIQUE (email)
);


-- public.registry definition

-- Drop table

-- DROP TABLE public.registry;

CREATE TABLE public.registry (
	registry_id int8 NOT NULL,
	description varchar(255) NULL,
	instant_time timestamp(6) NULL,
	CONSTRAINT registry_pkey PRIMARY KEY (registry_id)
);


-- public.app_user_credential definition

-- Drop table

-- DROP TABLE public.app_user_credential;

CREATE TABLE public.app_user_credential (
	credentialid int8 NOT NULL,
	credential varchar(128) NULL,
	userid int8 NULL,
	CONSTRAINT app_user_credential_pkey PRIMARY KEY (credentialid),
	CONSTRAINT uk_ao5vrue9jdq445xj86ssp9rkv UNIQUE (userid),
	CONSTRAINT fkd31o51t3thckdy3k5jcgy8f5j FOREIGN KEY (userid) REFERENCES public.appuser(userid)
);


-- public.approle_appfunctionality definition

-- Drop table

-- DROP TABLE public.approle_appfunctionality;

CREATE TABLE public.approle_appfunctionality (
	functionalityid int8 NOT NULL,
	roleid int8 NOT NULL,
	CONSTRAINT fkcei0iqho3f1tqssn210uql1t8 FOREIGN KEY (functionalityid) REFERENCES public.appfunctionality(functionalityid),
	CONSTRAINT fkla0as05544sj2e8xell1svbta FOREIGN KEY (roleid) REFERENCES public.approle(roleid)
);


-- public.approle_appuser definition

-- Drop table

-- DROP TABLE public.approle_appuser;

CREATE TABLE public.approle_appuser (
	userid int8 NOT NULL,
	roleid int8 NOT NULL,
	CONSTRAINT fkbjnm7ubu1mejkyny5g9ovimv0 FOREIGN KEY (roleid) REFERENCES public.approle(roleid),
	CONSTRAINT fkqadr4a2s4cii5e200xc17njg FOREIGN KEY (userid) REFERENCES public.appuser(userid)
);


-- public.apptoken definition

-- Drop table

-- DROP TABLE public.apptoken;

CREATE TABLE public.apptoken (
	tokenid bigserial NOT NULL,
	active bool NULL,
	generated_at timestamp(6) NULL,
	ip varchar(24) NULL,
	"token" varchar(128) NULL,
	valid_until timestamp(6) NULL,
	apprefreshtokenid int8 NULL,
	userid int8 NULL,
	CONSTRAINT apptoken_pkey PRIMARY KEY (tokenid),
	CONSTRAINT fkbukqo5oydc0dr73nty8f3vcko FOREIGN KEY (userid) REFERENCES public.appuser(userid),
	CONSTRAINT fkoyk99l3r6nxawepikt73frr6p FOREIGN KEY (apprefreshtokenid) REFERENCES public.apprefreshtoken(refreshtokenid)
);


-- public.apptwofactorauth definition

-- Drop table

-- DROP TABLE public.apptwofactorauth;

CREATE TABLE public.apptwofactorauth (
	twofactorauthid int8 NOT NULL,
	secretkey varchar(255) NULL,
	userid int8 NULL,
	CONSTRAINT apptwofactorauth_pkey PRIMARY KEY (twofactorauthid),
	CONSTRAINT uk_5dqlpaxqxfxibysuaoxmeeavx UNIQUE (userid),
	CONSTRAINT fk94pcd3263dj15e3yncihkf3mo FOREIGN KEY (userid) REFERENCES public.appuser(userid)
);


-- public.resetpassword definition

-- Drop table

-- DROP TABLE public.resetpassword;

CREATE TABLE public.resetpassword (
	resetid int8 NOT NULL,
	active bool NULL,
	ip varchar(24) NULL,
	"token" varchar(6) NULL,
	valid_until int8 NULL,
	userid int8 NULL,
	CONSTRAINT resetpassword_pkey PRIMARY KEY (resetid),
	CONSTRAINT fk19lffly8h7yn4ojdrxqo9kv0j FOREIGN KEY (userid) REFERENCES public.appuser(userid)
);