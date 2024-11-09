// src/services/productService.js

import httpClient from "../Utils/http-common";

const API_URL = "http://tu-backend.com/api/v1/products"; // Cambia esta URL según tu configuración

const productService = {
  async getProducts() {
    try {
      const response = await httpClient.get("/api/v1/products");
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Error al obtener productos"
      );
    }
  },

  async getProduct(id) {
    try {
      const response = await httpClient.get(`${API_URL}/${id}`);
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Producto no encontrado"
      );
    }
  },

  async postProduct(product) {
    try {
      const response = await httpClient.post(API_URL, product);
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Error al crear producto"
      );
    }
  },

  async putProduct(id, product) {
    try {
      const response = await httpClient.put(`${API_URL}/${id}`, product);
      return response.data;
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Error al actualizar producto"
      );
    }
  },

  async deleteProduct(id) {
    try {
      await httpClient.delete(`${API_URL}/${id}`);
      return true; // Retorna true si se elimina correctamente
    } catch (error) {
      throw new Error(
        error.response ? error.response.data : "Error al eliminar producto"
      );
    }
  },
};

export default productService;
