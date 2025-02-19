CREATE TABLE comments (
  id UUID PRIMARY KEY,
  content TEXT NOT NULL,
  parent_comment_id UUID,
  talker_id UUID,
  FOREIGN KEY (parent_comment_id) REFERENCES comments(id),
  FOREIGN KEY (talker_id) REFERENCES comments(id)
);