INSERT INTO
  oauth_client_details
  (client_id,
   resource_ids,
   client_secret,
   scope,
   authorized_grant_types,
   access_token_validity,
   refresh_token_validity,
   autoapprove)
VALUES
  ('reflow',
   'oauth2-resource',
   '$2a$12$SnYWnr.B4a7qlGnFLck16OVOkuGs4fN4Du78tk7KmwXoUANkPr6mG', # "reflow" after the bcrypt (12 rounds).
   'read,write',
   'client_credentials,password,refresh_token',
   1209600, # 14 days = 336 hours.
   0, # never expires. For now.
   '.*');
