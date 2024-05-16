
DELETE FROM trip;

-- Inserindo registros no banco de dados com dados diferentes

INSERT INTO trip (qtd_pessoas, local, guia_responsavel, valor_unitario, data_inicio, data_final, descricao, image)
VALUES
    (3, 'Rio de Janeiro', 'Ana Silva', 100.00, '2024-05-20', '2024-05-25', 'Explorar as belas praias e pontos turísticos do Rio de Janeiro.', 'imgPath/rio-de-janeiro.jpg'),
    (5, 'Foz do Iguaçu', 'Carlos Oliveira', 150.00, '2024-06-10', '2024-06-15', 'Contemplar as majestosas Cataratas do Iguaçu e explorar a natureza exuberante da região.', 'imgPath/foz-do-iguacu.jpg'),
    (2, 'Bonito', 'Juliana Santos', 120.00, '2024-07-05', '2024-07-10', 'Mergulhar nas águas cristalinas dos rios e grutas de Bonito e desfrutar das paisagens naturais únicas.','imgPath/Bonito-Mato-Grosso.jpg'),
    (4, 'Salvador', 'José Lima', 80.00, '2024-08-15', '2024-08-20', 'Explorar o centro histórico de Salvador, com suas igrejas barrocas e cultura afro-brasileira.','imgPath/salvador.jpeg'),
    (6, 'Gramado', 'Fernanda Oliveira', 200.00, '2024-09-20', '2024-09-25', 'Curtir o clima europeu de Gramado, com seus jardins floridos, arquitetura charmosa e delícias gastronômicas.','imgPath/Gramado.jpg'),
    (3, 'Fernando de Noronha', 'Pedro Almeida', 250.00, '2024-10-10', '2024-10-15', 'Explorar as praias paradisíacas, fazer mergulho com snorkel e observar a vida marinha em Fernando de Noronha.','imgPath/Morro-dois-Irmãos-Noronha.jpg'),
    (5, 'Manaus', 'Mariana Silva', 180.00, '2024-11-05', '2024-11-10', 'Conhecer a exuberante Floresta Amazônica, fazer passeios de barco pelo rio Negro e visitar o Teatro Amazonas.','imgPath/manaus.jpg'),
    (2, 'Búzios', 'Lucas Oliveira', 150.00, '2024-12-15', '2024-12-20', 'Relaxar nas praias deslumbrantes de Búzios e explorar suas boutiques, restaurantes e vida noturna.','imgPath/buzios_city.jpeg'),
    (4, 'Porto de Galinhas', 'Amanda Santos', 120.00, '2025-01-20', '2025-01-25', 'Aproveitar as piscinas naturais de águas cristalinas, praticar esportes aquáticos e saborear a culinária local.','imgPath/porto-de-galinhas.png'),
    (6, 'Chapada dos Veadeiros', 'Rafaela Lima', 180.00, '2025-02-10', '2025-02-15', 'Explorar as cachoeiras, trilhas e formações rochosas da Chapada dos Veadeiros e conectar-se com a natureza.','imgPath/chapada-dos-veadeiros.jpg');
