create database TestesBD;

use TestesBD;

create table Usuario(id_usuario bigint not null auto_increment unique,
                     email varchar(50) not null unique,
                     nome varchar(256) not null,
                     senha varchar(256) not null,
                     papel enum('administrador', 'testador') not null,
                     data_criacao timestamp default current_timestamp,
                     primary key (id_usuario));
create table Projeto(id_projeto bigint not null auto_increment unique,
                     nome varchar(256) not null,
                     descricao text not null,
                     data_criacao timestamp default current_timestamp,
                     primary key(id_projeto));
create table Estrategia(id_estrategia bigint not null auto_increment unique,
                        primary key (id_estrategia),
                        nome varchar(256) not null,
                        descricao text,
                        exemplo text,
                        dica text);
CREATE TABLE SessaoTeste (
                             id_sessao BIGINT NOT NULL AUTO_INCREMENT,
                             descricao TEXT NOT NULL,
                             nome_testador VARCHAR(256) NOT NULL,
                             estrategia_id BIGINT NOT NULL,
                             tempo INT NOT NULL,
                             projeto_id BIGINT NOT NULL,
                             usuario_id BIGINT NOT NULL,
                             status ENUM('criado', 'em_execucao', 'finalizado'),
                             data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             PRIMARY KEY (id_sessao),
                             FOREIGN KEY (estrategia_id) REFERENCES Estrategia(id_estrategia),
                             FOREIGN KEY (projeto_id) REFERENCES Projeto(id_projeto),
                             FOREIGN KEY (usuario_id) REFERENCES Usuario(id_usuario)
);
create table UsuarioProjeto(usuario_id bigint not null,
                            projeto_id bigint not null,
                            primary key(usuario_id, projeto_id),
                            foreign key (usuario_id) references Usuario(id_usuario),
                            foreign key (projeto_id) references Projeto(id_projeto));
create table Imagem(id_imagem bigint not null auto_increment unique,
                    primary key (id_imagem),
                    url varchar(256) not null,
                    data_criacao timestamp default current_timestamp,
                    estrategia_id bigint not null,
                    foreign key(estrategia_id) references Estrategia(id_estrategia));
create table Bug(id_bug bigint not null auto_increment unique,
                 primary key (id_bug),
                 descricao text,
                 data_criacao timestamp default current_timestamp,
                 sessao_id bigint not null,
                 foreign key (sessao_id) references SessaoTeste(id_sessao));
