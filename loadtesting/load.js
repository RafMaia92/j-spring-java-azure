import http from 'k6/http'
import { check, sleep } from 'k6'

export const options = {

    stages: [
        { duration: '1m', target: 2000 }, // simulate ramp-up of traffic from 1 to 100 users over 5 minutes.]
        { duration: '10s', target: 1400 },
        { duration: '2m', target: 200 }, // stay at 100 users for 10 minutes
        { duration: '1m', target: 0 }, // ramp-down to 0 users
    ],

    thresholds: {
        http_req_failed: ['rate<0.01'], // http errors should be less than 1%
        http_req_duration: ['p(95)<200'], // 95% of requests should be below 200ms
    },

};

export default function () {
 
  let res = http.get('https://jsh-parking-fn.azurewebsites.net/api/hello')

  check(res, { 'success login': (r) => r.status === 200 })

  sleep(0.3)
}
