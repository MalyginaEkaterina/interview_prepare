/*ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. 
Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;*/

SELECT 
    a.name,
    a.start_time,
    a.duration,
    b.name,
    b.start_time,
    b.duration
FROM
    (SELECT 
        s.id,
            s.start_time,
            DATE_ADD(s.start_time, INTERVAL m.duration SECOND) AS end_time,
            m.name,
            m.duration
    FROM
        sessions s
    INNER JOIN movies m ON s.movie_id = m.id) a,
    (SELECT 
        s2.id, s2.start_time, m2.name, m2.duration
    FROM
        sessions s2
    INNER JOIN movies m2 ON s2.movie_id = m2.id) b
WHERE
    a.start_time <= b.start_time
        AND b.start_time < a.end_time
        AND a.id <> b.id
        order by a.start_time

/*перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
 Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;*/
 
with a as
(SELECT 
        s.id,
		s.start_time,
		DATE_ADD(s.start_time, INTERVAL m.duration SECOND) AS end_time,
		m.name,
		m.duration
    FROM
        sessions s
    INNER JOIN movies m ON s.movie_id = m.id)
SELECT 
    a1.name,
    a1.start_time,
    a1.duration,
    a2.start_time,
    TIMESTAMPDIFF(MINUTE, a1.end_time, a2.start_time) AS diff
FROM
    a AS a1,
    sessions AS a2
WHERE
    NOT EXISTS( SELECT * FROM a AS a3 WHERE a3.start_time < a1.end_time AND a3.end_time > a1.end_time)
	AND a2.start_time = (SELECT MIN(s2.start_time) FROM sessions s2 WHERE s2.start_time > a1.end_time)
HAVING diff >= 30
ORDER BY diff DESC

/*список фильмов, для каждого — с указанием общего числа посетителей за все время, 
среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). 
Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;*/

WITH a AS 
(SELECT 
    s.movie_id,
    s.id,
    COUNT(*) AS visitors,
    SUM(s.price) AS session_price
FROM tickets t INNER JOIN sessions s ON t.session_id = s.id
GROUP BY s.id)
SELECT 
    m.name,
    SUM(a.visitors) AS total_visitors,
    AVG(a.visitors) AS avg_show_visitors,
    SUM(a.session_price) AS total_amount
FROM
    a,
    movies m
WHERE
    a.movie_id = m.id
GROUP BY a.movie_id
UNION
SELECT 
    'total',
    SUM(a.visitors) AS total_visitors,
    AVG(a.visitors) AS avg_show_visitors,
    SUM(a.session_price) AS total_amount
FROM
    a
ORDER BY total_amount DESC

/*число посетителей и кассовые сборы, сгруппированные по времени начала фильма: 
с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.). */

SELECT 
    COUNT(*) AS count_visitor,
    SUM(s.price) AS total_amount,
    CASE
        WHEN HOUR(s.start_time) >= 9 AND HOUR(s.start_time) < 15 THEN 'с 9 до 15'
        WHEN HOUR(s.start_time) >= 15 AND HOUR(s.start_time) < 18 THEN 'с 15 до 18'
        WHEN HOUR(s.start_time) >= 18 AND HOUR(s.start_time) < 21 THEN 'с 18 до 21'
        WHEN HOUR(s.start_time) >= 21 THEN 'с 21 до 00:00'
        ELSE 'до 9'
    END time_interval
FROM tickets t INNER JOIN sessions s ON t.session_id = s.id
GROUP BY time_interval