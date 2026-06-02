import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 150, // usuarios virtuales
    duration: '30s', // duración de la prueba
};
//graphql query para listar pedidos
export default function () {
    const query = `
        query {
            listarPedidos {
                id
                clienteId
                productosIds
                total
                estado
                fechaPedido
            }
        }
    `;

    const payload = JSON.stringify({ query });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post('http://localhost:8081/graphql', payload, params);

    check(res, {
        'status es 200': (r) => r.status === 200,
    });

    sleep(1);
}
//rest endpoint para listar pedidos
export function listarPedidosRest() {
    const res = http.get('http://localhost:8081/api/rest/pedidos'); 
    check(res, {
        'status es 200': (r) => r.status === 200,
    });
}