SELECT
  question_text,
  options_text
FROM questions q
JOIN options o ON q.question_id = o.question_id
WHERE q.question_id = ? ORDER BY options_id;