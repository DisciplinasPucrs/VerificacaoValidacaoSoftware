package com.vev.exemplo.bancodados;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class SqlIntegrationTestBase {

    private Connection connection;
    protected InvoiceDao dao;

    @BeforeEach
    void openConnectionAndCleanup() throws SQLException {

        connection = DriverManager.getConnection("jdbc:hsqldb:mem:book");

        /**
         * Criar tabelas se ainda não existem.
         */
        PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists invoice (name varchar(100), value double)");
        preparedStatement.execute();
        connection.commit();

        dao = new InvoiceDao(connection);

        /**
         * Limpar estado das tabelas antes de executar o teste.
         * Evitar que um teste influencie em outro.
         *
         * Observe que executar um simples 'truncate' é simples e suficiente para este teste.
         */
        connection.prepareStatement("truncate table invoice").execute();
    }

    @AfterEach
    void close() throws SQLException {
        /**
         * Na prática, configurar e utilizar um pool de conexões.
         */
        connection.close();
    }

}
