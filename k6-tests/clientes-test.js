import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 150,
    duration: '30s',
};
//graphql query para listar clientes
export default function () {

    const query = `
        query {
            listarClientes {
                id
                nombres
                apellidos
                correo
                activo
                fechaRegistro
            }
        }
    `;

    const payload = JSON.stringify({
        query: query
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(
        'http://localhost:8081/graphql',
        payload,
        params
    );

    check(res, {
        'status es 200': (r) => r.status === 200,
    });

    sleep(1);
}
//rest endpoint para listar clientes
export function listarClientesRest() {
    const res = http.get('http://localhost:8081/api/rest/clientes');
    check(res, {
        'status es 200': (r) => r.status === 200,
    });
}