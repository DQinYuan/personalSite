local score = redis.call("hget", "ID_SCORE_HASH", KEYS[1])
redis.call("zremrangebyscore", "SYS_ART_TABLE_PUBLISHED", score, score)
redis.call("hset", "ID_SCORE_HASH", KEYS[1], KEYS[2])
redis.call("zadd", "SYS_ART_TABLE_PUBLISHED", KEYS[2], ARGV[1])
