import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 150, // usuarios virtuales
    duration: '30s', // duración de la prueba
};
//graphql query para listar productos
export default function () {
    const query = `
        query {
            listarProductos {   
                id
                nombre
                descripcion
                precio
                activo
                stock
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
//rest endpoint para listar productos
export function listarProductosRest() {
    const res = http.get('http://localhost:8081/api/rest/productos');
    check(res, {
        'status es 200': (r) => r.status === 200,
    });
}