SELECT *
FROM users
WHERE CHAR_LENGTH(username) <= /*username_length*/10

/*IF orders != null*/
ORDER BY /*$orders*/username
-- ELSE ORDER BY username
/*END*/

/*BEGIN*/
LIMIT
  /*IF offset != null*/
  /*offset*/0,
  /*END*/

  /*IF size != null*/
  /*size*/10
  /*END*/
/*END*/
