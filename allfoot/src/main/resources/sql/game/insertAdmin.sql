INSERT INTO admin_user (name, line_id) SELECT
                                         :name,
                                         :line_id
                                       WHERE NOT exists(SELECT 1
                                                        FROM admin_user
                                                        WHERE line_id = :line_id);