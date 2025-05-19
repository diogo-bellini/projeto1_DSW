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
create table SessaoTeste(descricao text not null,
                         id_sessao bigint not null auto_increment,
                         primary key (id_sessao),
                         nome_testador varchar(256) not null,
                         estrategia_id bigint not null,
                         foreign key(estrategia_id) references Estrategia(id_estrategia),
                         tempo int not null, projeto_id bigint not null,
                         foreign key (projeto_id) references Projeto(id_projeto),
                         usuario_id bigint not null,
                         foreign key(usuario_id) references Usuario(id_usuario),
                         data_criacao timestamp default current_timestamp);
create table UsuarioProjeto(usuario_id bigint not null,
                            projeto_id bigint not null,
                            primary key(usuario_id, projeto_id),
                            foreign key (usuario_id) references Usuario(id_usuario),
                            foreign key (projeto_id) references Projeto(id_projeto));
create table UsuarioSessao(usuario_id bigint not null,
                           sessao_id bigint not null,
                           primary key (usuario_id, sessao_id),
                           foreign key (usuario_id) references Usuario(id_usuario),
                           foreign key (sessao_id) references SessaoTeste(id_sessao),
                           status enum('criado', 'em execucao', 'finalizado'));
create table Estrategia(id_estrategia bigint not null auto_increment unique,
                        primary key (id_estrategia),
                        nome varchar(256) not null,
                        descricao text,
                        exemplo text,
                        dica text);
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
