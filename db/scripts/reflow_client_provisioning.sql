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
  ('grassp',
   'oauth2-resource',
   '$2a$12$dYoDao.6cFWR1K.FpCNKEe0MatQJ7Wok4kBmixn7Bz5DR98NOiQpC', # "grassp" after the bcrypt (12 rounds).
   'read,write',
   'client_credentials,password,refresh_token',
   1209600, # 14 days = 336 hours.
   0, # never expires. For now.
   '.*');

INSERT INTO
  applicable_tax
  (name, rate)
VALUES
  ('Sales Tax', 0.0);