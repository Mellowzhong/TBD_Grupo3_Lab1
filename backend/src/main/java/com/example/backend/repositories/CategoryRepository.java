package com.example.backend.repositories;

import com.example.backend.entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    private Sql2o sql2o;

    public List<CategoryEntity> findAll() {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM categories WHERE deleted_at IS NULL";
            return con.createQuery(sql)
                    .executeAndFetch(CategoryEntity.class);
        }
    }

    public CategoryEntity findById(long id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM categories WHERE id = :id AND deleted_at IS NULL";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(CategoryEntity.class);
        }
    }

    public CategoryEntity findByName(String name) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM categories WHERE name = :name AND deleted_at IS NULL";
            return con.createQuery(sql)
                    .addParameter("name", name)
                    .executeAndFetchFirst(CategoryEntity.class);
        }
    }

    public CategoryEntity findByNameAndNotId(String name, long id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM categories WHERE name = :name AND id <> :id  AND deleted_at IS NULL";
            return con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeAndFetchFirst(CategoryEntity.class);
        }
    }

    public CategoryEntity save(CategoryEntity category) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO categories (name) VALUES (:name)";
            int id = con.createQuery(sql, true)
                    .addParameter("name", category.getName())
                    .executeUpdate()
                    .getKey(Integer.class);
            category.setId(id);
            return category;
        }
    }

    public CategoryEntity update(long id, CategoryEntity category) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE categories SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", category.getName())
                    .addParameter("id", id)
                    .executeUpdate();
            CategoryEntity updatedCategory = con.createQuery("SELECT * FROM categories WHERE id = :id")
                    .addParameter("id", category.getId())
                    .executeAndFetchFirst(CategoryEntity.class);

            return updatedCategory;
        }
    }

    public boolean delete(long id) {
        String query = "UPDATE categories SET deleted_at = :deletedAt WHERE id = :id AND deleted_at IS NULL";
        try (Connection con = sql2o.open()) {
            int rowsSoftDeleted = con.createQuery(query)
                    .addParameter("deletedAt", LocalDateTime.now())
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult();
            return rowsSoftDeleted > 0;
        }
    }

}
