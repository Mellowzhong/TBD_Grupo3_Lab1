package com.example.backend.repositories;

import com.example.backend.dtos.LogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class LogRepository {

    private final Sql2o sql2o;

    public LogRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<LogDTO> findAllLogs() {
        String query = "SELECT id, iduser AS userId, table_name AS tableName, idelement AS elementId, operation, description, date FROM logs";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(query).executeAndFetch(LogDTO.class);
        }
    }
}
