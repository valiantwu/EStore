CREATE USER 'devUser'@'%' IDENTIFIED BY 'devPassWd';
DROP USER 'devUser'@'%';
GRANT SELECT,INSERT,DELETE ON dev.* TO 'devUser'@'%';
jdbc:mysql://localhost:3306/test
