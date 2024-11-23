import httpClient from "../Utils/http-common"; // Cambia esta URL según tu configuración

export const orderService = {

    async getProductOrdersById(id) {
        try {
            const response = await httpClient.get(`/api/v1/ordersDetails/order/${id}`);
            return response.data;
        } catch (error) {
            throw new Error(
                error.response ? error.response.data : "Error al obtener ordenes"
            );
        }
    },

    async gerOrderById(id) {
        try {
            const response = await httpClient.get(`/api/v1/orders/${id}`);
            return response.data;
        } catch (error) {
            throw new Error(
                error.response ? error.response.data : "Error al obtener orden"
            );
        }
    }

}



