INSERT INTO personal_info (
    first_name,
    last_name,
    title,
    profile_description,
    profile_image_url,
    years_of_experience,
    email,
    phone,
    linkedin_url,
    github_url
)
VALUES (
           'Juan Carlos',
           'Hernández Montero',
           'Ingeniero en Sistemas Computacionales',
           'Desarrollador en formación, apasionado por Java y el desarrollo de software.',
           'img/JuanCarlos.png',
           0,
           'juancarlos.hdz.montero@gmail.com',
           '5624524910',
           'https://www.linkedin.com/in/juan-carlos-hernández-montero-93b159228',
           'https://github.com/juancakun'
       );

INSERT INTO skills (
    name,
    level_percentage,
    icon_class,
    personal_info_id
)
VALUES
    ('Java', 90, 'img/logos/java.png', 1),
    ('Spring Boot', 85, 'img/logos/spring-boot.png', 1),
    ('PostgreSQL', 80, 'img/logos/postgre-sql.png', 1),
    ('MySQL', 80, 'img/logos/mysql.png', 1),
    ('SQL Server', 75, 'img/logos/servidor-sql.png', 1),
    ('C/C++', 65, 'img/logos/logotipo-de-c-.png', 1),
    ('HTML5', 85, 'img/logos/html-5.png', 1),
    ('CSS3', 80, 'img/logos/css-3.png', 1),
    ('JavaScript', 70, 'img/logos/js.png', 1),
    ('React Native', 75, 'img/logos/react-js.png', 1),
    ('Expo', 70, 'img/logos/expo.png', 1),
    ('Git', 85, 'img/logos/git.png', 1),
    ('AWS', 50, 'img/logos/aws.png', 1);

INSERT INTO educations (
    degree,
    institution,
    start_date,
    end_date,
    description,
    personal_info_id
)
VALUES (
           'Ingeniería en Sistemas Computacionales',
           'Escuela Superior de Cómputo (ESCOM) - Instituto Politécnico Nacional',
           '2020-08-01',
           '2026-01-31',
           'Formación en desarrollo de software, programación, bases de datos, sistemas distribuidos, desarrollo web, arquitectura de software y tecnologías de cómputo.',
           1
       );

INSERT INTO experiences (
    job_title,
    company_name,
    start_date,
    end_date,
    description,
    personal_info_id
)
VALUES (
           'Desarrollador Full Stack',
           'ProfeAsis - Proyecto académico',
           '2024-01-01',
           '2025-01-01',
           'Desarrollo de una aplicación móvil para docentes de educación básica utilizando React Native y Expo. Implementación de registro de asistencia, gestión de alumnos, periodos, actividades y calificaciones, generación de reportes y códigos QR. Utilización de SQLite para almacenamiento local y desarrollo de una arquitectura basada en contextos y servicios.',
           1
       ),
       (
           'Desarrollador Backend',
           'ZonaFitSpring - Proyecto personal',
           '2026-01-01',
           '2026-06-01',
           'Desarrollo de una aplicación web backend utilizando Java y Spring Boot. Implementación de operaciones CRUD, integración con bases de datos MySQL, manejo de entidades, repositorios y persistencia de información. Uso de Maven para la gestión del proyecto y Git para control de versiones.',
           1
       ),
       (
           'Desarrollador Java',
           'Sistema distribuido de procesamiento de archivos - Proyecto académico',
           '2024-01-01',
           '2024-06-01',
           'Desarrollo de un sistema distribuido en Java compuesto por un servidor coordinador y tres servidores trabajadores. El servidor principal distribuía equitativamente los archivos entre los servidores disponibles, que procesaban el contenido de manera concurrente para localizar palíndromos relacionados con una palabra proporcionada por el usuario. Implementación de concurrencia, comunicación entre servidores, sincronización mediante semáforos y consolidación de resultados.',
           1
       ),
       (
           'Desarrollador Backend',
           'My Portfolio Backend - Proyecto personal',
           '2026-08-01',
           NULL,
           'Desarrollo de un backend para portafolio profesional utilizando Java, Spring Boot y PostgreSQL. Implementación de persistencia mediante Spring Data JDBC, configuración de conexión con una base de datos PostgreSQL alojada en Neon y diseño de estructuras para información personal, habilidades, educación y proyectos profesionales.',
           1
       );