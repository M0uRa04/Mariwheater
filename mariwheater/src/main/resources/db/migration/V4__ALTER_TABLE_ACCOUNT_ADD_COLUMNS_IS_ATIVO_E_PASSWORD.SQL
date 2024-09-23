ALTER TABLE account
ADD password varchar(150) null,
ADD isAtivo boolean DEFAULT TRUE;