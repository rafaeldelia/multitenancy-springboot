INSERT INTO public.tbl_tenant_master (db_name,url,user_name,"password",driver_class,status) VALUES
	 ('springer','jdbc:postgresql://localhost:5432/postgres?currentSchema=tenant1','postgres','arlepton1234','org.postgresql.Driver','ACTIVE'),
	 ('cocacola','jdbc:postgresql://localhost:5432/postgres?currentSchema=tenant2','postgres','arlepton1234','org.postgresql.Driver','ACTIVE'),
	 ('midea','jdbc:mysql://localhost:3306/midea','root','root','com.mysql.cj.jdbc.Driver','ACTIVE'),
	 ('public','jdbc:postgresql://localhost:5432/postgres?currentSchema=public','postgres','arlepton1234','org.postgresql.Driver','ACTIVE');
